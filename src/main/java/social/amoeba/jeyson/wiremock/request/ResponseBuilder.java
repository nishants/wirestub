package social.amoeba.jeyson.wiremock.request;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public abstract class ResponseBuilder {
  public static Map render(
      Map scope,
      File templatesPath) throws IOException {

    boolean isJSON = templatesPath.getName().toLowerCase().endsWith(".json"),
            isXML   =templatesPath.getName().toLowerCase().endsWith(".xml");

    return isXML ? XML.parse(templatesPath) : isJSON ? JSON.parse(templatesPath) : null;
  }
}
