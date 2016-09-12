package social.amoeba.jeyson.wiremock.request;

import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class JSON {
  public static Map parse(byte[] body) throws IOException {
    return new ObjectMapper().readValue(body, Map.class);
  }

  public static Map parse(File file) throws IOException {
    return parse(new Scanner(file).useDelimiter("\\Z").next().getBytes());
  }

  public static Map parseJSON(String body) throws IOException {
    body = body.replaceAll("'", "\"");
    return parse(body.getBytes());
  }
}
