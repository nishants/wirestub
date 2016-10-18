package social.amoeba.jeyson;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import social.amoeba.TestSupport;
import social.amoeba.TestSupport.Spec;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JeysonArrayTest {
  private final String templatesPath  = "/templates";


  private Jeyson jeyson;
  private TestSupport support   = new TestSupport();

  @Before
  public void setUp() throws Exception {
    jeyson = new Jeyson(support.absolute(templatesPath));
  }

  @Ignore
  @Test
  public void testSpecs() throws Exception {
    HashMap<Object, Object> scope = new HashMap<>(),
      template = new HashMap<>();
    template.put("data", new int[]{1,2,3});

    String actual = new ObjectMapper().writeValueAsString(jeyson.compile(scope, new ObjectMapper().writeValueAsString(template)));
    assertThat(
        actual,
        is("{\"data\" : [[1,2,3]]}"));
  }
}