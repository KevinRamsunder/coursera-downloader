package exdr.GUI.GUIDisplays.Downloader;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import exdr.backend.FileAgents.DirectoryAgent;
import exdr.backend.FileAgents.DownloadAgent;
import exdr.backend.ParserPackage.containers.Downloadable;
import exdr.backend.ParserPackage.containers.LectureTree;
import exdr.backend.ParserPackage.containers.SectionHeader;
import exdr.backend.ParserPackage.containers.VideoContent;

public class DownloadAction {

   private LectureTree tree;
   private String baseDir;
   private int[] locations;
   private final JPanel panel;
   private final JLabel count;

   public DownloadAction(DownloaderContainer container) {
      this.tree = container.tree;
      this.baseDir = container.baseDir;
      this.locations = container.locations;
      this.panel = container.panel;
      this.count = container.count;

      init();

      programFinished(baseDir);
   }

   private void init() {
      String tempDir = "";
      String tempDir2 = "";

      for (int i = 0; i < locations.length; i++) {
         int n = locations[i];
         tempDir = baseDir + "\\" + access(n).getClassName() + "\\"
               + access(n).getTitle();
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
   }

   private SectionHeader access(int i) {
      return tree.access(i);
   }

   private VideoContent access(int i, int j) {
      return tree.access(i, j);
   }

   private Downloadable access(int i, int j, int k) {
      return tree.access(i, j, k);
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