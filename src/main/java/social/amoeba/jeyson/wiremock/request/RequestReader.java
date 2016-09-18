package social.amoeba.jeyson.wiremock.request;

import com.github.tomakehurst.wiremock.http.ContentTypeHeader;
import com.github.tomakehurst.wiremock.http.Request;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestReader {

  public static Map read(Request request) throws IOException {
    if(request.getMethod().getName().equals("GET")){
      return new social.amoeba.jeyson.wiremock.request.Request(request);
    }
    ContentTypeHeader contentType = request.getHeaders().getContentTypeHeader();
    String mimeType = contentType == null ? null : contentType.mimeTypePart();
    boolean isJSON = contentType != null && mimeType.equalsIgnoreCase("application/json"),
            isXML  = contentType != null &&mimeType.equalsIgnoreCase("application/xml") || mimeType.equalsIgnoreCase("text/xml");

    return isXML ? new XMLRequest(request) : isJSON ? new JSONRequest(request) : new HashMap<>();
  }

}
