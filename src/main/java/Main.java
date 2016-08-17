import com.github.tomakehurst.wiremock.WireMockServer;
import social.amoeba.jeyson.wiremock.JeysonWiremock;

import javax.script.ScriptException;
import java.io.*;
import java.net.URISyntaxException;


import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class Main {
  public static void main(String...args) throws InterruptedException, URISyntaxException, NoSuchMethodException, ScriptException, IOException {

    int port = 8819;
    String portFlag = "--port";
    for(int i = 0 ; i < args.length; i++ ){
      if(args[i] == portFlag){
        port = Integer.parseInt(args[i+1]);
      }
    }
    Main.class.getProtectionDomain().getCodeSource().getLocation().getFile();
    String jarPath = Main.class.getProtectionDomain().getCodeSource().getLocation().getFile(),
           dir     = new File(jarPath).getParent();

    final WireMockServer wireMockServer = new WireMockServer(options()
        .extensions(new JeysonWiremock())
        .usingFilesUnderDirectory(dir)
        .port(port)); //No-args constructor will start on config 8080, no HTTPS

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
