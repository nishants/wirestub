package social.amoeba.jeyson.wiremock.request;

import com.github.tomakehurst.wiremock.http.Request;

import java.io.IOException;

public class XMLRequest extends social.amoeba.jeyson.wiremock.request.Request{
  public XMLRequest(Request request) throws IOException {
    put("body", XML.parse(request.getBody()));
    put("headers", headers(request));
    put("cookies", request.getCookies());
    put("query", queryParams(request.getUrl()));
  }

}
