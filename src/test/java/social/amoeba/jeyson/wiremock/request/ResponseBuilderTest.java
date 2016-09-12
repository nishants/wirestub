package social.amoeba.jeyson.wiremock.request;

import org.junit.Test;

import java.io.*;
import java.util.Collections;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static social.amoeba.TestSupport.createTempFile;
import static social.amoeba.jeyson.wiremock.request.JSON.parseJSON;
import static social.amoeba.jeyson.wiremock.request.XML.parseXML;

public class ResponseBuilderTest {

  @Test
  public void parseXMLFile() throws IOException {
    String bodyFileName = "some.xml",
           contents     = "<body><message>hello</message></body>";

    File templateFile = createTempFile(bodyFileName, contents);

    Map scope    = Collections.emptyMap();

    String actual = ResponseBuilder.render(scope, templateFile),
        expected = "<body><message>hello</message></body>";
    assertThat(actual, is(expected));
  }

  @Test
  public void parseJsonFile() throws IOException {
    String bodyFileName = "some.json",
           contents     = "{'body' : {'message' : 'hello'}}";

    File templateFile = createTempFile(bodyFileName, contents);

    Map scope    = Collections.emptyMap();

    String actual   = ResponseBuilder.render(scope, templateFile),
           expected = "{'body' : {'message' : 'hello'}}".replaceAll("'", "\"");

    assertThat(actual, is(expected));
  }
}