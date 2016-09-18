package social.amoeba.jeyson.wiremock;

import org.junit.Test;
import social.amoeba.TestSupport;

import javax.script.ScriptException;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RuntimeArgsTest {

  @Test
  public void shouldAddRootDirIfNotProvided() throws InterruptedException, NoSuchMethodException, ScriptException, IOException, URISyntaxException {
    String[] args     = {"--verbose","--port", "5123"},
             expected = {"--verbose", "--port", "5123", "--extensions", "social.amoeba.jeyson.wiremock.JeysonWiremock", "--root-dir", TestSupport.jarRooot()},
             actual   = RuntimeArgs.set(args);

    assertThat(actual, is(expected));

  }
  @Test
  public void shouldNotSetRootDirIfProvided() throws InterruptedException, NoSuchMethodException, ScriptException, IOException, URISyntaxException {
    String[] args     = {"--verbose","--port", "5123", "--root-dir", "some-path"},
             expected = {"--verbose","--port", "5123", "--root-dir", "some-path", "--extensions", "social.amoeba.jeyson.wiremock.JeysonWiremock"},
             actual   = RuntimeArgs.set(args);

    assertThat(actual, is(expected));

  }
}