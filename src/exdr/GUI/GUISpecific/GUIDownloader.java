package exdr.GUI.GUISpecific;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import exdr.backend.FileAgents.DirectoryAgent;
import exdr.backend.FileAgents.DownloadAgent;
import exdr.backend.ParserPackage.containers.Downloadable;
import exdr.backend.ParserPackage.containers.SectionHeader;
import exdr.backend.ParserPackage.containers.VideoContent;

public class GUIDownloader {

   private List<SectionHeader> weeks;

   public GUIDownloader(List<SectionHeader> weeks, final JPanel panel,
         final JLabel count, String baseDir, int[] locations) {

      this.weeks = weeks;
      String tempDir = "";
      String tempDir2 = "";

      for (int i = 0; i < locations.length; i++) {
         int n = locations[i];

         tempDir = baseDir + "\\" + access(n).getTitle();
         DirectoryAgent.createDirectory(tempDir);

         for (int j = 0; j < access(n).getQuantity(); j++) {
            tempDir2 = tempDir + "\\" + access(n, j).getTitle();
            DirectoryAgent.createDirectory(tempDir2);

            for (int k = 0; k < access(n, j).getDownloadableCount(); k++) {
               DownloadAgent.downloadFile(access(n, j, k), tempDir2);
               updateList(panel, count);
            }
         }
      }
      programFinished(baseDir);
   }

   private SectionHeader access(int i) {
      return weeks.get(i);
   }

   private VideoContent access(int i, int j) {
      return weeks.get(i).getSection(j);
   }

   private Downloadable access(int i, int j, int k) {
      return weeks.get(i).getSection(j).getDownload(k);
   }

   private void updateList(final JPanel panel, final JLabel count) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            panel.remove(panel.getComponent(0));
            panel.updateUI();
            count.setText(String.valueOf((Integer.parseInt(count.getText()) - 1)));
         }
      });
   }

   private void programFinished(String baseDir) {
      String t = "Downloads complete.\nFiles located: ";
      JOptionPane.showMessageDialog(null, t + baseDir);
      System.exit(0);
   }
}
