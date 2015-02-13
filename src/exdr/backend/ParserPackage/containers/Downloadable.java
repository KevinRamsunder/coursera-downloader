package exdr.backend.ParserPackage.containers;

import java.net.URL;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import exdr.backend.ParserPackage.parsers.DLParser;

public class Downloadable {

   protected String title;
   protected URL url;
   private Cookies cookies;

   public Downloadable(DLParser parser, Cookies cookies) {
      this.title = clearInvalidChars(parser.getTitle());
      this.url = parser.getURL();
      this.cookies = cookies;
   }

   public String getTitle() {
      return this.title;
   }

   private String clearInvalidChars(String title) {
      return title.replaceAll("\\\\|\\/|\\:|\\*|\\?|\"|\\<|\\>|\\|", ".");
   }

   public URL getURL() {
      modifyVideoURL();
      return url;
   }

   private void modifyVideoURL() {
      if (url == null) {
         return;
      }

      String URL = url.toString();
      if (URL != null && URL.contains(".mp4")) {
         modifyWithServerResponse();
      }
   }

   private void modifyWithServerResponse() {
      Response r = null;

      try {
         r = Jsoup.connect(url.toString()).ignoreContentType(true)
               .cookies(cookies.getCookies()).execute();
      } catch (Exception e) {
         return;
      }

      
      if (r != null && r.url() != null) {
         this.url = r.url();
      } else {
         this.url = null;
      }
   }
}