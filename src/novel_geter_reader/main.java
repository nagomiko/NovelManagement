package novel_geter_reader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class main {



    public static void main(String[] args) {
        getEpisodeList();
    }

    private static void getEpisodeList(){
        try {
            Document doc = Jsoup.connect("https://ncode.syosetu.com/n9669bk/").get();

            Elements wikiUrl = doc.select("dd a");

            for (Element element : wikiUrl) {
                System.out.println(element);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
