package social.amoeba.jeyson;

import jdk.nashorn.api.scripting.ScriptObjectMirror;


import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

public class Jeyson {
  private final ScriptObjectMirror engine;

  public Jeyson(String tempatesPath) throws IOException, ScriptException, NoSuchMethodException, URISyntaxException {
    try (BufferedReader buffer = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/jeyson.js")))) {
      String jsScript = buffer.lines().collect(Collectors.joining("\n"));
      Script script = new Script(jsScript);
      Object jsObject = script.execute("jeyson", new JeysonConfig(Paths.get(tempatesPath)));
      engine = (ScriptObjectMirror) jsObject;
    }
  }

  public Map compile(Map scope, String template) throws ScriptException, NoSuchMethodException, IOException {
    return (Map)((ScriptObjectMirror)engine.get("compile")).call(engine, scope, template);
  }
}
