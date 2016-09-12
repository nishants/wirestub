package social.amoeba.jeyson.wiremock;

import static java.util.stream.IntStream.range;

public class RuntimeArgs {
  private static final String[] RUN_TIME_ARGS = new String[]{
      "--extensions",
      "social.amoeba.jeyson.wiremock.JeysonWiremock"
  };
  public static String[] like(String[] args){
    String[] enhanced = new String[args.length  + 2];

    range(0, args.length)
        .forEach(index -> enhanced[index] = args[index]);

    range(0, RUN_TIME_ARGS.length)
        .forEach(index -> enhanced[args.length + index] = RUN_TIME_ARGS[index]);

    return enhanced;
  }
}
