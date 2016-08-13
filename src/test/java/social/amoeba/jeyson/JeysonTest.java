package social.amoeba.jeyson;

import org.junit.Before;
import org.junit.Test;
import social.amoeba.TestSupport;
import social.amoeba.TestSupport.Spec;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;


import java.nio.file.Paths;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JeysonTest {

  private final String jeysonJsPath   = "/jeyson.js";
  private final String templatesPath  = "/templates";

  private final String[]     specs    = new String[]{
      "/specs/include_template_spec.json  :should include json with relative paths",
      "/specs/expression_spec.json        :should support expressions for primitive types",
      "/specs/scope_spec.json             :should support accessing scope values by reference in expressoins",
      "/specs/plain_old.json              :every json must be a valid jeyson template",
  };

  private Jeyson jeyson;
  private TestSupport support   = new TestSupport();

  @Before
  public void setUp() throws Exception {
    jeyson = new Jeyson(support.absolute(jeysonJsPath),support.absolute(templatesPath));
  }

  @Test
  public void testSpecs() throws Exception {
    for (String spec : specs) {
      Spec params = support.getSample(spec.split(":")[0].trim());
      Map actual = jeyson.compile(params.scope, params.template);
      assertThat(
          spec.split(":")[1],
          new ObjectMapper().writeValueAsString(actual),
          is(params.expected));
    }
  }
}