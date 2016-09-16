package social.amoeba.jeyson.wiremock;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;
import social.amoeba.jeyson.ResponseScope;
import social.amoeba.jeyson.wiremock.request.RequestReader;
import social.amoeba.jeyson.wiremock.response.BeforeBlock;
import social.amoeba.jeyson.wiremock.response.ResponseWriter;

import java.util.HashMap;
import java.util.Map;


public class JeysoneWiremockException extends ResponseDefinitionTransformer {

  private ResponseWriter responseWriter;
  private final Map session = new HashMap();
  private Map config = new HashMap<>();

  @Override
  public ResponseDefinition transform(Request request,
                                      ResponseDefinition responseDefinition,
                                      FileSource files,
                                      Parameters parameters) {


    ResponseDefinitionBuilder builder = new ResponseDefinitionBuilder().like(responseDefinition);
    String templatePath   = responseDefinition.getBodyFileName();

    if (templatePath != null) {
      try {
        Map scope = new ResponseScope(session, new HashMap(this.config), RequestReader.read(request));
        responseWriter = responseWriter == null ? new ResponseWriter(files.getPath()) : responseWriter;
        new BeforeBlock().run(responseDefinition, scope);
        responseWriter.writeTo(builder, scope, templatePath);
      } catch (Exception e) {
        String errorMessage = "************* Jeyson Error *******************" + System.getProperty("line.separator");
        errorMessage += e.getMessage() + System.getProperty("line.separator");
        System.err.println(errorMessage);
        e.printStackTrace();
      }
    }

    return builder.build();
  }

  public String getName() {
    return "Jeyson";
  }
}