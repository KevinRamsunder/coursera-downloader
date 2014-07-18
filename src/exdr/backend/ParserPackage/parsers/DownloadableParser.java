package exdr.backend.ParserPackage.parsers;

import java.net.MalformedURLException;
import java.net.URL;

import org.jsoup.nodes.Element;

public class DownloadableParser {

   private String title;
   private URL url;

   public DownloadableParser(Element a) {
      this.title = a.select("div").first().ownText();

      try {
         this.url = new URL(a.attr("href"));
      } catch (MalformedURLException e) {
         e.printStackTrace();
      }
   }

   public String getTitle() {
      return this.title;
   }

   public URL getURL() {
      return this.url;
   }
}
