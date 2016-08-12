package social.amoeba.jeyson;

import wiremock.com.fasterxml.jackson.core.JsonParseException;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;

public class CompileParam {
  private final Path __files;

  public CompileParam(Path __files, String template) {
    this.__files = __files;
  }

  public CompileParam(Path __files) {
    this.__files = __files;
  }

  public String getTemplate(String path) throws IOException {
    return new String(Files.readAllBytes(Paths.get(__files.toString(), path)));
  }

  public Map parseJson(String json) throws IOException {
    return new ObjectMapper().readValue(json, Map.class);
  }

  public String stringify(Map json) throws IOException {
    return new ObjectMapper().writeValueAsString(json);
  }

  public Map deleteField(String field, Map hash) throws IOException {
    hash.remove(field);
    return hash;
  }

  public void log(Object...params) throws IOException {
    System.out.println(Arrays.toString(params));
  }

}
