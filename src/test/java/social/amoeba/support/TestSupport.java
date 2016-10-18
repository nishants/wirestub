package social.amoeba.support;

import com.github.tomakehurst.wiremock.http.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestSupport {
  public String absolute(String path) throws URISyntaxException, IOException {
    return Paths.get(this.getClass().getResource(path).toURI()).toAbsolutePath().toString();
  }

  public static String jarRooot() {
    return Paths.get("").toAbsolutePath().toString();
  }

  public static Request jsonRequestBody(String body){
    return createRequest("application/json", body);
  }

  public static Request xmlRequestBody(String body){
    return createRequest("application/xml", body);
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
