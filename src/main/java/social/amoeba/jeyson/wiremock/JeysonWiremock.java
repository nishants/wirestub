package social.amoeba.jeyson.wiremock;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.http.Request;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;

import social.amoeba.jeyson.Expression;
import social.amoeba.jeyson.ResponseScope;
import social.amoeba.jeyson.wiremock.request.RequestReader;
import social.amoeba.jeyson.wiremock.response.ResponseWriter;

import javax.script.ScriptException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JeysonWiremock extends ResponseDefinitionTransformer {

  private ResponseWriter responseWriter;
  private final Map session = new HashMap();
  private final Expression expressions = new Expression();
  private Map config = new HashMap<>();

  @Override
  public ResponseDefinition transform(Request request,
                                      ResponseDefinition responseDefinition,
                                      FileSource files,
                                      Parameters parameters) {


    ResponseDefinitionBuilder builder = new ResponseDefinitionBuilder().like(responseDefinition);
      String templatesHome  = files.getPath(),
             templatePath   = responseDefinition.getBodyFileName();

    if (templatePath != null) {
      try {
        Map scope = new ResponseScope(session, new HashMap(config), RequestReader.read(request));
        before(responseDefinition, scope);

        if (responseWriter == null) {
          responseWriter = new ResponseWriter(templatesHome);
        }

        byte[] responseBody = responseWriter.render(scope, templatePath);

        builder = builder.withBody(responseBody);
        builder = builder.withHeaders(responseWriter.header(templatePath));
      } catch (Exception e) {
        String errorMessage = "************* Jeyson Error *******************" + System.getProperty("line.separator");
        errorMessage += e.getMessage() + System.getProperty("line.separator");
        System.err.println(errorMessage);
        e.printStackTrace();
      }
    }

    return builder.build();
  }

  private void before(ResponseDefinition responseDefinition, Map scope) throws URISyntaxException, NoSuchMethodException, IOException, ScriptException {
    Parameters transformers = responseDefinition.getTransformerParameters();
    if(transformers != null && transformers.get("before") != null){
      String[] beforeBlock = ((List<String>) responseDefinition.getTransformerParameters().get("before")).toArray(new String[0]);
      expressions.eval(beforeBlock, scope);
    }
  }


  public String getName() {
    return "Jeyson";
  }
}