package exdr.GUI.GUIDisplays.Results;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

public class InitResults {

   public InitResults(ResultsContainer c) {
      initFrame(c.frame);
      initLabel(c.frame);
      initButton(c);
      initScrollPane(c);
      initToggle(c);
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
      JLabel lblSelectGames = new JLabel("Select sections to download:");
      lblSelectGames.setFont(new Font("Arial", Font.BOLD, 11));
      lblSelectGames.setBounds(83, 11, 163, 16);
      frame.getContentPane().add(lblSelectGames);
   }

   private void initButton(ResultsContainer c) {
      c.submit.setEnabled(true);
      c.submit.setBounds(115, 255, 87, 23);
      c.frame.getContentPane().add(c.submit);
      c.frame.getRootPane().setDefaultButton(c.submit);
      c.submit.addActionListener(new ResultsAction(c));
   }

   private void initScrollPane(ResultsContainer c) {
      c.total = c.parser.structure.getStructure().size();
      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setFont(new Font("Arial", Font.PLAIN, 11));
      scrollPane.getVerticalScrollBar().setUnitIncrement(c.total);
      scrollPane.setBackground(UIManager.getColor("Button.background"));
      scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      int scroll = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
      scrollPane.setVerticalScrollBarPolicy(scroll);
      scrollPane.setBounds(8, 34, 305, 214);
      c.frame.getContentPane().add(scrollPane);
      JPanel panel = new JPanel();
      scrollPane.setViewportView(panel);
      int validResults = displayandGetNumberOfResults(c, panel);
      c.selectedTotal = validResults;
      panel.setLayout(new GridLayout(validResults, 1, 0, 0));
   }

   private void initToggle(ResultsContainer c) {
      c.toggle = new JToggleButton("All Off");
      c.toggle.setBounds(8, 255, 63, 23);
      c.toggle.addActionListener(new ToggleAction(c));
      c.frame.getContentPane().add(c.toggle);
   }

   private void initVisible(JFrame frame) {
      frame.setVisible(true);
   }

   private boolean isValidCB(ResultsContainer c, String title) {
      return c.courses.getMap().containsValue(title);
   }

   private int displayandGetNumberOfResults(ResultsContainer c, JPanel panel) {
      int validResults = 0;
      c.checkBox = new JCheckBox[c.total];

      for (int i = 0; i < c.checkBox.length; i++) {
         String title = c.parser.structure.access(i).getClassName();

         if (!isValidCB(c, title)) {
            continue;
         }

         validResults++;
         c.checkBox[i] = new JCheckBox(title + ": "
               + c.parser.structure.access(i).getTitle());
         c.checkBox[i].setSelected(true);
         c.checkBox[i].setFont(new Font("Arial", Font.PLAIN, 11));
         c.checkBox[i].addActionListener(new ResultsCheckboxAction(c.checkBox[i], c));
         panel.add(c.checkBox[i]);
      }

      return validResults;
   }
}