package exdr.backend.ParserPackage.parsers;

import org.jsoup.nodes.Element;

public class SectionParser {
   private String title;
   private int quantity;

   public SectionParser(Element div, Element ul) {
      this.title = div.select("h3").first().ownText().substring(1);
      this.quantity = ul.select("li").size();
   }

   public String getTitle() {
      return this.title;
   }

   public int getQuantity() {
      return this.quantity;
   }
}
