package social.amoeba.jeyson.wiremock.response;

import com.github.tomakehurst.wiremock.http.HttpHeader;
import com.github.tomakehurst.wiremock.http.HttpHeaders;
import social.amoeba.jeyson.Jeyson;
import social.amoeba.jeyson.Json;

import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Scanner;

public class XMLResponseBuilder {
  private final String templatesHome;
  private final Jeyson jeyson;

  public XMLResponseBuilder(String templatesHome, Jeyson jeyson) throws URISyntaxException, NoSuchMethodException, ScriptException, IOException {
    this.templatesHome = templatesHome;
    this.jeyson = new Jeyson(templatesHome);
  }

  public byte[] readTemplate(
      File file) throws IOException {
    return new Scanner(file).useDelimiter("\\Z").next().getBytes();
  }

  public byte[] render(Map scope, String relativePath) throws IOException, ScriptException, NoSuchMethodException {
    return readTemplate(new File(templatesHome, relativePath));
  }

  public HttpHeaders header(String templatePath) {
    boolean isJSON = templatePath.toLowerCase().endsWith(".json"),
            isXML  = templatePath.toLowerCase().endsWith(".xml");

    String mime = isJSON ? "application/json" : isXML ? "application/xml" : null;

    return new HttpHeaders(new HttpHeader[]{new HttpHeader("Content-Type", mime),});
  }
}
