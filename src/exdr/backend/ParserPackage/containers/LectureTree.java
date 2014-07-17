package exdr.backend.ParserPackage.containers;

import java.util.ArrayList;
import java.util.List;

import exdr.backend.FileAgents.DirectoryAgent;
import exdr.backend.FileAgents.DownloadAgent;

public class LectureTree {

   private List<SectionHeader> weeks;

   public LectureTree() {
      this.weeks = new ArrayList<SectionHeader>();
   }

   public LectureTree(List<SectionHeader> weeks) {
      this.weeks = weeks;
   }

   public List<SectionHeader> getStructure() {
      return this.weeks;
   }

   public void createDirectoriesAndDownload(String baseDir) {
      String tempDir = "";
      String tempDir2 = "";

      for (int i = 0; i < weeks.size(); i++) {
         tempDir = baseDir + "\\" + access(i).title;
         DirectoryAgent.createDirectory(tempDir);

         for (int j = 0; j < access(i).quantity; j++) {
            tempDir2 = tempDir + "\\" + access(i, j).title;
            DirectoryAgent.createDirectory(tempDir2);

            for (int k = 0; k < access(i, j).downloadableItems; k++) {
               System.out.println("Downloading: " + access(i, j, k).getTitle());
               DownloadAgent.downloadFile(access(i, j, k), tempDir2);
            }
         }
      }

      System.out.println("\nDownload Complete.\nDestination: " + baseDir);
   }

   public void createDirectoriesAndDownload(String baseDir, int[] locations) {
      String tempDir = "";
      String tempDir2 = "";

      for (int i = 0; i < locations.length; i++) {
         int n = locations[i];

         tempDir = baseDir + "\\" + access(n).title;
         DirectoryAgent.createDirectory(tempDir);

         for (int j = 0; j < access(n).quantity; j++) {
            tempDir2 = tempDir + "\\" + access(n, j).title;
            DirectoryAgent.createDirectory(tempDir2);

            for (int k = 0; k < access(n, j).downloadableItems; k++) {
               DownloadAgent.downloadFile(access(n, j, k), tempDir2);
            }
         }
      }
   }

   public void printEverything() {
      for (int i = 0; i < weeks.size(); i++) {
         System.out.println(access(i).title);

         for (int j = 0; j < access(i).quantity; j++) {
            System.out.println("   " + access(i, j).title);

            for (int k = 0; k < access(i, j).downloadableItems; k++) {
               System.out.println("      " + access(i, j, k).url);
            }
            System.out.println();
         }
         System.out.println();
      }
   }

   public SectionHeader access(int i) {
      return weeks.get(i);
   }

   public VideoContent access(int i, int j) {
      return weeks.get(i).getSection(j);
   }

   public Downloadable access(int i, int j, int k) {
      return weeks.get(i).getSection(j).getDownload(k);
   }
}
