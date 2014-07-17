package exdr.backend.ParserPackage.containers;

import java.util.ArrayList;
import java.util.List;

import exdr.backend.ParserPackage.parsers.SectionHeaderParser;

public class SectionHeader extends Header {

   private List<VideoContent> sections;

   public SectionHeader(SectionHeaderParser parser) {
      super(parser.getTitle(), parser.getQuantity());
      sections = new ArrayList<VideoContent>(quantity);
   }

   public List<VideoContent> getSectionList() {
      return sections;
   }

   public void add(VideoContent section) {
      sections.add(section);
   }

   public VideoContent getSection(int i) {
      return sections.get(i);
   }

}
