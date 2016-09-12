package social.amoeba.jeyson.wiremock.request;

import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class JSON {
  public static Map parse(byte[] body) throws IOException {
    return new ObjectMapper().readValue(body, Map.class);
  }
}
