package social.amoeba.jeyson;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


public class JSFile {
  private final Invocable scriptEnginge;

  public JSFile(Path path) throws IOException, ScriptException {
    ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
    engine.eval(Files.newBufferedReader(path, StandardCharsets.UTF_8));
    this.scriptEnginge = (Invocable) engine;
  }

  public Object execute(String function, Object params) throws ScriptException, NoSuchMethodException {
    return scriptEnginge.invokeFunction(function, params);
  }

}
