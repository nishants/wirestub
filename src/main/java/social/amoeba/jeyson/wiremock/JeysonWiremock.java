package social.amoeba.jeyson.wiremock;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;
import social.amoeba.jeyson.Jeyson;
import social.amoeba.jeyson.Json;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
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
        response.withHeader("Content-Type", "application/json");
        response = response.withBody(parse(templatesPath, readFile(templatesPath, bodyFileName)));
      } catch (IOException e) {
        e.printStackTrace();
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      } catch (ScriptException e) {
        e.printStackTrace();
      } catch (URISyntaxException e) {
        e.printStackTrace();
      }
    }
    return response.build();
  }

  private String parse(String templatesPath, String template) throws URISyntaxException, NoSuchMethodException, ScriptException, IOException {
    template = Json.stringify(Json.parse(template, Map.class));
    Map compiled = new Jeyson(templatesPath).compile(new HashMap(), template);
    return new ObjectMapper().writeValueAsString(compiled);
  }

  private String readFile(String path, String filename) throws IOException {
    return Json.stringify(new ObjectMapper().readValue(new File(path, filename), Map.class).get("body"));
  }


  public String getName() {
    return "Jeyson";
  }
}