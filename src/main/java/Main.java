import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import social.amoeba.jeyson.Jeyson;
import social.amoeba.jeyson.wiremock.JeysonWiremock;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import javax.script.ScriptException;
import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class Main {
  private static final WireMockConfiguration config =
      options()
          .extensions(new JeysonWiremock())
          .usingFilesUnderDirectory("/Users/dawn/Documents/projects/jeyson-java/src/main/resources")
          .port(8819);

  public static void main(String...hh) throws InterruptedException, URISyntaxException, NoSuchMethodException, ScriptException, IOException {
    Jeyson jeyson = new Jeyson("/Users/dawn/Documents/projects/jeyson-java/src/test/resources/templates/shared");
    Map compile = jeyson.compile(new HashMap(), "{\"sum\": \"{{2 + 2}}\"}");

    System.out.println(new ObjectMapper().writeValueAsString(compile));

    final WireMockServer wireMockServer = new WireMockServer(config); //No-args constructor will start on config 8080, no HTTPS
    wireMockServer.start();

    Runtime.getRuntime().addShutdownHook(
        new Thread() {
          @Override
          public void run() {
            wireMockServer.stop();
          }
        });
  }
}
