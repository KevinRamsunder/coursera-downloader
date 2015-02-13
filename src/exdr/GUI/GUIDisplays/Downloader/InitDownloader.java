package exdr.GUI.GUIDisplays.Downloader;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EtchedBorder;

public class InitDownloader {

   public InitDownloader(DownloaderContainer c) {
      initFrame(c.frame);
      initCount(c.frame, c.count);
      initScrollPane(c);
      initLabels(c.frame, c.label);
      initVisible(c.frame);

      initDownload(c);
   }

   private void initFrame(JFrame frame) {
      frame.setTitle("Download");
      frame.setBounds(100, 100, 335, 315);
      frame.setResizable(false);
      frame.setLocationRelativeTo(null);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().setLayout(null);
   }

   private void initCount(JFrame frame, JLabel count) {
      count.setFont(new Font("Arial", Font.BOLD, 11));
      count.setBounds(112, 259, 25, 14);
      frame.getContentPane().add(count);
   }

   private void initScrollPane(DownloaderContainer c) {
      JScrollPane scrollPane = new JScrollPane();
      int scroll = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
      scrollPane.setVerticalScrollBarPolicy(scroll);
      scrollPane.setFont(new Font("Arial", Font.PLAIN, 11));
      scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
      scrollPane.setBackground(SystemColor.menu);
      scrollPane.setBounds(8, 34, 305, 214);
      c.frame.getContentPane().add(scrollPane);
      scrollPane.setViewportView(c.panel);
      scrollPane.setAutoscrolls(true);
      c.panel.setLayout(new GridLayout(0, 1, 0, 0));
      addItemsToList(c);
   }

   private void initLabels(JFrame frame, JLabel label) {
      JLabel lblSelectGames = new JLabel("Downloading...");
      lblSelectGames.setFont(new Font("Arial", Font.BOLD, 11));
      lblSelectGames.setBounds(118, 12, 94, 16);
      frame.getContentPane().add(lblSelectGames);
      label = new JLabel("Items left.");
      label.setFont(new Font("Arial", Font.BOLD, 11));
      label.setBounds(132, 258, 94, 16);
      frame.getContentPane().add(label);
   }

   private void initVisible(JFrame frame) {
      frame.setVisible(true);
   }

   private void addItemsToList(DownloaderContainer c) {
      new DownloadListAction(c);
   }

   private void initDownload(final DownloaderContainer c) {
      Thread thread = new Thread() {
         @Override
         public void run() {
            new DownloadAction(c);
         }
      };
      thread.start();
   }
}