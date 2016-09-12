package social.amoeba.jeyson.wiremock;

import org.junit.Test;

import javax.script.ScriptException;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RuntimeArgsTest {

  @Test
  public void addProperties() throws InterruptedException, NoSuchMethodException, ScriptException, IOException, URISyntaxException {
    String[] args     = {"--verbose","--port", "5123"},
             expected = {"--verbose","--port", "5123", "--extensions", "social.amoeba.jeyson.wiremock.JeysonWiremock"},
             actual   = RuntimeArgs.like(args);

    assertThat(actual, is(expected));

  }
}