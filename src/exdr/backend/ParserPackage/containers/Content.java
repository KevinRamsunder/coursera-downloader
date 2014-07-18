package exdr.backend.ParserPackage.containers;

public abstract class Content {

   protected String title;
   protected int downloadableItems;

   public Content(String title, int downloadableItems) {
      this.title = clearInvalidChars(title);
      this.downloadableItems = downloadableItems;
   }

   private String clearInvalidChars(String title) {
      return title.replaceAll("\\\\|\\/|\\:|\\*|\\?|\"|\\<|\\>|\\|", ".");
   }

   public String getTitle() {
      return this.title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public int getDownloadableCount() {
      return downloadableItems;
   }
}
