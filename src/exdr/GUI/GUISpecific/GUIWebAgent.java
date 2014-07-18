package exdr.GUI.GUISpecific;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.IncorrectnessListener;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptJobManager;
import com.gargoylesoftware.htmlunit.util.Cookie;

import exdr.backend.Strings.Html;
import exdr.backend.Strings.UserNotifications;
import exdr.backend.WebAgents.UserCredentials;

public abstract class GUIWebAgent {

   protected WebClient webClient;
   private HtmlPage page;

   public GUIWebAgent(UserCredentials loginInformation)
         throws FailingHttpStatusCodeException, MalformedURLException,
         IOException {
      initWebClient();
      connectToWebsite();
      waitForPageToLoad();
      loginUser(loginInformation);

      if (!verifyUser()) {
         throw new IllegalArgumentException(UserNotifications.loginFailed);
      }

      page = null;
      setJavascriptEnabled(false);
   }

   private void setJavascriptEnabled(boolean enabled) {
      webClient.getOptions().setJavaScriptEnabled(enabled);
   }

   private void initWebClient() {
      webClient = new WebClient(BrowserVersion.FIREFOX_17);
      webClient.getOptions().setCssEnabled(false);
      webClient.getOptions().setJavaScriptEnabled(true);
      webClient.getOptions().setThrowExceptionOnScriptError(false);
      webClient.getCookieManager().setCookiesEnabled(true);
      webClient.setIncorrectnessListener(new IncorrectnessListener() {

         @Override
         public void notify(String arg0, Object arg1) {

         }
      });
   }

   private void connectToWebsite() throws FailingHttpStatusCodeException,
         MalformedURLException, IOException {
      page = webClient.getPage(Html.url);
   }

   private void waitForPageToLoad() {
      JavaScriptJobManager manager = page.getEnclosingWindow().getJobManager();

      while (manager.getJobCount() > 0) {
         webClient.waitForBackgroundJavaScript(1000);
      }
   }

   private void loginUser(UserCredentials loginInformation) throws IOException {
      HtmlElement emailField = (HtmlElement) page.getElementById(Html.email);
      HtmlElement passwordField = (HtmlElement) page
            .getElementById(Html.password);
      HtmlElement button = (HtmlElement) page.getFirstByXPath(Html.button);

      ((HtmlTextInput) emailField).setValueAttribute(String
            .valueOf(loginInformation.getEmailOnce()));

      ((HtmlPasswordInput) passwordField).setValueAttribute(String
            .valueOf(loginInformation.getPasswordOnce()));

      button.click();
      webClient.waitForBackgroundJavaScript(5000);
   }

   private boolean verifyUser() {
      Cookie i = webClient.getCookieManager().getCookie(
            UserNotifications.cookieVerify);

      return i != null;
   }
}
