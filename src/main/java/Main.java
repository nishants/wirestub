import com.github.tomakehurst.wiremock.standalone.WireMockServerRunner;
import social.amoeba.jeyson.wiremock.RuntimeArgs;

import javax.script.ScriptException;
import java.io.*;
import java.net.URISyntaxException;

public class Main {
  public static void main(String...args) throws InterruptedException, URISyntaxException, NoSuchMethodException, ScriptException, IOException {
    new WireMockServerRunner().run(RuntimeArgs.set(args));
  }
}
