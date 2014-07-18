package exdr.GUI.GUIDisplays;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.text.DefaultCaret;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;

import exdr.GUI.GUISpecific.GUIMainParser;
import exdr.GUI.GUISpecific.GUIUserWebAgent;
import exdr.backend.Strings.GUINotifications;
import exdr.backend.Strings.UserNotifications;

public class EnterUrlGUI {

   private GUIUserWebAgent agent;
   private JFrame frame;
   private JTextField textField;

   public EnterUrlGUI(GUIUserWebAgent agent) {
      this.agent = agent;
      initialize();
      this.frame.setVisible(true);
   }

   public JFrame getFrame() {
      return this.frame;
   }

   private void initialize() {
      frame = new JFrame();
      frame.setTitle("Select Lecture");
      frame.setResizable(false);
      frame.setBounds(100, 100, 275, 145);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);

      JTextArea textArea = new JTextArea();
      String t = "Enter course lecture URL. Example:\nwww.class.coursera.org/class-title/lecture";
      textArea.setText(t);
      textArea.setBounds(12, 11, 242, 33);
      frame.getContentPane().add(textArea);
      DefaultCaret caret = (DefaultCaret) textArea.getCaret();
      caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
      textArea.setLineWrap(true);
      textArea.setWrapStyleWord(true);
      textArea.setEditable(false);
      textArea.setBackground(UIManager.getColor("Button.background"));
      textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      textArea.setVisible(true);
      textArea.setFont(new Font("Arial", Font.PLAIN, 11));

      textField = new JTextField();
      textField.setFont(new Font("Dialog", Font.PLAIN, 11));
      textField.setColumns(10);
      textField.setBounds(12, 53, 242, 20);
      frame.getContentPane().add(textField);

      JButton button = new JButton("Submit");
      button.setBounds(12, 82, 242, 23);
      frame.getContentPane().add(button);
      button.addActionListener(new ButtonClick());
      frame.getRootPane().setDefaultButton(button);
   }

   private class ButtonClick implements ActionListener {

      @Override
      public void actionPerformed(ActionEvent e) {
         String html = null;
         String url = textField.getText();

         try {
            html = agent.getCoursePageForParsing(url);
         } catch (IOException | IllegalArgumentException e1) {
            processError(e1.getMessage());
            return;
         } catch (FailingHttpStatusCodeException e1) {
            promptNotFound();
            return;
         } catch (Exception e1) {
            promptBadInput();
            return;
         } finally {
            textField.setText("");
         }

         GUIMainParser data = new GUIMainParser(agent, html, url);
         new ResultsGUI(data);
         frame.dispose();
      }

      private void processError(String error) {
         if (error.equals(UserNotifications.badURL)) {
            JOptionPane.showMessageDialog(null, GUINotifications.invalid);
         } else if (error.equals(UserNotifications.accessDenied)) {
            JOptionPane.showMessageDialog(null, GUINotifications.noAccess);
         } else {
            JOptionPane.showMessageDialog(null, GUINotifications.notFound);
         }
      }

      private void promptNotFound() {
         JOptionPane.showMessageDialog(null, GUINotifications.notFound);
      }

      private void promptBadInput() {
         JOptionPane.showMessageDialog(null, GUINotifications.wrong);
      }
   }
}
