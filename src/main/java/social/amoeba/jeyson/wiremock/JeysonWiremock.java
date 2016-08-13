package social.amoeba.jeyson.wiremock;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;

public class JeysonWiremock extends ResponseDefinitionTransformer {

  @Override
  public ResponseDefinition transform(Request request,
                                      ResponseDefinition responseDefinition,
                                      FileSource files,
                                      Parameters parameters) {
    boolean isJsonFileResponse = responseDefinition.getBodyFileName().endsWith(".json");
    String controllerName      = responseDefinition.getTransformerParameters().get("controller").toString();
    String templatesPath       = files.getPath();

    return new ResponseDefinitionBuilder()
        .like(responseDefinition)
        .withBody(parse(readFile(templatesPath, responseDefinition.getBodyFileName())))
        .build();
  }

  private String parse(String template) {
    return template;
  }

  private String readFile(String path, String filename) {
    return "Transformed body";
  }


  public String getName() {
    return "Jeyson";
  }
}