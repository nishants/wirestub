package social.amoeba.jeyson;

import org.junit.Before;
import org.junit.Test;
import social.amoeba.support.ScriptSpec;
import social.amoeba.support.TestSupport;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;


import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JeysonTest {

  private String templatesPath  = "/templates";
  private Jeyson jeyson;
  private TestSupport support   = new TestSupport();

  @Before
  public void setUp() throws Exception {
    jeyson = new Jeyson(support.absolute(templatesPath));
  }

  @Test
  public void templateSpecs() throws Exception {
    for (ScriptSpec.Spec spec : ScriptSpec.getSpecs()) {
      Map actual = jeyson.compile(spec.scope, spec.template);
      assertThat(
          "spec.name",
          new ObjectMapper().writeValueAsString(actual),
          is(spec.expected));
    }
  }
}