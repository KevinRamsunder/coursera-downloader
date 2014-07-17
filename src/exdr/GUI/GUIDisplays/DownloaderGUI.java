package exdr.GUI.GUIDisplays;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;

import exdr.GUI.Actions.DownloadAction;
import exdr.GUI.Actions.DownloadListAction;
import exdr.GUI.Containers.DownloaderContainer;
import exdr.backend.ParserPackage.containers.LectureTree;

public class DownloaderGUI {

   private JFrame frame;
   private LectureTree structure;
   private int[] locations;
   private JPanel panel;
   private String absPath;
   private JLabel count;
   private JLabel label;

   public DownloaderGUI(LectureTree structure, String absPath, int[] checked) {
      this.structure = structure;
      this.locations = checked;
      this.absPath = absPath;
      initialize();
   }

   private void initialize() {
      frame = new JFrame();
      frame.setTitle("Download");
      frame.setBounds(100, 100, 335, 315);
      frame.setResizable(false);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);
      frame.setVisible(true);

      count = new JLabel();
      count.setFont(new Font("Arial", Font.BOLD, 11));
      count.setBounds(112, 259, 25, 14);
      frame.getContentPane().add(count);

      JScrollPane scrollPane = new JScrollPane();
      int scroll = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
      scrollPane.setVerticalScrollBarPolicy(scroll);
      scrollPane.setFont(new Font("Arial", Font.PLAIN, 11));
      scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      scrollPane.setBackground(SystemColor.menu);
      scrollPane.setBounds(8, 34, 305, 214);
      frame.getContentPane().add(scrollPane);

      JLabel lblSelectGames = new JLabel("Downloading...");
      lblSelectGames.setFont(new Font("Arial", Font.BOLD, 11));
      lblSelectGames.setBounds(118, 12, 94, 16);
      frame.getContentPane().add(lblSelectGames);

      panel = new JPanel();
      scrollPane.setViewportView(panel);
      scrollPane.setAutoscrolls(true);
      panel.setLayout(new GridLayout(0, 1, 0, 0));

      addItemsToList(panel);

      label = new JLabel("Items left.");
      label.setFont(new Font("Arial", Font.BOLD, 11));
      label.setBounds(132, 258, 94, 16);
      frame.getContentPane().add(label);

      initDownload();
   }

   private DownloaderContainer getContainer() {
      return new DownloaderContainer(structure, panel, count, absPath,
            locations);
   }

   private void addItemsToList(final JPanel panel) {
      new DownloadListAction(getContainer());
   }

   private void initDownload() {
      Thread thread = new Thread() {
         @Override
         public void run() {
            new DownloadAction(getContainer());
         }
      };
      thread.start();
   }
}
