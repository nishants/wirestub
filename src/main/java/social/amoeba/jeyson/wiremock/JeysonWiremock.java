package social.amoeba.jeyson.wiremock;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;

import social.amoeba.jeyson.Expression;
import social.amoeba.jeyson.wiremock.request.RequestReader;
import social.amoeba.jeyson.wiremock.request.ResponseBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JeysonWiremock extends ResponseDefinitionTransformer {

  private ResponseBuilder responseBuilder;
  private final Map session = new HashMap();
  private final Expression expressions = new Expression();
  private Map config = new HashMap<>();

  @Override
  public ResponseDefinition transform(Request request,
                                      ResponseDefinition responseDefinition,
                                      FileSource files,
                                      Parameters parameters) {


    ResponseDefinitionBuilder builder = new ResponseDefinitionBuilder().like(responseDefinition);
      Map scope             = new HashMap<>();
      String templatesHome  = files.getPath(),
             templatePath   = responseDefinition.getBodyFileName();

    if (templatePath != null) {
      try {
        scope.put("request", RequestReader.read(request));
        scope.put("session", session);
        scope.put("config" , new HashMap(config));

        Parameters transformers = responseDefinition.getTransformerParameters();
        if(transformers != null && transformers.get("before") != null){
          String[] beforeBlock = ((List<String>) responseDefinition.getTransformerParameters().get("before")).toArray(new String[0]);
          expressions.eval(beforeBlock, scope);
        }

        if (responseBuilder == null) {
          responseBuilder = new ResponseBuilder(templatesHome);
        }

        byte[] responseBody = responseBuilder.render(scope, templatePath);

        builder = builder.withBody(responseBody);
        builder = builder.withHeaders(responseBuilder.header(templatePath));
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