package social.amoeba.jeyson.wiremock.request;

import java.util.HashMap;
import java.util.Map;

public class XMLMap extends HashMap {
  public XMLMap(Map map){
    super(map);
  }

  public XMLMap() {
    super();
  }

  public Object xpath(String path) {
    return null;
  }
}
