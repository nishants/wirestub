package social.amoeba.jeyson.wiremock.request;

import org.junit.Test;
import social.amoeba.jeyson.wiremock.request.XMLRequest.XMLRequestBody;

import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static social.amoeba.support.TestSupport.*;
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
  public void xpathForXmlRequests() throws IOException, XPathExpressionException {
    XMLRequestBody xmlRequestBody       = (XMLRequestBody)(RequestReader.read(xmlRequestBody("<body><message>hello</message></body>")).get("body"));

    assertThat(xmlRequestBody.xpath("//message[text() = 'hello']"), is("hello"));
  }

  @Test
  public void parseRequestQueryForJson() throws IOException {
    com.github.tomakehurst.wiremock.http.Request request = jsonRequestBody("{}");
    setURl(request, "file?param-one=value-one&param-two=value-two");
    Map actual        = toMap(RequestReader.read(request).get("query")),
        expected      = parseJSON("{'param-one': 'value-one', 'param-two': 'value-two'}");

    assertThat(actual, is(expected));
  }
  @Test
  public void parseRequestQueryForXml() throws IOException {
    com.github.tomakehurst.wiremock.http.Request request = xmlRequestBody("<hello></hello>");
    setURl(request, "file?param-one=value-one&param-two=value-two");
    Map actual        = toMap(RequestReader.read(request).get("query")),
        expected      = parseJSON("{'param-one': 'value-one', 'param-two': 'value-two'}");

    assertThat(actual, is(expected));
  }


  @Test
  public void emptyQueryForNoParams() throws IOException {
    com.github.tomakehurst.wiremock.http.Request request = jsonRequestBody("{}");
    setURl(request, "file");

    Map actual        = toMap(RequestReader.read(request).get("query")),
        expected      = parseJSON("{}");

    assertThat(actual, is(expected));
  }

  @Test
  public void ignoreBadQueryParams() throws IOException {
    com.github.tomakehurst.wiremock.http.Request request = jsonRequestBody("{}");
    setURl(request, "file?param-one=value-one&param-two");

    Map actual        = toMap(RequestReader.read(request).get("query")),
        expected      = parseJSON("{'param-one': 'value-one', 'param-two': ''}");

    assertThat(actual, is(expected));
  }

}