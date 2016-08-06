package social.amoeba;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class JeysonTest {
  private static final int port = 8811;
  private final WireMockConfiguration config =
      options()
//          .extensions("com.mycorp.ExtensionOne")
          .port(port);


  @Test
  public void exampleTest() {
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