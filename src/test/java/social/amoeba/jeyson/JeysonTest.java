package social.amoeba.jeyson;

import org.junit.Before;
import org.junit.Test;
import social.amoeba.TestSupport;
import social.amoeba.TestSupport.Spec;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JeysonTest {

  private Jeyson jeyson;
  private String __files      = "/__files";
  private String __helloFile  = "/__files/hello/hello.json";
  private String __includeFile  = "/__files/include.json";
  private String helloFileContent;
  private String includeFileContent;
  private TestSupport support = new TestSupport();

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
    Spec sample = support.getSample("/specs/include_template_spec.json");
    assertThat(jeyson.compile(sample.scope, sample.template), is(sample.expected));
  }

  @Test
  public void testExpressoin() throws Exception {
    Spec sample = support.getSample("/specs/expression_spec.json");
    assertThat(jeyson.compile(sample.scope, sample.template), is(sample.expected));
  }
  @Test
  public void testScope() throws Exception {
    Spec sample = support.getSample("/specs/scope_spec.json");
    assertThat(jeyson.compile(sample.scope, sample.template), is(sample.expected));
  }
}