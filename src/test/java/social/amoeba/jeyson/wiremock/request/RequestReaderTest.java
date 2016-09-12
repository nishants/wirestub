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
import static social.amoeba.TestSupport.jsonRequestBody;
import static social.amoeba.TestSupport.xmlRequestBody;

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

  @Test
  public void parseXMLRequest() throws IOException {
    Map actual        = RequestReader.read(xmlRequestBody("<body><message>hello</message></body>")),
        expected      = new HashMap<>(),
        expectedBody  = new HashMap<>();

    expectedBody.put("message", "hello");
    expected.put("body", expectedBody);

    assertThat(actual, is(expected));
  }

}