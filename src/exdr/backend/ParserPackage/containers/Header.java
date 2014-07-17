package exdr.backend.ParserPackage.containers;

public abstract class Header {

   protected String title;
   protected int quantity;

   public Header(String title, int quantity) {
      this.title = clearInvalidChars(title);
      this.quantity = quantity;
   }

   private String clearInvalidChars(String title) {
      return title.replaceAll("\\\\|\\/|\\:|\\*|\\?|\"|\\<|\\>|\\|", ".");
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getTitle() {
      return title;
   }

   public int getQuantity() {
      return quantity;
   }

}
