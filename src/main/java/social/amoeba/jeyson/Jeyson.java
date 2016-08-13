package social.amoeba.jeyson;

import jdk.nashorn.api.scripting.ScriptObjectMirror;


import javax.script.ScriptException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Jeyson {
  private final ScriptObjectMirror engine;

  public Jeyson(String jeysonJsPath, String tempatesPath) throws IOException, ScriptException, NoSuchMethodException, URISyntaxException {
    JSFile script = new JSFile(jeysonJsPath);
    Object jsObject = script.execute("jeyson", new CompileParam(Paths.get(tempatesPath)));
    engine = (ScriptObjectMirror) jsObject;
  }

  public Map compile(Map scope, String template) throws ScriptException, NoSuchMethodException, IOException {
    return (Map)((ScriptObjectMirror)engine.get("compile")).call(engine, scope, template);
  }
}
