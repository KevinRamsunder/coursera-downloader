package exdr.GUI.GUIDisplays.CourseOverview;

import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

public class InitCourses {

   public InitCourses(CourseContainer c) {
      initFrame(c.frame);
      initLabel(c.frame);
      initButton(c);
      initScrollPane(c);
      initVisible(c.frame);
   }

   private void initFrame(JFrame frame) {
      frame.setTitle("Download");
      frame.setBounds(100, 100, 335, 315);
      frame.setLocationRelativeTo(null);
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);
   }

   private void initLabel(JFrame frame) {
      JLabel lblSelectGames = new JLabel("Select class sections:");
      lblSelectGames.setFont(new Font("Arial", Font.BOLD, 11));
      lblSelectGames.setBounds(83, 11, 163, 16);
      frame.getContentPane().add(lblSelectGames);
   }

   private void initButton(CourseContainer c) {
      c.submit.setText("Submit");
      c.submit.setEnabled(true);
      c.submit.setBounds(115, 255, 87, 23);
      c.frame.getContentPane().add(c.submit);
      c.frame.getRootPane().setDefaultButton(c.submit);
      c.submit.addActionListener(new CourseAction(c));
   }

   private void initScrollPane(CourseContainer c) {
      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setFont(new Font("Arial", Font.PLAIN, 11));
      scrollPane.getVerticalScrollBar().setUnitIncrement(5);
      scrollPane.setBackground(UIManager.getColor("Button.background"));
      scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      int scroll = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
      scrollPane.setVerticalScrollBarPolicy(scroll);
      scrollPane.setBounds(8, 34, 305, 214);
      c.frame.getContentPane().add(scrollPane);
      JPanel panel = new JPanel();
      scrollPane.setViewportView(panel);
      panel.setLayout(new GridLayout(5, 1, 0, 0));
      displayResults(panel, c);
   }

   private void initVisible(JFrame frame) {
      frame.setVisible(true);
   }

   private void displayResults(JPanel panel, CourseContainer c) {
      List<String> titles = c.courses.getTitles();

      for (int i = 0; i < c.courses.getSize(); i++) {
         c.checkbox[i] = new JCheckBox(titles.get(i));
         c.checkbox[i].addActionListener(new CourseCheckboxAction(
               c.checkbox[i], c));
         setCheckBox(c.checkbox[i], panel);
      }
   }

   private void setCheckBox(final JCheckBox checkBox, JPanel panel) {
      checkBox.setSelected(true);
      checkBox.setFont(new Font("Arial", Font.PLAIN, 11));
      panel.add(checkBox);
   }
}