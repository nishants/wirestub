package social.amoeba.jeyson;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class JSFileTest {

  private String path;
  private Arguments args;
  private Path filePath;
  private JSFile JSFile;

  @Before
  public void setUp() throws Exception {
    path = "/sample-script.js";
    filePath = Paths.get(this.getClass().getResource(path).toURI());
    JSFile = new JSFile(filePath);
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