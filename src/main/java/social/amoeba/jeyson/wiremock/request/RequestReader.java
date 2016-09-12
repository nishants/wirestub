package social.amoeba.jeyson.wiremock.request;

import com.github.tomakehurst.wiremock.http.Request;

import java.io.IOException;
import java.util.Map;

public abstract class RequestReader {
  abstract Map parse() throws IOException;

  public static Map read(Request request) throws IOException {
    String mimeType = request.getHeaders().getContentTypeHeader().mimeTypePart();
    boolean isJSON = mimeType.equalsIgnoreCase("application/json"),
            isXML  = mimeType.equalsIgnoreCase("application/xml") || mimeType.equalsIgnoreCase("text/xml");

    return isJSON ? new JSONRequestReader(request.getBody()).parse() : null;
  }

}
