package social.amoeba;

import wiremock.com.fasterxml.jackson.core.JsonProcessingException;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class TestSupport {
  public Spec getSample(String path) throws URISyntaxException, IOException {
    String s = new String(Files.readAllBytes(Paths.get(this.getClass().getResource(path).toURI())));
    return new ObjectMapper().readValue(s, Spec.class);
  }
  public static class Spec{
    public void setTemplate(Map template) throws JsonProcessingException {
      this.template = new ObjectMapper().writeValueAsString(template);
    }
    public void setExpected(Map json) throws JsonProcessingException {
      this.expected = new ObjectMapper().writeValueAsString(json);
    }

    public Map config;
    public String template;
    public Map    scope;
    public String expected;
  }
}
