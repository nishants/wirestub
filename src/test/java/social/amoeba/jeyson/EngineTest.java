package social.amoeba.jeyson;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EngineTest {

  private String path;
  private Arguments args;
  private Path filePath;
  private Engine engine;

  @Before
  public void setUp() throws Exception {
    path = "/sample-script.js";
    filePath = Paths.get(this.getClass().getResource(path).toURI());
    engine = new Engine(filePath);
  }

  @Test
  public void shouldPassArgumentToScript() throws Exception {
    Object execute = engine.execute("returnArgument", new Arguments("some-arg"));
    assertThat(execute.toString(), is("some-arg"));
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