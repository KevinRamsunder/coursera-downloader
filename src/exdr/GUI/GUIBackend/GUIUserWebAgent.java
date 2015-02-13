package exdr.GUI.GUIBackend;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.Cookie;

import exdr.backend.ParserPackage.containers.Courses;
import exdr.backend.Strings.Html;
import exdr.backend.Strings.UserNotifications;
import exdr.backend.WebAgents.UserCredentials;

public class GUIUserWebAgent extends GUIWebAgent {

   private Map<String, String> cookies;

   public GUIUserWebAgent(UserCredentials loginInformation)
         throws FailingHttpStatusCodeException, MalformedURLException,
         IOException, IllegalArgumentException {
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

   private boolean verifyURL(String lectureURL) {
      String temp = lectureURL;
      temp = temp.substring((temp.lastIndexOf('/')));
      return temp.equalsIgnoreCase(Html.endingOfURL);
   }

   private boolean accessGranted(HtmlPage page) {
      return page.asXml().contains(Html.validPageKey);
   }

   public String getCoursePageForParsing(String lectureURL)
         throws FailingHttpStatusCodeException, MalformedURLException,
         IOException, IllegalArgumentException {
      HtmlPage page = null;

      if (!verifyURL(lectureURL)) {
         throw new IllegalArgumentException(UserNotifications.badURL);
      }

      setJavascriptEnabled(false);
      page = webClient.getPage(lectureURL);

      if (!accessGranted(page)) {
         throw new IllegalArgumentException(UserNotifications.accessDenied);
      }

      return page.asXml();
   }

   public Courses getCurrentCourses() throws FailingHttpStatusCodeException,
         MalformedURLException, IOException {
      setJavascriptEnabled(true);
      HtmlPage page = webClient.getPage(Html.mainUrl);
      waitForPageToLoad(page);
      setJavascriptEnabled(false);
      return new Courses(page);
   }
}