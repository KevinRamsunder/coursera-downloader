package exdr.GUI.GUIDisplays;

import java.awt.Font;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginGUI {

   private JFrame frame;
   private JTextField emailField;
   private JPasswordField passwordField;
   private JButton submitButton;

   public LoginGUI() {
      initialize();
   }

   public JFrame getFrame() {
      return frame;
   }

   private void initialize() {
      frame = new JFrame();
      frame.setResizable(false);
      frame.setTitle("Coursera Login");
      frame.setBounds(100, 100, 275, 145);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);

      Label label = new Label("Email");
      label.setFont(new Font("Arial", Font.BOLD, 11));
      label.setBounds(19, 19, 39, 22);
      frame.getContentPane().add(label);

      emailField = new JTextField();
      emailField.setFont(new Font("Dialog", Font.PLAIN, 11));
      emailField.setBounds(94, 18, 163, 20);
      frame.getContentPane().add(emailField);
      emailField.setColumns(10);

      passwordField = new JPasswordField();
      passwordField.setFont(new Font("Dialog", Font.PLAIN, 8));
      passwordField.setColumns(10);
      passwordField.setBounds(94, 46, 163, 20);
      frame.getContentPane().add(passwordField);

      Label label_1 = new Label("Password");
      label_1.setFont(new Font("Arial", Font.BOLD, 11));
      label_1.setBounds(18, 46, 63, 22);
      frame.getContentPane().add(label_1);

      submitButton = new JButton("Login");
      submitButton.setBounds(15, 79, 242, 23);
      frame.getContentPane().add(submitButton);

      addAction();
      frame.getRootPane().setDefaultButton(submitButton);
   }

   private void addAction() {
      LoginContainer container = new LoginContainer(frame, emailField,
            passwordField, submitButton);
      submitButton.addActionListener(new LoginAction(container));
   }

}
