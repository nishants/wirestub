package social.amoeba.support;

import social.amoeba.jeyson.Json;
import wiremock.com.fasterxml.jackson.core.JsonProcessingException;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScriptSpec {

  public static ArrayList<String> findSpecFiles(String path) throws URISyntaxException {
    ArrayList<String> specFiles   = new ArrayList();
    path = ScriptSpec.class.getResource(path).getPath();

    for(File file : new File(path).listFiles()){
      if(file.isFile() && file.getName().endsWith("spec.json")){
        specFiles.add(file.getAbsolutePath());
      }
    }
    return specFiles;
  }

  public static List<Spec> getSpecs() throws URISyntaxException, IOException {
    List<Spec> specs = new ArrayList<>();

    for(String path : findSpecFiles("/specs")){
      String specJson = new String(Files.readAllBytes(Paths.get(path).toAbsolutePath()));
      Spec spec = Json.parse(specJson, Spec.class);
      specs.add(spec);
    }
    return specs;
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
