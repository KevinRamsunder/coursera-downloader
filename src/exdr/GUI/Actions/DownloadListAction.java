package exdr.GUI.Actions;

import javax.swing.JLabel;
import javax.swing.JPanel;

import exdr.GUI.Containers.DownloaderContainer;
import exdr.backend.ParserPackage.containers.LectureTree;

public class DownloadListAction {

   private LectureTree tree;
   private int[] locations;
   private final JPanel panel;
   private final JLabel count;

   public DownloadListAction(DownloaderContainer container) {
      this.tree = container.tree;
      this.locations = container.locations;
      this.panel = container.panel; 
      this.count = container.count;
      
      init();
   }

   private void init() {
      int r = 0;
      for (int i = 0; i < locations.length; i++) {
         int n = locations[i];
         for (int j = 0; j < tree.access(n).getQuantity(); j++) {
            for (int k = 0; k < tree.access(n, j).getDownloadableCount(); k++) {
               panel.add(new JLabel(tree.access(n, j, k).getTitle()));
               r++;
            }
         }
      }
      count.setText(String.valueOf(r));
   }
}
