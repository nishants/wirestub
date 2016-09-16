package social.amoeba.jeyson.wiremock.request;

import com.github.tomakehurst.wiremock.http.Request;

import java.io.IOException;

public class JSONRequest extends social.amoeba.jeyson.wiremock.request.Request{
  public JSONRequest(Request request) throws IOException {
    super(request);
    put("body",     JSON.parse(request.getBody()));
  }

}
