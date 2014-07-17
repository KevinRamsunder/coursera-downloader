package exdr.backend.ParserPackage.containers;

import java.io.IOException;
import java.net.URL;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import exdr.backend.ParserPackage.parsers.DownloadableParser;

public class Downloadable {

   protected String title;
   protected URL url;
   private Cookies cookies;

   public Downloadable(DownloadableParser parser, Cookies cookies) {
      this.title = clearInvalidChars(parser.getTitle());
      this.url = parser.getURL();
      this.cookies = cookies;
   }

   public String getTitle() {
      return title;
   }

   private String clearInvalidChars(String title) {
      return title.replaceAll("\\\\|\\/|\\:|\\*|\\?|\"|\\<|\\>|\\|", ".");
   }

   public URL getURL() {
      modifyVideoURL();
      return url;
   }

   private void modifyVideoURL() {
      if (url.toString().contains(".mp4")) {
         modifyWithServerResponse();
      }
   }

   private void modifyWithServerResponse() {
      Response r = null;

      try {
         r = Jsoup.connect(url.toString()).ignoreContentType(true)
               .cookies(cookies.getCookies()).execute();
      } catch (IOException e) {
         e.printStackTrace();
      }

      this.url = r.url();
   }

}
