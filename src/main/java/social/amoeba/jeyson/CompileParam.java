package social.amoeba.jeyson;

public class CompileParam {
  private final String template;

  public CompileParam(String template) {
    this.template = template;
  }

  public String getTemplate(String path){
    return "{\"message\" : \"hello\"}";
  }
}
