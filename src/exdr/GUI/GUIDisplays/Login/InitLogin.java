package exdr.GUI.GUIDisplays.Login;

import java.awt.Font;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class InitLogin {

   public InitLogin(LoginContainer c) {
      initFrame(c.frame);
      initEmailLabel(c.frame);
      initPasswordLabel(c.frame);
      initEmail(c.frame, c.emailField);
      initPassword(c.frame, c.passwordField);
      initButton(c);
      initVisible(c.frame);
   }
   
   private void initFrame(JFrame frame) {
      frame.setResizable(false);
      frame.setTitle("Coursera Login");
      frame.setBounds(100, 100, 275, 145);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);
   }

   private void initEmailLabel(JFrame frame) {
      Label label = new Label("Email");
      label.setFont(new Font("Arial", Font.BOLD, 11));
      label.setBounds(19, 19, 39, 22);
      frame.getContentPane().add(label);
   }

   private void initEmail(JFrame frame, JTextField emailField) {
      emailField.setFont(new Font("Dialog", Font.PLAIN, 11));
      emailField.setBounds(94, 18, 163, 20);
      frame.getContentPane().add(emailField);
      emailField.setColumns(10);
      frame.getContentPane().add(emailField);
   }

   private void initPassword(JFrame frame, JPasswordField passwordField) {
      passwordField.setFont(new Font("Dialog", Font.PLAIN, 8));
      passwordField.setColumns(10);
      passwordField.setBounds(94, 46, 163, 20);
      frame.getContentPane().add(passwordField);
   }

   private void initPasswordLabel(JFrame frame) {
      Label label_1 = new Label("Password");
      label_1.setFont(new Font("Arial", Font.BOLD, 11));
      label_1.setBounds(18, 46, 63, 22);
      frame.getContentPane().add(label_1);
   }

   private void initButton(LoginContainer c) {
      c.submitButton.setText("Login");
      c.submitButton.setBounds(15, 79, 242, 23);
      c.frame.getContentPane().add(c.submitButton);
      c.submitButton.addActionListener(new LoginAction(c));
      c.frame.getRootPane().setDefaultButton(c.submitButton);
   }
   
   private void initVisible(JFrame frame) {
      frame.setVisible(true);
   }
}