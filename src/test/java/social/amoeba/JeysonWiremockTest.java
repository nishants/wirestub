package social.amoeba;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit.WireMockClassRule;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Rule;
import org.junit.Test;
import social.amoeba.jeyson.wiremock.JeysonWiremock;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.hamcrest.MatcherAssert.assertThat;

public class JeysonWiremockTest {
  private static final int port = 8811;
  private final WireMockConfiguration config =
      options()
          .extensions(new JeysonWiremock())
          .port(port);

  @Rule
  public WireMockClassRule wireMockRule = new WireMockClassRule(config);

  @Test
  public void exactUrlOnly() {
    stubFor(get(urlEqualTo("/some/thing"))
        .willReturn(aResponse()
            .withHeader("Content-Type", "text/plain")
            .withBody("Hello world!")));

  }
}