package social.amoeba.jeyson;

import org.junit.Test;

import javax.script.ScriptException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ExpressionTest {

  @Test
  public void eval() throws ScriptException, IOException, URISyntaxException, NoSuchMethodException {
    HashMap<Object, Object> scope = new HashMap<>();
    scope.put("message", "hello");
    Object actual = new Expression().eval("message + ' world !'", scope);
    assertThat(actual, is("hello world !"));
  }

}