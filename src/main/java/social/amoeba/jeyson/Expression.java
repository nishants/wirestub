package social.amoeba.jeyson;

import javax.script.ScriptException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class Expression {

  public Object eval(String expression, Map scope) throws ScriptException, NoSuchMethodException, IOException, URISyntaxException {
    scope.put("expression", expression);
    String template = "{'expression' : '{{<expression>}}'}".replaceAll("'", "\"").replace("<expression>", expression);
    return new Jeyson("/").compile(scope, template).get("expression");
  }

  public Object eval(String[] before, Map scope) throws URISyntaxException, NoSuchMethodException, IOException, ScriptException {
   if(before != null){
     String block = "";
     for(String line : before){
       block += line +" ;";
     }
     return eval(block, scope);
   }
    return null;
  }
}
