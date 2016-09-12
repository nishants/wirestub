package social.amoeba.jeyson.wiremock.request;

import com.github.tomakehurst.wiremock.http.ResponseDefinition;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public abstract class ResponseBuilder {
  public static Map render(
      Map scope,
      ResponseDefinition responseDefinition,
      File templatesPath) throws IOException {


    File bodyFile = new File(templatesPath, responseDefinition.getBodyFileName());
    return XML.parse(bodyFile);
  }
}
