package social.amoeba.jeyson.wiremock;

import java.io.File;
import java.net.URISyntaxException;

import static java.util.stream.IntStream.range;

public class RuntimeArgs {
  public static String[] set(String[] args) throws URISyntaxException {
    String[] params = new String[]{
        "--extensions",
        JeysonWiremock.class.getName(),
        "--root-dir",
        new File(RuntimeArgs.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParent()
    };
    String[] enhanced = new String[args.length  + params.length];

    range(0, args.length)
        .forEach(index -> enhanced[index] = args[index]);

    range(0, params.length)
        .forEach(index -> enhanced[args.length + index] = params[index]);

    return enhanced;
  }
}
