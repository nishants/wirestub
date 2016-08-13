package social.amoeba.jeyson;

import wiremock.com.fasterxml.jackson.core.JsonParseException;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class Json {
  public static <T> T parse(String json, Class<T> type) throws IOException {
    return new ObjectMapper().readValue(json, type);
  }
  public static String stringify(Object object) throws IOException {
    return new ObjectMapper().writeValueAsString(object);
  }
}
