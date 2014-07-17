package exdr.backend.ParserPackage.containers;

import java.util.ArrayList;
import java.util.List;

import exdr.backend.ParserPackage.parsers.VideoContentParser;

public class VideoContent extends Content {

   private List<Downloadable> downloads;

   public VideoContent(VideoContentParser parser) {
      super(parser.getTitle(), parser.getQuantity());
      downloads = new ArrayList<Downloadable>(super.downloadableItems);
   }

   public List<Downloadable> getDownloadables() {
      return downloads;
   }

   public void add(Downloadable d) {
      downloads.add(d);
   }

   public Downloadable getDownload(int i) {
      return downloads.get(i);
   }

}
