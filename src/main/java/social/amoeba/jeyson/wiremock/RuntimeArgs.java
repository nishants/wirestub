package social.amoeba.jeyson.wiremock;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;

public class RuntimeArgs {
  public static String[] set(String[] args) throws URISyntaxException {
    List<String> params = new ArrayList<String>(asList(args));
    params.add("--extensions");
    params.add(JeysonWiremock.class.getName());

    if(params.indexOf("--root-dir") < 0){
      params.add("--root-dir");
      params.add(new File(RuntimeArgs.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getAbsolutePath());
    }

    return params.toArray(new String[0]);
  }
}
