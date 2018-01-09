package novel_geter_reader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class yara {
    public static void main(String[] args) throws Exception{
        String text;
        Document doc = Jsoup.connect("http://yaranaioblog.blog14.fc2.com/blog-entry-7389.html").get();
        Elements newsHeadlines = doc.select("div.entry_main");

        text=newsHeadlines.toString().replaceAll("　","");
        text=text.replaceAll("<br>","");
        text=text.replaceAll("</div>","").replaceAll("<div>","").replaceAll("<div id=\"novel_honbun\" class=\"novel_view\">","");


        System.out.println(text);
    }

}
