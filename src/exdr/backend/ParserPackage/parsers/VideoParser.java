package exdr.backend.ParserPackage.parsers;

import org.jsoup.nodes.Element;

public class VideoParser {
   private String title;
   private int downloadableItems;

   public VideoParser(Element video) {
      this.title = video.select("a").first().ownText();
      this.downloadableItems = video.select("div").first().select("a").size();
   }

   public String getTitle() {
      return this.title;
   }

   public int getQuantity() {
      return this.downloadableItems;
   }
}
