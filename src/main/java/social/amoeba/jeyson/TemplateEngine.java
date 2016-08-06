package social.amoeba.jeyson;

import javax.script.ScriptException;
import java.io.IOException;
import java.nio.file.Path;

public class TemplateEngine {
  private final JSFile script;
  private final Path __files;

  public TemplateEngine(Path jsEngine, Path __files) throws IOException, ScriptException, NoSuchMethodException {
    this.__files = __files;
    script = new JSFile(jsEngine);
    script.execute( "init", new CompileParam(__files));
  }

  public String compile(String template) throws ScriptException, NoSuchMethodException {
    return script.execute( "compile", template).toString();
  }
}
