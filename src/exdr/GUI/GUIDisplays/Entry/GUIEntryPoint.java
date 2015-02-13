package exdr.GUI.GUIDisplays.Entry;

import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import exdr.GUI.GUIDisplays.Login.GUILogin;
import exdr.backend.Strings.GUINotifications;

public class GUIEntryPoint {

   /** GUI Entry */
   
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               setStyle();
               initGUI();
            } catch (Exception e) {
               e.printStackTrace();
               fatalError();
            }
         }
      });
   }

   private static void initGUI() throws Exception {
      new GUILogin();
   }

   private static void setStyle() throws Exception {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
   }

   private static void fatalError() {
      JOptionPane.showMessageDialog(null, GUINotifications.unknown);
      System.exit(0);
   }
}