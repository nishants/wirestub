import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import social.amoeba.jeyson.wiremock.JeysonWiremock;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class Main {
  private static final WireMockConfiguration config =
      options()
          .extensions(new JeysonWiremock())
          .usingFilesUnderDirectory("/Users/dawn/Documents/projects/jeyson/src/main/resources")
          .port(8819);

  public static void main(String...hh) throws InterruptedException {
    final WireMockServer wireMockServer = new WireMockServer(config); //No-args constructor will start on config 8080, no HTTPS
    wireMockServer.start();

    Runtime.getRuntime().addShutdownHook(
        new Thread() {
          @Override
          public void run() {
            wireMockServer.stop();
          }
        });

    Thread.sleep(99999000);
    wireMockServer.shutdownServer();

  }
}
