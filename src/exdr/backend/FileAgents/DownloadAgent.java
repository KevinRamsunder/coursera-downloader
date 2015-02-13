package exdr.backend.FileAgents;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import exdr.backend.ParserPackage.containers.Downloadable;

public class DownloadAgent {

   public static void downloadFile(Downloadable e, String path) {
      URL url = e.getURL();

      if (url == null || e.getTitle() == null) {
         return;
      }

      File dir = new File(getFinalPath(url, e, path));

      try {
         FileUtils.copyURLToFile(url, dir);
      } catch (Exception e1) {
         System.out.print("The current download has been interrupted: ");
         System.out.println(e.getTitle());
         return;
      }
   }

   private static String getFinalPath(URL url, Downloadable e, String path) {
      return path + "\\" + e.getTitle() + getExtension(url, e);
   }

   private static String getExtension(URL url, Downloadable e) {
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
