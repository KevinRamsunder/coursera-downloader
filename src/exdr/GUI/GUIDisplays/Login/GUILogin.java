package exdr.GUI.GUIDisplays.Login;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class GUILogin {

   private JFrame frame;
   private JTextField emailField;
   private JPasswordField passwordField;
   private JButton submitButton;

   public GUILogin() {
      frame = new JFrame();
      emailField = new JTextField();
      passwordField = new JPasswordField();
      submitButton = new JButton();
      
      initialize();
   }

   private void initialize() {
      new InitLogin(getContainer());
   }

   private LoginContainer getContainer() {
      return new LoginContainer(frame, emailField, passwordField, submitButton);
   }
}