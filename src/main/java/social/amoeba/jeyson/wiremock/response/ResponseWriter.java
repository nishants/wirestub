package social.amoeba.jeyson.wiremock.response;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.http.HttpHeader;
import com.github.tomakehurst.wiremock.http.HttpHeaders;
import social.amoeba.jeyson.Jeyson;

import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class ResponseWriter {
  private final String templatesHome;
  private final Jeyson jeyson;

  private final JSONResponseBuilder jsonResponseBuilder;
  private final XMLResponseBuilder  xmlResponseBuilder;

  public ResponseWriter(String templatesHome) throws URISyntaxException, NoSuchMethodException, ScriptException, IOException {
    this.templatesHome = templatesHome;
    jeyson = new Jeyson(templatesHome);
    jsonResponseBuilder = new JSONResponseBuilder(templatesHome, jeyson);
    xmlResponseBuilder = new XMLResponseBuilder(templatesHome, jeyson);
  }

  public ResponseDefinitionBuilder writeTo(ResponseDefinitionBuilder builder, Map scope, String templatePath) throws URISyntaxException, NoSuchMethodException, ScriptException, IOException {
    File file = new File(templatesHome, templatePath);
    boolean isJSON = file.getName().toLowerCase().endsWith(".json"),
            isXML  = file.getName().toLowerCase().endsWith(".xml");

    byte[] body = isJSON ? jsonResponseBuilder.render(scope, templatePath)  : isXML ? xmlResponseBuilder.render(scope, templatePath) : null;

    builder = builder.withBody(body);
    String mime = isJSON ? "application/json" : isXML ? "application/xml" : null;
    builder = builder.withHeaders(new HttpHeaders(new HttpHeader[]{new HttpHeader("Content-Type", mime),}));

    return builder;
  }
}
