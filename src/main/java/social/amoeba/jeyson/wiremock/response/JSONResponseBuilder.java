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

public class JSONResponseBuilder {
  private final String templatesHome;
  private final Jeyson jeyson;

  public JSONResponseBuilder(String templatesHome, Jeyson jeyson) throws IOException, ScriptException, NoSuchMethodException {
    this.templatesHome = templatesHome;
    this.jeyson = jeyson;
  }

  protected static byte[] readTemplate(File file) throws IOException {
    return new Scanner(file).useDelimiter("\\Z").next().getBytes();
  }

  public byte[] render(Map scope, String relativePath) throws IOException, ScriptException, NoSuchMethodException {
    byte[] template = readTemplate(new File(templatesHome, relativePath));
    Map compiled = jeyson.compile(scope, new String(template));
    return Json.stringify(compiled).getBytes();
  }

}
