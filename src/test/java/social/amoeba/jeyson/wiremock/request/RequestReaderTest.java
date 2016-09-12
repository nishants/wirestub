package social.amoeba.jeyson.wiremock.request;

import com.github.tomakehurst.wiremock.http.HttpHeader;
import com.github.tomakehurst.wiremock.http.HttpHeaders;
import com.github.tomakehurst.wiremock.http.Request;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class RequestReaderTest {
  @Test
  public void parseJsonRequest() throws IOException {
    Map actual        = RequestReader.read(jsonRequestBody("{'body' : {'message' : 'hello'}}")),
        expected      = new HashMap<>(),
        expectedBody  = new HashMap<>();

    expectedBody.put("message", "hello");
    expected.put("body", expectedBody);

    assertThat(actual, is(expected));
  }

  private Request jsonRequestBody(String body){
    Request mocked = mock(Request.class);

    HttpHeader contentTypeHeader = new HttpHeader("Content-Type", "application/json");
    HttpHeaders headers = new HttpHeaders(new HttpHeader[]{contentTypeHeader});

    when(mocked.getHeaders()).thenReturn(headers);
    when(mocked.getBody()).thenReturn(body.replaceAll("'", "\"").getBytes());
    return mocked;
  }
}