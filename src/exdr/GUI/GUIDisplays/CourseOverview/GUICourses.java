package exdr.GUI.GUIDisplays.CourseOverview;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;

import exdr.GUI.GUIBackend.GUIUserWebAgent;
import exdr.backend.ParserPackage.containers.Courses;

public class GUICourses {

   private GUIUserWebAgent agent;
   private Courses courses;
   private JFrame frame;
   private JButton submit;
   private JCheckBox[] checkbox;

   public GUICourses(GUIUserWebAgent agent, Courses courses) {
      this.agent = agent;
      this.courses = courses;
      frame = new JFrame();
      submit = new JButton();
      checkbox = new JCheckBox[courses.getSize()];
      
      initialize();
   }

   private void initialize() {
      new InitCourses(getContainer());
   }

   private CourseContainer getContainer() {
      return new CourseContainer(frame, submit, checkbox, agent, courses);
   }
}