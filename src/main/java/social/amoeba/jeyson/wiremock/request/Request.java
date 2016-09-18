package social.amoeba.jeyson.wiremock.request;

import com.github.tomakehurst.wiremock.http.HttpHeader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Request extends HashMap{
  protected Request(com.github.tomakehurst.wiremock.http.Request request){
    put("headers",  headers(request));
    put("cookies",  request.getCookies());
    put("query",    queryParams(request.getUrl()));
    put("body",     new HashMap());
  }

  protected static Map queryParams(String url){
    HashMap params = new HashMap<>();
    if(url.contains("?")){
      String[] pairs = url.split("\\?")[1].split("&");
      for(int i = 0 ; i < pairs.length; i++){
        String tokens[] = pairs[i].split("="),
            name = tokens[0],
            value = tokens.length == 2 ? tokens[1] : "";

        params.put(name, value);
      }
    }
    return params;
  }
  protected static Map headers(com.github.tomakehurst.wiremock.http.Request request){
    Map headers = new HashMap<>();
    Iterator<HttpHeader> iterator = request.getHeaders().all().iterator();
    while(iterator.hasNext()){
      HttpHeader next = iterator.next();
      headers.put(next.key(), next.values());
    }
    return headers;
  }
}
