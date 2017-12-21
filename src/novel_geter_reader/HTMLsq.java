package novel_geter_reader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HTMLsq {
    public static void main(String[] args) throws Exception{
        String text;
        Document doc = Jsoup.connect("https://ncode.syosetu.com/n9669bk/1/").get();
        Elements newsHeadlines = doc.select("div.novel_view");

        text=newsHeadlines.toString().replaceAll("　","");
        text=text.replaceAll("<br>","");
        text=text.replaceAll("</div>","").replaceAll("<div>","").replaceAll("<div id=\"novel_honbun\" class=\"novel_view\">","");


        System.out.println(text);
    }

}
