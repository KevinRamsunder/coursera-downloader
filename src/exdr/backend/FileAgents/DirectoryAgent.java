package exdr.backend.FileAgents;

import java.io.File;

public class DirectoryAgent {

   public static void createDirectory(String dir) {
      boolean fileCreated = makeFile(dir);
      if (!fileCreated) {
         tryAgain(dir);
      }
   }

   private static boolean makeFile(String dir) {
      return new File(dir).mkdirs();
   }

   private static void tryAgain(String dir) {
      String newDirectory = clearInvalidChars(dir);
      makeFile(newDirectory);
   }

   private static String clearInvalidChars(String title) {
      return title.replaceAll("\\\\|\\/|\\:|\\*|\\?|\"|\\<|\\>|\\|", ".");
   }
}