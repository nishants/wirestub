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
  private String templatesPath  = "/templates";
  private TestSupport support = new TestSupport();
  private String[]     specs  = new String[]{
      "/specs/include_template_spec.json  :should include json with relative paths",
      "/specs/expression_spec.json        :should support expressions for primitive types",
      "/specs/scope_spec.json             :should support accessing scope values by reference in expressoins",
      "/specs/plain_old.json              :every json must be a valid jeyson template",
  };

  @Before
  public void setUp() throws Exception {
    jeyson = new Jeyson(Paths.get(this.getClass().getResource(templatesPath).toURI()));
  }

  @Test
  public void testSpecs() throws Exception {
    for (String spec : specs) {
      Spec params = support.getSample(spec.split(":")[0].trim());
      assertThat(spec.split(":")[1], jeyson.compile(params.scope, params.template), is(params.expected));
    }
  }
}