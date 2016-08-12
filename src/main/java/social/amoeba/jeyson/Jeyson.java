package social.amoeba.jeyson;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import javax.script.ScriptException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.Map;

public class Jeyson {
  private final ScriptObjectMirror engine;
  private final String jsEngine = "/jeyson.js";

  public Jeyson(Path __files) throws IOException, ScriptException, NoSuchMethodException, URISyntaxException {
    JSFile script = new JSFile(jsEngine);
    Object jsObject = script.execute("jeyson", new CompileParam(__files));
    engine = (ScriptObjectMirror) jsObject;
  }

  public String compile(Map scope, String template) throws ScriptException, NoSuchMethodException {
    return ((ScriptObjectMirror)engine.get("compile")).call(engine, scope, template).toString();
  }
}
