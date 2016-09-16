package social.amoeba.jeyson;

import javax.script.ScriptException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

public class Expression {

  public Object eval(String expression, HashMap scope) throws ScriptException, NoSuchMethodException, IOException, URISyntaxException {
    scope.put("expression", expression);
    String template = "{'expression' : '{{<expression>}}'}".replaceAll("'", "\"").replace("<expression>", expression);
    return new Jeyson("/").compile(scope, template).get("expression");
  }
}
