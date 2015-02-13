package exdr.GUI.GUIDisplays.Downloader;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import exdr.backend.ParserPackage.containers.Courses;
import exdr.backend.ParserPackage.containers.LectureTree;

public class DownloaderContainer {

   public JFrame frame;
   public JLabel label;
   public JPanel panel;
   public final JLabel count;
   public LectureTree tree;
   public String baseDir;
   public Courses courses;
   public int[] locations;

   public DownloaderContainer(JFrame frame, JLabel label, LectureTree tree,
         final JPanel panel, final JLabel count, String baseDir,
         int[] locations, Courses courses) {
      this.frame = frame;
      this.label = label;
      this.tree = tree;
      this.panel = panel;
      this.count = count;
      this.baseDir = baseDir;
      this.locations = locations;
      this.courses = courses;
   }
}
