package exdr.GUI.GUIDisplays;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

import exdr.GUI.GUISpecific.GUIMainParser;

public class ResultsGUI {

   private GUIMainParser parser;
   private JFrame frame;
   private JButton submit;
   private JCheckBox[] checkBox;
   private JToggleButton toggle;
   private int total;
   private boolean on = false;

   public ResultsGUI(GUIMainParser parser) {
      this.parser = parser;
      initialize();
      frame.setVisible(true);
   }

   public JFrame getFrame() {
      return this.frame;
   }

   private void initialize() {
      frame = new JFrame();
      frame.setTitle("Download");
      frame.setBounds(100, 100, 335, 315);
      frame.setLocationRelativeTo(null);
      frame.setResizable(false);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);

      JLabel lblSelectGames = new JLabel("Select sections to download:");
      lblSelectGames.setFont(new Font("Arial", Font.BOLD, 11));
      lblSelectGames.setBounds(83, 11, 163, 16);
      frame.getContentPane().add(lblSelectGames);

      submit = new JButton("Submit");
      submit.setEnabled(true);
      submit.setBounds(115, 255, 87, 23);
      frame.getContentPane().add(submit);

      total = parser.structure.getStructure().size();

      JScrollPane scrollPane = new JScrollPane();
      scrollPane.setFont(new Font("Arial", Font.PLAIN, 11));
      scrollPane.getVerticalScrollBar().setUnitIncrement(total);
      scrollPane.setBackground(UIManager.getColor("Button.background"));
      scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      int scroll = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
      scrollPane.setVerticalScrollBarPolicy(scroll);
      scrollPane.setBounds(8, 34, 305, 214);
      frame.getContentPane().add(scrollPane);

      JPanel panel = new JPanel();
      scrollPane.setViewportView(panel);
      panel.setLayout(new GridLayout(total, 1, 0, 0));

      toggle = new JToggleButton("All Off");
      toggle.setBounds(8, 255, 63, 23);
      toggle.addActionListener(new Toggle());
      frame.getContentPane().add(toggle);

      displayResults(parser, panel);
      submit.addActionListener(new Submit());
      frame.getRootPane().setDefaultButton(submit);
   }

   private void setButtonStatus() {
      if (total > 0) {
         submit.setEnabled(true);
      } else {
         submit.setEnabled(false);
      }
   }

   private void displayResults(GUIMainParser parser, JPanel panel) {
      checkBox = new JCheckBox[total];

      for (int i = 0; i < checkBox.length; i++) {
         checkBox[i] = new JCheckBox(parser.structure.access(i).getTitle());
         setCheckBox(checkBox[i], panel);
      }
   }

   private void setCheckBox(final JCheckBox checkBox, JPanel panel) {
      checkBox.setSelected(true);
      checkBox.setFont(new Font("Arial", Font.PLAIN, 11));
      checkBox.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent arg0) {
            if (checkBox.isSelected()) {
               total++;
            } else {
               total--;
            }
            setButtonStatus();
         }
      });
      panel.add(checkBox);
   }

   private class Submit implements ActionListener {

      @Override
      public void actionPerformed(ActionEvent arg0) {
         buttonState(false);

         DestinationGUI i = new DestinationGUI();
         String path = i.getPath();

         if (path == null) {
            submit.setEnabled(true);
            JOptionPane.showMessageDialog(null,
                  "You did not choose a destination.");
            return;
         }

         path += "\\" + parser.title;

         frame.dispose();
         new DownloaderGUI(parser.structure, path, checkboxToIntArray());
      }

      private int[] checkboxToIntArray() {
         int i = 0;
         int[] onValues = new int[total];

         for (int j = 0; j < checkBox.length; j++) {
            if (checkBox[j].isSelected()) {
               onValues[i++] = j;
            }
         }

         return onValues;
      }

      private void buttonState(final boolean enabled) {
         SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               submit.setEnabled(enabled);
            }
         });
      }
   }

   private class Toggle implements ActionListener {

      @Override
      public void actionPerformed(ActionEvent arg0) {
         for (JCheckBox i : checkBox) {
            i.setSelected(on);
         }

         toggle();
         setButtonStatus();
      }

      private void toggle() {
         on = !on;
         if (on) {
            toggle.setText("All On");
            total = 0;
         } else {
            toggle.setText("All Off");
            total = parser.structure.getStructure().size();
         }
      }
   }
}
