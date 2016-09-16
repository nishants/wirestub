package social.amoeba.jeyson.wiremock.response;

import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.http.ResponseDefinition;
import social.amoeba.jeyson.Expression;

import javax.script.ScriptException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class BeforeBlock {
  private final Expression expressions = new Expression();


  public void run(ResponseDefinition responseDefinition, Map scope) throws URISyntaxException, NoSuchMethodException, IOException, ScriptException {
    Parameters transformers = responseDefinition.getTransformerParameters();
    if(transformers != null && transformers.get("before") != null){
      String[] beforeBlock = ((List<String>) responseDefinition.getTransformerParameters().get("before")).toArray(new String[0]);
      expressions.eval(beforeBlock, scope);
    }
  }
}
