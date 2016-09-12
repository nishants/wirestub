package social.amoeba.jeyson.wiremock.request;

import social.amoeba.jeyson.Jeyson;
import social.amoeba.jeyson.Json;

import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Scanner;

public class ResponseBuilder {
  public static byte[] readTemplate(
      File file) throws IOException {
    return new Scanner(file).useDelimiter("\\Z").next().getBytes();
  }

  public static byte[] render(Map scope, String templatesHome, String relativePath) throws IOException, NoSuchMethodException, ScriptException, URISyntaxException {
    File file = new File(templatesHome, relativePath);
    boolean isJSON = file.getName().toLowerCase().endsWith(".json"),
            isXML   =file.getName().toLowerCase().endsWith(".xml");

    byte[] template = readTemplate(file);

    Jeyson jeyson = new Jeyson(templatesHome);
    Map compile = jeyson.compile(scope, new String(template));
    return isJSON ? Json.stringify(compile).getBytes()  : template;
  }
}
