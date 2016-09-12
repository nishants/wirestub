package social.amoeba.jeyson.wiremock.request;

import com.github.tomakehurst.wiremock.http.HttpHeader;
import com.github.tomakehurst.wiremock.http.Request;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RequestReader {
  public static Map read(Request request) throws IOException {
    String mimeType = request.getHeaders().getContentTypeHeader().mimeTypePart();
    boolean isJSON = mimeType.equalsIgnoreCase("application/json"),
            isXML  = mimeType.equalsIgnoreCase("application/xml") || mimeType.equalsIgnoreCase("text/xml");

    Map body = isJSON ? JSON.parse(request.getBody()) : isXML ? XML.parse(request.getBody()) : null,
        headers = new HashMap<>(),
        result = new HashMap<>();

    Iterator<HttpHeader> iterator = request.getHeaders().all().iterator();
    while(iterator.hasNext()){
      HttpHeader next = iterator.next();
      headers.put(next.key(), next.values());
    }

    result.put("body"   , body);
    result.put("headers", headers);

    return result;
  }

}
