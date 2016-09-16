package social.amoeba.jeyson;


import java.util.HashMap;
import java.util.Map;

public class ResponseScope extends HashMap{
  public ResponseScope(Map session, Map config, Map request){
    put("request", request);
    put("session", session);
    put("config" , new HashMap(config));
  }
}
