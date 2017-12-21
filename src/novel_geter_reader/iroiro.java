package novel_geter_reader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class iroiro {
    public static void main(String[] args) throws Exception {
        Document doc = Jsoup.connect("https://ncode.syosetu.com/n9669bk/").get();

        Elements wikiUrl = doc.select("dd a");

        for(Element element: wikiUrl){
            System.out.println(element);
        }
    }

}