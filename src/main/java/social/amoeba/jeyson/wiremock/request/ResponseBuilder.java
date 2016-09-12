package social.amoeba.jeyson.wiremock.request;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public abstract class ResponseBuilder {
  public static String render(
      Map scope,
      File teplatePath) throws IOException {

    boolean isJSON = teplatePath.getName().toLowerCase().endsWith(".json"),
            isXML   =teplatePath.getName().toLowerCase().endsWith(".xml");

    return new Scanner(teplatePath).useDelimiter("\\Z").next();
  }
}
