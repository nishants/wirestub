package social.amoeba.jeyson;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ScriptTest {

  private Script script;

  @Before
  public void setUp() throws Exception {
    try (BufferedReader buffer = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/sample-script.js")))) {
      String jsScript = buffer.lines().collect(Collectors.joining("\n"));
      script = new Script(jsScript);
    }
  }

  @Test
  public void shouldHandleArrays() throws Exception {
    ArrayList list = new ArrayList();
    list.add(1);
    list.add(2);
    list.add(3);
    Object actual = (List)script.execute("insertInArray", list, new JeysonConfig(null));
    List expected = new ArrayList<>();
    expected.add(1);
    expected.add(2);
    expected.add(3);
    expected.add(4);
    assertThat(actual, is(expected));
  }
  @Test
  public void packList() throws Exception {
    ArrayList list = new ArrayList();
    list.add(1);
    list.add(2);
    list.add(3);
    Object actual = script.execute("unlist", list, new JeysonConfig(null));
    List expected = new ArrayList<>();
    expected.add(1);
    expected.add(2);
    expected.add(3);
    expected.add(4);
    assertThat(Json.stringify(actual), is(Json.stringify(expected)));
  }
  @Test
  public void shouldPassArgumentToScript() throws Exception {
    Object execute = script.execute("returnArgument", new Arguments("some-arg"));
    assertThat(execute.toString(), is("some-arg"));
  }

  @Test
  public void shouldSupportEval() throws Exception {
    Object execute = script.execute("evalSnippet", new Arguments("[1,2,3,4,5].join(',')"));
    assertThat(execute.toString(), is("1,2,3,4,5"));
  }

  @Test
  public void testInMemoryValues() throws Exception {
    Object result = ((ScriptObjectMirror) script.execute("callsCount", null)).get("count");
    assertThat((Double)result, is(1.0));

    result = ((ScriptObjectMirror) script.execute("callsCount", null)).get("count");
    assertThat((Double)result, is(2.0));
  }

  @Test
  public void testScopeExecute() throws Exception {
    HashMap scope = new HashMap();
    scope.put("field", "my-value");

    HashMap params = new HashMap();
    params.put("scope", scope);
    params.put("expression", "field +\"-cat\"");

    Object execute = script.execute("executeScope", params);
    assertThat(execute.toString(), is("my-value-cat"));
  }

  public static class Arguments {
    private final String message ;
    public String getMessage() {
      return message;
    }
    public Arguments(String message) {
      this.message = message;
    }

  }
}