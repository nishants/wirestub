package social.amoeba.jeyson.wiremock.request;

import org.junit.Test;
import social.amoeba.jeyson.wiremock.response.ResponseWriter;

import java.io.*;
import java.util.Collections;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static social.amoeba.TestSupport.createTempFile;

public class ResponseBuilderTest {

  @Test
  public void parseXMLFile() throws Exception {
    String bodyFileName = "some.xml",
           contents     = "<body><message>hello</message></body>";

    File templateFile = createTempFile(bodyFileName, contents);
    ResponseWriter builder = new ResponseWriter(templateFile.getParent());

    Map scope    = Collections.emptyMap();

    String actual = new String(builder.readTemplate(templateFile)),
        expected = "<body><message>hello</message></body>";
    assertThat(actual, is(expected));
  }

  @Test
  public void parseJsonFile() throws Exception {
    String bodyFileName = "some.json",
           contents     = "{'body' : {'message' : 'hello'}}";

    File templateFile = createTempFile(bodyFileName, contents);

    Map scope    = Collections.emptyMap();
    ResponseWriter builder = new ResponseWriter(templateFile.getParent());

    String actual   = new String(builder.readTemplate(templateFile)),
           expected = "{'body' : {'message' : 'hello'}}".replaceAll("'", "\"");

    assertThat(actual, is(expected));
  }
}