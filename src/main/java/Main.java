import social.amoeba.jeyson.Jeyson;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import javax.script.ScriptException;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {

  public static void main(String...hh) throws InterruptedException, URISyntaxException, NoSuchMethodException, ScriptException, IOException {
    Path path = Paths.get("/Users/dawn/Documents/projects/jeyson-java/src/main/resources/jeyson.js");
    System.out.println("the jar is ready..." + path);



    Jeyson jeyson = new Jeyson("/Users/dawn/Documents/projects/jeyson-java/src/test/resources/templates/shared");
    Map compile = jeyson.compile(new HashMap(), "{\"sum\": \"{{2 + 2}}\"}");

    System.out.println(new ObjectMapper().writeValueAsString(compile));

//    final WireMockServer wireMockServer = new WireMockServer(config); //No-args constructor will start on config 8080, no HTTPS
//    wireMockServer.start();
//
//    Runtime.getRuntime().addShutdownHook(
//        new Thread() {
//          @Override
//          public void run() {
//            wireMockServer.stop();
//          }
//        });
//
//    Thread.sleep(99999000);
//    wireMockServer.shutdownServer();

  }
}
