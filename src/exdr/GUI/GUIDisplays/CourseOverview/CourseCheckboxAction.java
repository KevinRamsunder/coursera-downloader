package exdr.GUI.GUIDisplays.CourseOverview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

public class CourseCheckboxAction implements ActionListener {

   private JCheckBox checkBox;
   private CourseContainer c;

   public CourseCheckboxAction(JCheckBox checkBox, CourseContainer c) {
      this.checkBox = checkBox;
      this.c = c;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (checkBox.isSelected()) {
         c.total++;
      } else {
         c.total--;
      }
      setButtonStatus();
   }

   private void setButtonStatus() {
      if (c.total > 0) {
         c.submit.setEnabled(true);
      } else {
         c.submit.setEnabled(false);
      }
   }
}