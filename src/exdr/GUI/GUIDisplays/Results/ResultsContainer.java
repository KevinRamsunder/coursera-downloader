package exdr.GUI.GUIDisplays.Results;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JToggleButton;

import exdr.GUI.GUIBackend.GUIMainParser;
import exdr.backend.ParserPackage.containers.Courses;

public class ResultsContainer {

   public GUIMainParser parser;
   public JFrame frame;
   public JButton submit;
   public JCheckBox[] checkBox;
   public Courses courses;
   public JToggleButton toggle;
   public int total;
   public int selectedTotal;
   public boolean on = false;

   public ResultsContainer(GUIMainParser p, JFrame f, JButton s,
         JCheckBox[] cb, Courses vc, JToggleButton t, int tot, boolean state) {
      parser = p;
      frame = f;
      submit = s;
      checkBox = cb;
      courses = vc;
      total = tot;
      on = state;
   }
}
