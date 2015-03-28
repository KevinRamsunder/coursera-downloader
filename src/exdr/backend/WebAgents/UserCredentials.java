package exdr.backend.WebAgents;

public class UserCredentials {

   /**
    * This class uses char[] to store sensitive user information because they
    * are cleared faster from the system's memory when compared to Strings.
    * 
    * This class supplies the webAgent with user credentials once and only once,
    * clearing the instance variables as soon as they are used//
    */

   private char[] email;
   private char[] password;

   public UserCredentials(char[] email, char[] password) {
      this.email = email;
      this.password = password;
   }

   public char[] getEmailOnce() {
      if (email == null) {
         return "Empty email.".toCharArray();
      } else {
         char[] t = this.email;
         this.email = null;
         return t;
      }
   }

   public char[] getPasswordOnce() {
      if (password == null) {
         return "Empty password.".toCharArray();
      } else {
         char[] t = this.password;
         this.password = null;
         return t;
      }
   }
}