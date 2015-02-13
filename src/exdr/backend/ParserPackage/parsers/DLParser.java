package exdr.backend.ParserPackage.parsers;

import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.nodes.Element;

public class DLParser {

   private String title;
   private URL url;

   public DLParser(Element a) {
      this.title = a.select("div").first().ownText();

      try {
         this.url = new URL(a.attr("href"));
      } catch (MalformedURLException e) {
         return;
      }
   }

   public String getTitle() {
      return this.title;
   }

   public URL getURL() {
      return this.url;
   }
}
