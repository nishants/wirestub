package social.amoeba.jeyson.wiremock.response;

import social.amoeba.jeyson.Expression;
import social.amoeba.jeyson.Jeyson;

import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLResponseBuilder {
  private final String templatesHome;

  private static final String EXPRESSION_REGEX = "\\{\\{([^\\}]+)\\}\\}";

  public XMLResponseBuilder(String templatesHome) throws URISyntaxException, NoSuchMethodException, ScriptException, IOException {
    this.templatesHome = templatesHome;
  }

  public byte[] readTemplate(
      File file) throws IOException {
    return new Scanner(file).useDelimiter("\\Z").next().getBytes();
  }

  public byte[] render(Map scope, String relativePath) throws IOException, ScriptException, NoSuchMethodException, URISyntaxException {
    byte[] fileContents = readTemplate(new File(templatesHome, relativePath));

    List<String> expressions = new ArrayList<>();
    String xml = new String(fileContents);

    Matcher matcher = Pattern.compile(EXPRESSION_REGEX).matcher(xml);
    while (matcher.find()) {
      expressions.add(matcher.group());
    }

    for(String expression : expressions){
      String expr = expression.replace("{{", "").replace("}}", "");
      try {
        xml = xml.replace(expression, new Expression().eval(expr, scope).toString());
      }catch (Exception e){
        xml = xml.replace(expression, "Error in expression : '" + expr +" - " + e.getMessage());
      }
    }

    return xml.getBytes();
  }

}
