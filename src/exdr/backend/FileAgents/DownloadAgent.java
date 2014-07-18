package exdr.backend.FileAgents;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import exdr.backend.ParserPackage.containers.Downloadable;

public class DownloadAgent {

   public static void downloadFile(Downloadable e, String path) {
      File dir = new File(getFinalPath(e, path));

      try {
         FileUtils.copyURLToFile(e.getURL(), dir);
      } catch (IOException e1) {
         System.out.print("The current download has been interrupted: ");
         System.out.println(e.getTitle());
         e1.printStackTrace();
      }
   }

   private static String getFinalPath(Downloadable e, String path) {
      return path + "\\" + e.getTitle() + getExtension(e);
   }

   private static String getExtension(Downloadable e) {
      URL url = e.getURL();
      String temp = url.toString();

      if (temp.contains(".pdf")) {
         return ".pdf";
      } else if (temp.contains(".ppt")) {
         return ".ppt";
      } else if (temp.contains("=txt")) {
         return ".txt";
      } else if (temp.contains("=srt")) {
         return ".srt";
      } else if (temp.contains(".mp4")) {
         return ".mp4";
      }

      return "";
   }
}
