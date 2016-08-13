package social.amoeba.jeyson;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.junit.Before;
import org.junit.Test;
import social.amoeba.TestSupport;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.stream.Collectors;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class JSFileTest {

  private JSFile JSFile;

  @Before
  public void setUp() throws Exception {
    try (BufferedReader buffer = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/sample-script.js")))) {
      String jsScript = buffer.lines().collect(Collectors.joining("\n"));
      JSFile = new JSFile(jsScript);
    }
  }

  @Test
  public void shouldPassArgumentToScript() throws Exception {
    Object execute = JSFile.execute("returnArgument", new Arguments("some-arg"));
    assertThat(execute.toString(), is("some-arg"));
  }

  @Test
  public void shouldSupportEval() throws Exception {
    Object execute = JSFile.execute("evalSnippet", new Arguments("[1,2,3,4,5].join(',')"));
    assertThat(execute.toString(), is("1,2,3,4,5"));
  }

  @Test
  public void testInMemoryValues() throws Exception {
    Object result = ((ScriptObjectMirror)JSFile.execute("callsCount", null)).get("count");
    assertThat((Double)result, is(1.0));

    result = ((ScriptObjectMirror)JSFile.execute("callsCount", null)).get("count");
    assertThat((Double)result, is(2.0));
  }

  @Test
  public void testScopeExecute() throws Exception {
    HashMap scope = new HashMap();
    scope.put("field", "my-value");

    HashMap params = new HashMap();
    params.put("scope", scope);
    params.put("expression", "field +\"-cat\"");

    Object execute = JSFile.execute("executeScope", params);
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