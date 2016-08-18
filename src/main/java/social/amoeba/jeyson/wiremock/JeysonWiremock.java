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
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JeysonWiremock extends ResponseDefinitionTransformer {

  private final Logger logger;

  public JeysonWiremock(){
    logger = LoggerFactory.getLogger(JeysonWiremock.class);
  }

  @Override
  public ResponseDefinition transform(Request request,
                                      ResponseDefinition responseDefinition,
                                      FileSource files,
                                      Parameters parameters) {

    String bodyFileName         = responseDefinition.getBodyFileName();
    String templatesPath        = files.getPath();
    Map requestBody             = null ;
    boolean isJsonFileResponse  = bodyFileName == null ? false : bodyFileName.endsWith(".json");

    ResponseDefinitionBuilder response =
        new ResponseDefinitionBuilder()
            .like(responseDefinition);

    if(isJsonFileResponse){
      try {
        requestBody = Json.parse(new String(request.getBody()), Map.class);
        response.withHeader("Content-Type", "application/json");
        response = response.withBody(parse(requestBody, templatesPath, readFile(templatesPath, bodyFileName)));
      } catch (Exception e) {
        String errorMessage = "************* Jeyson Error *******************" + File.separator;
        errorMessage += "bodyFile : :bodyFile, templatesPath :  :templatesPath" + File.separator;;
        errorMessage += e.getMessage();
        logger.error(errorMessage);
        e.printStackTrace();
      }
    }
    return response.build();
  }

  private String parse(Map requestBody, String templatesPath, String template) throws URISyntaxException, NoSuchMethodException, ScriptException, IOException {
    template = Json.stringify(Json.parse(template, Map.class));
    HashMap scope = new HashMap();
    HashMap reqeust = new HashMap();
    reqeust.put("body", requestBody);
    scope.put("request", reqeust);

    Map compiled = new Jeyson(templatesPath).compile(scope, template);
    return new ObjectMapper().writeValueAsString(compiled);
  }

  private String readFile(String path, String filename) throws IOException {
    return Json.stringify(new ObjectMapper().readValue(new File(path, filename), Map.class).get("body"));
  }


  public String getName() {
    return "Jeyson";
  }
}