package social.amoeba.jeyson.wiremock.request;

import com.github.tomakehurst.wiremock.http.Request;

import java.io.IOException;
import java.util.HashMap;

public class XMLRequest extends social.amoeba.jeyson.wiremock.request.Request{

  public XMLRequest(Request request) throws IOException {
    super(request);
    put("body", new XMLRequestBody(request.getBody()));
  }

  public class XMLRequestBody extends HashMap{
    private final byte[] body;

    public XMLRequestBody(byte[] body) throws IOException {
      super(XML.parse(body));
      this.body = body;
    }

    public Object xpath(String path){
      return null;
    }
  }
}
