package exdr.backend.WebAgents;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;

import exdr.backend.Strings.Html;
import exdr.backend.Strings.UserNotifications;

public class UserWebAgent extends WebAgent {

   private Map<String, String> cookies;

   public UserWebAgent(UserCredentials loginInformation) {
      super(loginInformation);
      createSessionID();
   }

   private void createSessionID() {
      this.cookies = new HashMap<String, String>();

      for (Cookie i : webClient.getCookieManager().getCookies()) {
         cookies.put(i.getName(), i.getValue());
      }
   }

   public Map<String, String> getCookies() {
      return this.cookies;
   }

   public String getCoursePageForParsing(String lectureURL) {
      HtmlPage page = null;

      parseAndVerifyURL(lectureURL);

      try {
         page = webClient.getPage(lectureURL);
      } catch (FailingHttpStatusCodeException | IOException e) {
         e.printStackTrace();
      }

      verifyHtmlResultPage(page);

      return page.asXml();
   }

   private void parseAndVerifyURL(String lectureURL) {
      if (!verifyURL(lectureURL)) {
         throw new IllegalArgumentException(UserNotifications.badURL);
      }
   }

   private boolean verifyURL(String lectureURL) {
      String temp = lectureURL;
      temp = temp.substring((temp.lastIndexOf('/')));
      return temp.equalsIgnoreCase(Html.endingOfURL);
   }

   private void verifyHtmlResultPage(HtmlPage page) {
      if (!accessGranted(page)) {
         throw new IllegalArgumentException(UserNotifications.accessDenied);
      }
   }

   private boolean accessGranted(HtmlPage page) {
      return page.asXml().contains(Html.validPageKey);
   }

}
