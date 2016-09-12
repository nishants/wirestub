package social.amoeba.jeyson.wiremock.request;

import org.junit.Test;

import java.io.IOException;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static social.amoeba.TestSupport.jsonRequestBody;
import static social.amoeba.TestSupport.xmlRequestBody;
import static social.amoeba.jeyson.wiremock.request.JSON.parseJSON;

public class RequestReaderTest {
  @Test
  public void parseJsonRequest() throws IOException {
    Map actual        = RequestReader.read(jsonRequestBody("{'body' : {'message' : 'hello'}}")),
        expected      = parseJSON("{'headers': {'Content-Type' : ['application/json']}, 'body' : {'body' : {'message' : 'hello'}}, 'cookies' : {}, 'query':{}}");

    assertThat(actual, is(expected));
  }

  @Test
  public void parseXMLRequest() throws IOException {
    Map actual        = RequestReader.read(xmlRequestBody("<body><message>hello</message></body>")),
        expected      = parseJSON("{'headers': {'Content-Type' : ['application/xml']}, 'body' : {'body' : {'message' : 'hello'}}, 'cookies' : {}, 'query':{}}");

    assertThat(actual, is(expected));
  }

  @Test
  public void parseRequestQuery() throws IOException {
    Map actual        = RequestReader.queryParams("file?param-one=value-one&param-two=value-two"),
        expected      = parseJSON("{'param-one': 'value-one', 'param-two': 'value-two'}");

    assertThat(actual, is(expected));
  }

  @Test
  public void emptyQueryForNoParams() throws IOException {
    Map actual        = RequestReader.queryParams("file"),
        expected      = parseJSON("{}");

    assertThat(actual, is(expected));
  }

  @Test
  public void ignoreBadQueryParams() throws IOException {
    Map actual        = RequestReader.queryParams("file?param-one=value-one&param-two"),
        expected      = parseJSON("{'param-one': 'value-one', 'param-two': ''}");

    assertThat(actual, is(expected));
  }

}