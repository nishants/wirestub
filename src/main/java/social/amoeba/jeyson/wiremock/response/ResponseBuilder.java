package social.amoeba.jeyson.wiremock.response;

import com.github.tomakehurst.wiremock.http.HttpHeader;
import com.github.tomakehurst.wiremock.http.HttpHeaders;
import social.amoeba.jeyson.Expression;
import social.amoeba.jeyson.Jeyson;
import social.amoeba.jeyson.Json;

import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Scanner;

public class ResponseBuilder {
  private final String templatesHome;
  private final Jeyson jeyson;
  private final Expression expressions = new Expression();


  public ResponseBuilder(String templatesHome) throws URISyntaxException, NoSuchMethodException, ScriptException, IOException {
    this.templatesHome = templatesHome;
    jeyson = new Jeyson(templatesHome);
  }

  public byte[] readTemplate(
      File file) throws IOException {
    return new Scanner(file).useDelimiter("\\Z").next().getBytes();
  }

  public byte[] render(Map scope, String relativePath) throws IOException, ScriptException, NoSuchMethodException {
    File file = new File(templatesHome, relativePath);
    boolean isJSON = file.getName().toLowerCase().endsWith(".json"),
            isXML   =file.getName().toLowerCase().endsWith(".xml");

    byte[] template = readTemplate(file);

    return isJSON ? Json.stringify(jeyson.compile(scope, new String(template))).getBytes()  : template;
  }

  public HttpHeaders header(String templatePath) {
    boolean isJSON = templatePath.toLowerCase().endsWith(".json"),
            isXML  = templatePath.toLowerCase().endsWith(".xml");

    String mime = isJSON ? "application/json" : isXML ? "application/xml" : null;

    return new HttpHeaders(new HttpHeader[]{new HttpHeader("Content-Type", mime),});
  }
}
