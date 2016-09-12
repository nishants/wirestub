package social.amoeba.jeyson.wiremock.request;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.util.Map;

public class XMLRequestReader extends RequestReader{
  private final byte[] body;
  private final XmlMapper xmlMapper;

  public XMLRequestReader(byte[] bodyAsString) {
    body = bodyAsString;
    xmlMapper = new XmlMapper();
  }

  public Map parse() throws IOException {
    return xmlMapper.readValue(String.format("<request>%s</request>", new String(body)), Map.class);
  }
}
