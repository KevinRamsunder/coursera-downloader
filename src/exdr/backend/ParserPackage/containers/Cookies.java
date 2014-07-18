package exdr.backend.ParserPackage.containers;

import java.util.Map;

public class Cookies {

   private Map<String, String> cookies;

   public Cookies(Map<String, String> cookies) {
      this.cookies = cookies;
   }
   
   public Map<String, String> getCookies() {
      return cookies;
   }
}
