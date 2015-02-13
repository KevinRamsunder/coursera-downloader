package exdr.backend.ParserPackage.containers;

import java.util.ArrayList;
import java.util.List;

import exdr.backend.ParserPackage.parsers.SectionParser;

public class SectionHeader extends Header {

   private List<VideoContent> sections;
   private String className;

   public SectionHeader(SectionParser parser, String className) {
      super(parser.getTitle(), parser.getQuantity());
      sections = new ArrayList<VideoContent>(quantity);
      this.className = className;
   }

   public List<VideoContent> getSectionList() {
      return this.sections;
   }

   public void add(VideoContent section) {
      sections.add(section);
   }
   
   public String getClassName() {
      return className;
   }

   public VideoContent getSection(int i) {
      return sections.get(i);
   }
}