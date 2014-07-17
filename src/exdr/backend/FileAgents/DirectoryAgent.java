package exdr.backend.FileAgents;

import java.io.File;

public class DirectoryAgent {

   public static void createDirectory(String dir) {
      new File(dir).mkdirs();
   }
}
