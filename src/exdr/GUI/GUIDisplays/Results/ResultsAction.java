package exdr.GUI.GUIDisplays.Results;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import exdr.GUI.GUIDisplays.Downloader.DownloaderGUI;
import exdr.GUI.GUIDisplays.PathSelector.DestinationGUI;

public class ResultsAction implements ActionListener {

   private ResultsContainer c;

   public ResultsAction(ResultsContainer c) {
      this.c = c;
   }

   @Override
   public void actionPerformed(ActionEvent arg0) {
      buttonState(false);

      DestinationGUI i = new DestinationGUI();
      String path = i.getPath();

      if (path == null) {
         c.submit.setEnabled(true);
         JOptionPane.showMessageDialog(null,
               "You did not choose a destination.");
         return;
      }

      c.frame.dispose();
      path += "\\Coursera Downloads";
      new DownloaderGUI(c.parser.structure, path, checkboxToIntArray(),
            c.courses);
   }

   private int[] checkboxToIntArray() {
      int i = 0;
      int[] onValues = new int[c.selectedTotal];

      for (int j = 0; j < c.checkBox.length; j++) {
         if (c.checkBox[j] != null && c.checkBox[j].isSelected()) {
            onValues[i++] = j;
         }
      }
      
      return Arrays.copyOfRange(onValues, 0, i);
   }

   private void buttonState(final boolean enabled) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            c.submit.setEnabled(enabled);
         }
      });
   }
}