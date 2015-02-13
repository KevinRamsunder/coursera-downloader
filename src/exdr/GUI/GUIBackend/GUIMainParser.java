package exdr.GUI.GUIBackend;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import exdr.backend.ParserPackage.containers.Cookies;
import exdr.backend.ParserPackage.containers.Courses;
import exdr.backend.ParserPackage.containers.Downloadable;
import exdr.backend.ParserPackage.containers.LectureTree;
import exdr.backend.ParserPackage.containers.SectionHeader;
import exdr.backend.ParserPackage.containers.VideoContent;
import exdr.backend.ParserPackage.parsers.DLParser;
import exdr.backend.ParserPackage.parsers.SectionParser;
import exdr.backend.ParserPackage.parsers.VideoParser;
import exdr.backend.Strings.ParsingKeys;

public class GUIMainParser {

   /*
    * An absolute mess.. will refactor soon..
    */

   public LectureTree structure;

   public GUIMainParser(GUIUserWebAgent agent, Courses courses)
         throws Exception {
      Document doc;
      String HTML = null, url = null, title = null;
      Elements masterList = null, div = null, ul = null, li = null, a = null;

      structure = new LectureTree();

      for (int c = 0; c < courses.getSize(); c++) {
         url = courses.getLink(c) + "lecture";
         title = url.substring(url.indexOf('/', 10) + 1, url.lastIndexOf('/'));
         HTML = getHTML(agent, url);
         doc = Jsoup.parse(HTML);

         masterList = doc.select(ParsingKeys.masterKey);
         div = masterList.select(ParsingKeys.div);
         ul = masterList.select("ul");
         li = ul.select("li");
         a = li.select("div").select("a");

         int liCounter = 0;
         int aCounter = 0;

         List<SectionHeader> weeks = new ArrayList<SectionHeader>();
         Cookies cookies = new Cookies(agent.getCookies());

         for (int i = 0; i < div.size(); i++) {
            SectionParser p1 = new SectionParser(div.get(i), ul.get(i));
            weeks.add(new SectionHeader(p1, title));

            for (int j = 0; j < p1.getQuantity(); j++) {
               VideoParser p2 = new VideoParser(li.get(liCounter++));
               weeks.get(i).add(new VideoContent(p2));

               for (int k = 0; k < p2.getQuantity(); k++) {
                  DLParser p3 = new DLParser(a.get(aCounter++));
                  weeks.get(i).getSection(j).add(new Downloadable(p3, cookies));
               }
            }
         }

         structure.addWeeks(weeks);
      }
   }

   private String getHTML(GUIUserWebAgent agent, String url) throws Exception {
      String html = agent.getCoursePageForParsing(url);
      int index = html.indexOf("<div class=\"course-item-list\">");

      if (index == -1) {
         return html;
      } else {
         return html.substring(index);
      }
   }
}
