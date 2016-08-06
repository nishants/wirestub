package social.amoeba.jeyson;

import javax.script.ScriptException;
import java.io.IOException;
import java.nio.file.Path;

public class TemplateEngine {
  private final JSFile script;

  public TemplateEngine(Path jsEngine, Path __files) throws IOException, ScriptException {
    script = new JSFile(jsEngine);
  }

  public String compile(String template) throws ScriptException, NoSuchMethodException {
    return script.execute( "compile", new CompileParam(template)).toString();
  }
}
