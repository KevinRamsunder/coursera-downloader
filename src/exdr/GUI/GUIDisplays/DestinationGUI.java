package exdr.GUI.GUIDisplays;

import java.io.File;

import javax.swing.JFileChooser;

public class DestinationGUI {

   private JFileChooser chooser;
   private String path;

   public DestinationGUI() {
      initChooser();
      ignite();
   }

   private void initChooser() {
      chooser = new JFileChooser(new File(""));
      chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      chooser.setMultiSelectionEnabled(false);
      chooser.setVisible(true);
   }

   public void ignite() {
      int clickVal = chooser.showSaveDialog(null);
      processResult(clickVal);
   }

   private void processResult(int clickVal) {
      if (clickVal == JFileChooser.APPROVE_OPTION) {
         path = chooser.getSelectedFile().toString();
      }
   }

   public String getPath() {
      return path; 
   }
}
