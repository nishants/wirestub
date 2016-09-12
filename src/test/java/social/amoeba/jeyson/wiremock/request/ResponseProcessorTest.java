package social.amoeba.jeyson.wiremock.request;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static social.amoeba.TestSupport.createTempFile;
import static social.amoeba.TestSupport.xmlResponseDefinition;

public class ResponseProcessorTest {
  @Rule
  public TemporaryFolder temporaryFolder= new TemporaryFolder();

  @Test
  public void parseXMLFile() throws IOException {
    String bodyFileName = "some.xml",
           contents     = "<body><message>hello</message></body>";

    File templateFile = createTempFile(bodyFileName, contents);

    Map scope         = Collections.emptyMap(),
        actual        = ResponseProcessor.render(scope, xmlResponseDefinition(templateFile), templateFile.getParentFile()),
        expected      = new HashMap<>(),
        expectedBody  = new HashMap<>();

    expectedBody.put("message", "hello");
    expected.put("body", expectedBody);

    assertThat(actual, is(expected));
  }
}