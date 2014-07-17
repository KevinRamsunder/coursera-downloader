package exdr.GUI;

import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import exdr.GUI.GUIDisplays.LoginGUI;
import exdr.backend.Strings.GUINotifications;

public class EntryPoint {

   public static void main(String[] args) {

      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               setStyle();
               initGUI();
            } catch (Exception e) {
               fatalError();
            }
         }
      });

   }

   private static void initGUI() throws Exception {
      LoginGUI window = new LoginGUI();
      window.getFrame().setVisible(true);
   }

   private static void setStyle() throws Exception {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
   }

   private static void fatalError() {
      JOptionPane.showMessageDialog(null, GUINotifications.unknown);
      System.exit(0);
   }
}
