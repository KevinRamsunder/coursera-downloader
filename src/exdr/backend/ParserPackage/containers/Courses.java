package exdr.backend.ParserPackage.containers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

import exdr.backend.Strings.ParsingKeys;

public class Courses {

   private int size = 0;
   private List<String> titles;
   private List<String> links;
   private HashMap<Integer, String> map;

   public Courses(HtmlPage page) {
      new CourseListParser(page);
      map = new HashMap<Integer, String>(size);
   }

   public int getSize() {
      return size;
   }

   public List<String> getTitles() {
      return titles;
   }

   public List<String> getLinks() {
      return links;
   }

   public String getTitle(int i) {
      return titles.get(i);
   }

   public String getLink(int i) {
      return links.get(i);
   }

   public void addToMap(int i, String j) {
      map.put(i, j);
   }

   public HashMap<Integer, String> getMap() {
      return map;
   }

   private class CourseListParser {

      public CourseListParser(HtmlPage page) {
         titles = new ArrayList<String>();
         links = new ArrayList<String>();
         init(page);
      }

      private void init(HtmlPage page) {
         Document d = Jsoup.parse(page.asXml());
         Elements t = d.select(ParsingKeys.courseTitle);
         Elements e = d.select(ParsingKeys.courseList);

         if (t.size() != e.size()) {
            System.exit(0);
         }

         for (int i = 0; i < e.size(); i++) {
            String title = t.get(i).ownText();
            String link = e.get(i).absUrl("href");

            // only include this course if a URL is found
            if (link == null || link.length() < 15) {
               continue;
            } else {
               titles.add(title);
               links.add(link);
               size++;
            }
         }
      } 
   }
}