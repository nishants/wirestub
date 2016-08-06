package social.amoeba.jeyson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CompileParam {
  private final Path __files;

  public CompileParam(Path __files, String template) {
    this.__files = __files;
  }

  public CompileParam(Path __files) {
    this.__files = __files;
  }

  public String getTemplate(String path) throws IOException {
    return new String(Files.readAllBytes(Paths.get(__files.toString(), path)));
  }
}
