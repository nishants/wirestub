package social.amoeba.jeyson;

import com.fasterxml.jackson.core.JsonFactory;
import org.junit.Before;
import org.junit.Test;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JeysonTest {

  private Jeyson jeyson;
  private String __files      = "/__files";
  private String __helloFile  = "/__files/hello/hello.json";
  private String __includeFile  = "/__files/include.json";
  private String helloFileContent;
  private String includeFileContent;

  @Before
  public void setUp() throws Exception {
    jeyson = new Jeyson(Paths.get(this.getClass().getResource(__files).toURI()));
    helloFileContent = new String(Files.readAllBytes(Paths.get(this.getClass().getResource(__helloFile).toURI())));
    includeFileContent = new String(Files.readAllBytes(Paths.get(this.getClass().getResource(__includeFile).toURI())));
  }

  @Test
  public void testCompilePlainJson() throws Exception {
    String result = jeyson.compile(Collections.emptyMap(), helloFileContent);
    assertThat(result, is(helloFileContent));
  }

  @Test
  public void testCompileinclude() throws Exception {
    String result = jeyson.compile(Collections.emptyMap(), includeFileContent);
    assertThat(result, is("{\"message\":{\"body\":{\"shared\":\"a shared json template\"}}}"));
  }
}