package social.amoeba;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;

public class Jeyson extends ResponseDefinitionTransformer {

  @Override
  public ResponseDefinition transform(Request request,
                                      ResponseDefinition responseDefinition,
                                      FileSource files,
                                      Parameters parameters) {
    boolean isJsonFileResonse = responseDefinition.getBodyFileName().endsWith(".json");

    return new ResponseDefinitionBuilder().like(responseDefinition)
//        .withHeader("MyHeader", "Transformed")
//        .withStatus(200)
        .withBody(parse(readFile(files.getPath(), responseDefinition.getBodyFileName())))
        .build();
  }

  private String parse(String template) {
    return template;
  }

  private String readFile(String path, String filename) {
    return "Transformed body";
  }


  public String getName() {
    return "Jeyson Parser";
  }
}