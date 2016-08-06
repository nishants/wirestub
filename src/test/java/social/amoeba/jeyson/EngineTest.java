package social.amoeba.jeyson;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EngineTest {

  @Test
  public void shouldExecuteAGlobalMethod() throws Exception {
    String path     = "/sample-script.js";
    String function = "callMe";
    String args     = "some-arg";
    Path filePath   = Paths.get(this.getClass().getResource(path).toURI());

    Engine engine = new Engine(filePath);

    Object execute = engine.execute(function, args);

    assertThat(execute.toString(), is("you called me.."));
  }
}