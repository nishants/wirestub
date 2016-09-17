package social.amoeba;

import com.github.tomakehurst.wiremock.http.*;
import wiremock.com.fasterxml.jackson.core.JsonProcessingException;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestSupport {
  public Spec getSample(String path) throws URISyntaxException, IOException {
    String s = new String(Files.readAllBytes(Paths.get(this.getClass().getResource(path).toURI()).toAbsolutePath()));
    return new ObjectMapper().readValue(s, Spec.class);
  }
  public String absolute(String path) throws URISyntaxException, IOException {
    return Paths.get(this.getClass().getResource(path).toURI()).toAbsolutePath().toString();
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

  public static ResponseDefinition xmlResponseDefinition(File file) throws IOException {
    ResponseDefinition mocked = mock(ResponseDefinition.class);
    when(mocked.getBodyFileName()).thenReturn(file.getName());
    when(mocked.getHeaders()).thenReturn(headers("application/xml"));
    return mocked;
  }

  public static Request jsonRequestBody(String body){
    return createRequest("application/json", body);
  }

  public static Request xmlRequestBody(String body){
    return createRequest("application/xml", body);
  }

  public static File createTempFile(String bodyFileName, String contents) throws IOException {
    File file = new File(System.getProperty("java.io.tmpdir"), bodyFileName);
    if(bodyFileName.endsWith("json")){
      contents = contents.replaceAll("'", "\"");
    }
    file.createNewFile();
    FileWriter fileWriter = new FileWriter(file, false);
    fileWriter.write(contents);
    fileWriter.flush();
    return file;
  }

  private static Request createRequest(String mime,String body){
    Request request = mock(Request.class);


    RequestMethod method = mock(RequestMethod.class);

    when(method.getName()).thenReturn("PUT");
    when(request.getMethod()).thenReturn(method);

    when(request.getHeaders()).thenReturn(headers(mime));
    when(request.getUrl()).thenReturn("/some/fome/abs");
    when(request.getBody()).thenReturn(body.replaceAll("'", "\"").getBytes());
    return request;
  }

  public static void setURl(Request request ,String url){
    when(request.getUrl()).thenReturn(url);
  }

  public static Map toMap(Object arg){
    return (Map)arg;
  }

  private static HttpHeaders headers(String mime){
    return new HttpHeaders(new HttpHeader[]{new HttpHeader("Content-Type", mime),});
  }
}
