package exdr.GUI.GUIDisplays.CourseOverview;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import exdr.GUI.GUIBackend.GUIUserWebAgent;
import exdr.backend.ParserPackage.containers.Courses;

public class CourseContainer {

   public JFrame frame;
   public JButton submit;
   public JCheckBox[] checkbox;
   public GUIUserWebAgent agent;
   public Courses courses;
   public int total;

   public CourseContainer(JFrame frame, JButton submit, JCheckBox[] checkbox,
         GUIUserWebAgent agent, Courses courses) {
      this.frame = frame;
      this.submit = submit;
      this.checkbox = checkbox;
      this.agent = agent;
      this.courses = courses;
      this.total = courses.getSize();
   }
}