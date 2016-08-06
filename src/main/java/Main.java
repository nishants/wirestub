import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class Main {
  private static final WireMockConfiguration config =
      options()
//          .extensions("com.mycorp.ExtensionOne")
          .port(8811);

  public static void main(String...hh){
    final WireMockServer wireMockServer = new WireMockServer(config); //No-args constructor will start on config 8080, no HTTPS
    wireMockServer.start();

    Runtime.getRuntime().addShutdownHook(
        new Thread() {
          @Override
          public void run() {
            wireMockServer.stop();
          }
        });


    wireMockServer.stop();
  }
}
