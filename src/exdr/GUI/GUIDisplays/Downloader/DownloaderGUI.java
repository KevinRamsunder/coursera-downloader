package exdr.GUI.GUIDisplays.Downloader;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import exdr.backend.ParserPackage.containers.Courses;
import exdr.backend.ParserPackage.containers.LectureTree;

public class DownloaderGUI {

   private JFrame frame;
   private LectureTree structure;
   private int[] locations;
   private JPanel panel;
   private String absPath;
   private JLabel count;
   private JLabel label;
   private Courses courses;

   public DownloaderGUI(LectureTree structure, String absPath, int[] checked,
         Courses courses) {
      this.structure = structure;
      this.locations = checked;
      this.absPath = absPath;
      this.courses = courses;

      frame = new JFrame();
      count = new JLabel();
      panel = new JPanel();

      initialize();
   }

   private void initialize() {
      new InitDownloader(getContainer());
   }

   private DownloaderContainer getContainer() {
      return new DownloaderContainer(frame, label, structure, panel, count,
            absPath, locations, courses);
   }
}