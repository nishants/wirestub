package social.amoeba.jeyson.wiremock.request;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class XML {
  private static final XmlMapper xmlMapper = new XmlMapper();

  public static Map parse(byte[] body) throws IOException {
    return xmlMapper.readValue(String.format("<wrapper>%s</wrapper>", new String(body)), Map.class);
  }

  public static Map parseXML(String string) throws IOException {
    return parse(string.getBytes());
  }

  public static Map parse(File file) throws IOException {
    return parseXML(new Scanner(file).useDelimiter("\\Z").next());
  }
}
