package social.amoeba.jeyson.wiremock.request;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public abstract class ResponseProcessor {
  public static Map render(
      Map scope,
      ResponseDefinition responseDefinition,
      File templatesPath) throws IOException {

    File templateFile = new File(templatesPath, responseDefinition.getBodyFileName());

    String template = new Scanner(templateFile).useDelimiter("\\Z").next();
    return new XmlMapper().readValue(String.format("<response>%s</response>", template), Map.class);
  }
}
