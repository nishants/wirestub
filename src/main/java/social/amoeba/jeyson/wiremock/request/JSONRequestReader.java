package social.amoeba.jeyson.wiremock.request;

import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class JSONRequestReader extends RequestReader{
  private final byte[] body;

  public JSONRequestReader(byte[] bodyAsString) {
    body = bodyAsString;
  }

  @Override
  public Map parse() throws IOException {
    return new ObjectMapper().readValue(body, Map.class);
  }
}
