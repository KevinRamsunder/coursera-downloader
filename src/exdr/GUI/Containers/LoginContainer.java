package exdr.GUI.Containers;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginContainer {

   public JFrame frame;
   public JTextField emailField;
   public JPasswordField passwordField;
   public JButton submitButton;

   public LoginContainer(JFrame frame, JTextField emailField,
         JPasswordField passwordField, JButton submitButton) {
      this.frame = frame;
      this.emailField = emailField;
      this.passwordField = passwordField;
      this.submitButton = submitButton; 
   }
}
