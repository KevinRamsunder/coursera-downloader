package exdr.GUI.GUIDisplays.Results;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToggleAction implements ActionListener {

   private ResultsContainer c;

   public ToggleAction(ResultsContainer c) {
      this.c = c;
   }

   @Override
   public void actionPerformed(ActionEvent arg0) {
      for (int i = 0; i < c.checkBox.length; i++) {
         if (c.checkBox[i] != null) {
            c.checkBox[i].setSelected(c.on);
         }
      }

      toggle();
      setButtonStatus();
   }

   private void toggle() {
      c.on = !c.on;
      if (c.on) {
         c.toggle.setText("All On");
         c.total = 0;
      } else {
         c.toggle.setText("All Off");
         c.total = c.parser.structure.getStructure().size();
      }
   }

   private void setButtonStatus() {
      if (c.total > 0) {
         c.submit.setEnabled(true);
      } else {
         c.submit.setEnabled(false);
      }
   }
}