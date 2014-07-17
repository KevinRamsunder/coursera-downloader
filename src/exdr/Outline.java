package exdr;

import exdr.backend.ParserPackage.MainParser;
import exdr.backend.WebAgents.UserCredentials;
import exdr.backend.WebAgents.UserWebAgent;

public class Outline {

   /**
    * Here is a basic outline of the program.
    * 
    * As it stands here, this program will not work: email, password and course
    * url need to be provided.
    * 
    * Generally, it is not a good idea to hard code sensitive user information
    * in code, therefore, it is STRONGLY recommended that you use the GUI. The
    * GUI is specifically designed for user interaction and alleviates the need
    * to hard code any information.
    */

   public Outline() {
      /**
       * first, initialize the web session with user credentials. char[]'s are
       * preferred for sensitive information.
       */
      char[] email = "".toCharArray();
      char[] pass = "".toCharArray();
      UserWebAgent agent = loginWebAgent(email, pass);

      /** clear fields immediately to secure user information. */
      email = null;
      pass = null;

      /**
       * pick the class you want to download from, navigate to the lecture page
       * which lists all of the downloads. Enter that url here.
       */
      String url = "https://class.coursera.org/course-title/lecture";
      MainParser parser = initParser(agent, url);

      /** Create a title for the folder which will contain the downloads. */
      String folderTitle = "FOLDER TITLE";

      /**
       * Choose where you want to create the folder and paste the path here. Be
       * sure to append the folderTitle correctly.
       */
      String baseDirectory = "C:\\Users\\Public\\" + folderTitle;

      /** Start the download... and wait. */
      initDownload(parser, baseDirectory);

      /**
       * Again, the above code serves as an outline for the program, it is
       * strongly recommended that you use the GUI which handles all of the
       * above for you.
       */
   }

   private static UserWebAgent loginWebAgent(char[] email, char[] pass) {
      System.out.println("Logging in to Coursera.\n");
      UserCredentials credentials = new UserCredentials(email, pass);
      UserWebAgent agent = new UserWebAgent(credentials);
      return agent;
   }

   private static MainParser initParser(UserWebAgent agent, String lecture) {
      return new MainParser(agent, lecture);
   }

   private static void initDownload(MainParser parser, String baseDirectory) {
      System.out.println("Download starting.\n");
      parser.structure.createDirectoriesAndDownload(baseDirectory);
   }
}
