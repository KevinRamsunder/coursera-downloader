package exdr.GUI.Containers;

import javax.swing.JLabel;
import javax.swing.JPanel;

import exdr.backend.ParserPackage.containers.LectureTree;

public class DownloaderContainer {

   public final JPanel panel;
   public final JLabel count;
   public LectureTree tree;
   public String baseDir;
   public int[] locations;

   public DownloaderContainer(LectureTree tree, final JPanel panel,
         final JLabel count, String baseDir, int[] locations) {
      this.tree = tree;
      this.panel = panel;
      this.count = count;
      this.baseDir = baseDir;
      this.locations = locations; 
   }
}
