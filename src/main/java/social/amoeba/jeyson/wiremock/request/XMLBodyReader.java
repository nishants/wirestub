package social.amoeba.jeyson.wiremock.request;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.util.Map;

public class XMLBodyReader extends RequestReader{
  private final byte[] body;
  private final XmlMapper xmlMapper;

  public XMLBodyReader(byte[] bodyAsString) {
    body = bodyAsString;
    xmlMapper = new XmlMapper();
  }

  public Map parse() throws IOException {
    return xmlMapper.readValue(String.format("<request>%s</request>", new String(body)), Map.class);
  }
}
