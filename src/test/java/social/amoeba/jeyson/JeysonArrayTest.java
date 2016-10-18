package social.amoeba.jeyson;

import org.junit.Before;
import org.junit.Test;
import social.amoeba.TestSupport;
import wiremock.com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;

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

  @Test
  public void testSpecs() throws Exception {
    HashMap<Object, Object> scope = new HashMap<>(),
      template = new HashMap<>();

    ArrayList<Integer> array = new ArrayList<>();
    array.add(1);
    array.add(2);
    array.add(3);
    template.put("data", array);

    String  json    = new ObjectMapper().writeValueAsString(template),
            actual  = new ObjectMapper().writeValueAsString(jeyson.compile(scope, json));

    assertThat(actual, is("{\"data\":[1,2,3]}"));
  }
}