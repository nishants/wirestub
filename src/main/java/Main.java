import social.amoeba.jeyson.Jeyson;

import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

  public static void main(String...hh) throws InterruptedException, URISyntaxException, NoSuchMethodException, ScriptException, IOException {
    Path path = Paths.get("/Users/dawn/Documents/projects/jeyson-java/src/main/resources/jeyson.js");
    System.out.println("the jar is ready..." + path);
    System.out.println(new File("/Users/dawn/Documents/projects/jeyson-java/src/main/resources/jeyson.js").exists());
    new Jeyson("", "");

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
