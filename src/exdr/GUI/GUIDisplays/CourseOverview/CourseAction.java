package exdr.GUI.GUIDisplays.CourseOverview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import exdr.GUI.GUIBackend.GUIMainParser;
import exdr.GUI.GUIBackend.GUIUserWebAgent;
import exdr.GUI.GUIDisplays.Results.GUIResults;
import exdr.backend.ParserPackage.containers.Courses;

public class CourseAction implements ActionListener {

   private JFrame frame;
   private JButton submit;
   private GUIUserWebAgent agent;
   private Courses courses;
   private JCheckBox[] checkbox;

   public CourseAction(CourseContainer c) {
      this.frame = c.frame;
      this.submit = c.submit;
      this.agent = c.agent;
      this.courses = c.courses;
      this.checkbox = c.checkbox;
   }

   private void addSelectedToMap() {
      for (int i = 0; i < courses.getSize(); i++) {
         if (checkbox[i].isSelected()) {
            courses.addToMap(i, urlToCourseCode(courses, i));
         }
      }
      
      return;
   }

   private String urlToCourseCode(Courses courses, int j) {
      String url = courses.getLink(j) + "lecture";
      return url.substring(url.indexOf('/', 10) + 1, url.lastIndexOf('/'));
   }

   @Override
   public void actionPerformed(ActionEvent arg0) {
      Thread thread = new Thread() {
         public void run() {
            init();
         }
      };

      thread.start();
   }

   private void init() {
      buttonState(false);
      addSelectedToMap();
      try {
         new GUIResults(new GUIMainParser(agent, courses), courses);
      } catch (Exception e) {
         e.printStackTrace();
      }
      frame.dispose();
   }

   private void buttonState(final boolean state) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            submit.setEnabled(state);
            submit.setText("Loading...");
            for (int i = 0; i < checkbox.length; i++)
               checkbox[i].setEnabled(false);
         }
      });
   }
}