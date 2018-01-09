package novel_geter_reader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.sound.midi.Soundbank;

public class getTitle {
    public static String general_url = "https://ncode.syosetu.com";
    public static void main(String[] args) throws Exception {
        int target_id = 1;

        String target_ncode = "n9669bk";
        String target_url = general_url + "/" + target_ncode + "/";//home
        String target_subncode;
        int count = 0;

        Document doc = Jsoup.connect(target_url).get();

        Elements wikiUrl = doc.select("dd a");//url_get
        Elements title = doc.select("p.novel_title");//title_get


        System.out.println(title.text());
        for (Element element : wikiUrl) {

            target_subncode = element.attr("href");

            getSub(target_subncode);

            count+=1;
            if (count==10){
                break;
            }

//            System.out.println(target_subncode);
//            System.out.println(element.attr("href"));
        }
    }

    public static void getSub(String subncode) throws Exception{
        String text;
        String target_suburl=general_url+subncode;
        Document doc = Jsoup.connect(target_suburl).get();

        Elements subtitle = doc.select("p.novel_subtitle");//subtitle_get

        Elements newsHeadlines = doc.select("div.novel_view");

        text=newsHeadlines.toString().replaceAll("　","");
        text=text.replaceAll("<br>","");
        text=text.replaceAll("</div>","").replaceAll("<div>","").replaceAll("<div id=\"novel_honbun\" class=\"novel_view\">","");

        System.out.println("=====================================================================================");
        System.out.println(subtitle.text());
        System.out.println(text);

    }
}
