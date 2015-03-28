package exdr.GUI.GUIBackend;

import java.io.IOException;
import java.net.MalformedURLException;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.IncorrectnessListener;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
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

   protected void setJavascriptEnabled(boolean enabled) {
      webClient.getOptions().setJavaScriptEnabled(enabled);
   }

   private void initWebClient() {
      webClient = new WebClient(BrowserVersion.FIREFOX_24);
      webClient.getOptions().setCssEnabled(false);
      webClient.setAjaxController(new NicelyResynchronizingAjaxController());
      webClient.getOptions().setJavaScriptEnabled(true);
      webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
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

   protected void waitForPageToLoad() {
      JavaScriptJobManager manager = page.getEnclosingWindow().getJobManager();

      while (manager.getJobCount() > 0) {
         webClient.waitForBackgroundJavaScript(1250);
      }
   }

   protected void waitForPageToLoad(HtmlPage page) {
      JavaScriptJobManager manager = page.getEnclosingWindow().getJobManager();

      while (manager.getJobCount() > 0) {
         webClient.waitForBackgroundJavaScript(1250);
      }
   }

   private void loginUser(UserCredentials loginInformation) throws IOException {
      setEmailField(loginInformation.getEmailOnce());
      setPasswordField(loginInformation.getPasswordOnce());
      getSubmitButton().click();
      webClient.waitForBackgroundJavaScript(5000);
   }

   private void setEmailField(char[] email) {
      HtmlElement i = (HtmlElement) page.getElementById(Html.email);
      ((HtmlTextInput) i).setValueAttribute(String.valueOf(email));
   }

   private void setPasswordField(char[] pass) {
      HtmlElement i = (HtmlElement) page.getElementById(Html.password);
      ((HtmlPasswordInput) i).setValueAttribute(String.valueOf(pass));
   }

   private HtmlElement getSubmitButton() {
      return (HtmlElement) page.getFirstByXPath(Html.button);
   }

   private boolean verifyUser() {
      Cookie i = webClient.getCookieManager().getCookie(
            UserNotifications.cookieVerify);
      return i != null;
   }
}