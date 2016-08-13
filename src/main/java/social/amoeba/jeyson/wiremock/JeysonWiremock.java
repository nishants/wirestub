package social.amoeba.jeyson.wiremock;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class JeysonWiremock extends ResponseDefinitionTransformer {

  @Override
  public ResponseDefinition transform(Request request,
                                      ResponseDefinition responseDefinition,
                                      FileSource files,
                                      Parameters parameters) {
    String bodyFileName        = responseDefinition.getBodyFileName();
    boolean isJsonFileResponse = bodyFileName == null ? false : bodyFileName.endsWith(".json");
    String templatesPath       = files.getPath();

    ResponseDefinitionBuilder response = new ResponseDefinitionBuilder().like(responseDefinition);

    if(isJsonFileResponse){
      try {
        response = response.withBody(parse(readFile(templatesPath, bodyFileName)));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return response.build();
  }

  private String parse(String template) {
    return template;
  }

  private String readFile(String path, String filename) throws IOException {
    return new ObjectMapper().readValue(new File(path, filename), Map.class).get("body").toString();
  }


  public String getName() {
    return "Jeyson";
  }
}