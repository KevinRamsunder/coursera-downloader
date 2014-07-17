package exdr.GUI.Actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

import exdr.GUI.Containers.LoginContainer;
import exdr.GUI.GUIDisplays.EnterUrlGUI;
import exdr.GUI.GUISpecific.GUIUserWebAgent;
import exdr.backend.Strings.GUINotifications;
import exdr.backend.WebAgents.UserCredentials;

public class LoginAction implements ActionListener {

   private JFrame frame;
   private JTextField emailField;
   private JPasswordField passwordField;
   private JButton submitButton;

   public LoginAction(LoginContainer container) {
      frame = container.frame;
      emailField = container.emailField;
      passwordField = container.passwordField;
      submitButton = container.submitButton;
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      Thread thread = new Thread() {
         public void run() {
            login();
         }
      };

      thread.start();
   }

   private void login() {
      GUIUserWebAgent webAgent = null;

      char[] email = emailField.getText().toCharArray();
      char[] password = passwordField.getPassword();

      loadingButton();
      resetFields(false);

      try {
         webAgent = new GUIUserWebAgent(new UserCredentials(email, password));
      } catch (FailingHttpStatusCodeException | IOException e1) {
         webAgent = null;
         fatalError();
         System.exit(0);
      } catch (IllegalArgumentException e1) {
         webAgent = null;
         nonFatalError();
         return;
      } finally {
         email = null;
         password = null;
      }

      nextTask(webAgent);
      frame.dispose();
   }

   private void nextTask(GUIUserWebAgent i) {
      new EnterUrlGUI(i);
   }

   private void fatalError() {
      JOptionPane.showMessageDialog(null, GUINotifications.fatal);
   }

   private void nonFatalError() {
      JOptionPane.showMessageDialog(null, GUINotifications.loginFail);
      releaseButton();
      resetFields(true);
   }

   private void releaseButton() {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            submitButton.setEnabled(true);
            submitButton.setText("Login");
         }
      });
   }

   private void loadingButton() {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            submitButton.setEnabled(false);
            submitButton.setText("Logging in...");
         }
      });
   }

   private void resetFields(final boolean enabled) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            emailField.setText("");
            emailField.setEnabled(enabled);
            passwordField.setText("");
            passwordField.setEnabled(enabled);
         }
      });
   }
}