package exdr.GUI.GUIDisplays.Results;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JToggleButton;

import exdr.GUI.GUIBackend.GUIMainParser;
import exdr.backend.ParserPackage.containers.Courses;

public class GUIResults {

   private GUIMainParser parser;
   private JFrame frame;
   private JButton submit;
   private JCheckBox[] checkBox;
   private JToggleButton toggle;
   private int total;
   private Courses courses;
   private boolean on = false;

   public GUIResults(GUIMainParser parser, Courses courses) {
      this.parser = parser;
      frame = new JFrame();
      submit = new JButton("Submit");
      toggle = new JToggleButton("All Off");
      this.courses = courses;

      initialize();
   }

   private void initialize() {
      new InitResults(getContainer());
   }

   private ResultsContainer getContainer() {
      return new ResultsContainer(parser, frame, submit, checkBox,
            courses, toggle, total, on);
   }
}