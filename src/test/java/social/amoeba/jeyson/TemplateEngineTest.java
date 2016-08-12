package social.amoeba.jeyson;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TemplateEngineTest {

  private TemplateEngine templateEngine;
  private String jeysonJs     = "/jeyson-contract.js";
  private String __files      = "/__files";
  private String __helloFile  = "/__files/hello/hello.json";
  private String helloFileContent;

  @Before
  public void setUp() throws Exception {
    templateEngine = new TemplateEngine(
        Paths.get(this.getClass().getResource(jeysonJs).toURI()),
        Paths.get(this.getClass().getResource(__files).toURI())
    );
    helloFileContent = new String(Files.readAllBytes(Paths.get(this.getClass().getResource(__helloFile).toURI())));
  }

  @Test
  public void testGetTemplateCallBack() throws Exception {
    String result = templateEngine.compile(null, "{'id': 1}");
    assertThat(result, is(helloFileContent));
  }
}