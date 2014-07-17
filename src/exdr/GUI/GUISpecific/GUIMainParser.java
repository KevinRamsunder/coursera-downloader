package exdr.GUI.GUISpecific;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import exdr.backend.ParserPackage.containers.Cookies;
import exdr.backend.ParserPackage.containers.Downloadable;
import exdr.backend.ParserPackage.containers.LectureTree;
import exdr.backend.ParserPackage.containers.SectionHeader;
import exdr.backend.ParserPackage.containers.VideoContent;
import exdr.backend.ParserPackage.parsers.DownloadableParser;
import exdr.backend.ParserPackage.parsers.SectionHeaderParser;
import exdr.backend.ParserPackage.parsers.VideoContentParser;
import exdr.backend.Strings.ParsingKeys;

public class GUIMainParser {

   public String title;
   public LectureTree structure;

   public GUIMainParser(GUIUserWebAgent agent, String HTML, String url) {
      Document doc = Jsoup.parse(HTML);
      title = url.substring(url.indexOf('/', 10) + 1, url.lastIndexOf('/'));

      Elements masterList = doc.select(ParsingKeys.masterKey);
      Elements div = masterList.select(ParsingKeys.div);
      Elements ul = masterList.select("ul");
      Elements li = ul.select("li");
      Elements a = li.select("div").select("a");

      int liCounter = 0;
      int aCounter = 0;

      List<SectionHeader> weeks = new ArrayList<SectionHeader>();
      Cookies cookies = new Cookies(agent.getCookies());

      for (int i = 0; i < div.size(); i++) {
         SectionHeaderParser p1 = new SectionHeaderParser(div.get(i), ul.get(i));
         weeks.add(new SectionHeader(p1));

         for (int j = 0; j < p1.getQuantity(); j++) {
            VideoContentParser p2 = new VideoContentParser(li.get(liCounter++));
            weeks.get(i).add(new VideoContent(p2));

            for (int k = 0; k < p2.getQuantity(); k++) {
               DownloadableParser p3 = new DownloadableParser(a.get(aCounter++));
               weeks.get(i).getSection(j).add(new Downloadable(p3, cookies));
            }
         }
      }

      structure = new LectureTree(weeks);
   }
}
