package social.amoeba.jeyson;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.ScriptException;
import java.io.IOException;
import java.nio.file.Path;

public class TemplateEngine {
  private final ScriptObjectMirror engine;

  public TemplateEngine(Path jsEngine, Path __files) throws IOException, ScriptException, NoSuchMethodException {
    JSFile script = new JSFile(jsEngine);
    Object jsObject = script.execute("jeyson", new CompileParam(__files));
    engine = (ScriptObjectMirror) jsObject;
  }

  public String compile(String template) throws ScriptException, NoSuchMethodException {
    return ((ScriptObjectMirror)engine.get("compile")).call(engine, template).toString();
  }
}
