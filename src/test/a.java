package test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;

public class a {
    public static void main(String[] args) throws Exception {
        String text;
        String url = "https://ncode.syosetu.com/n9669bk/1/";
        try {

            Document doc = Jsoup.connect("https://wadawa").get();
            Elements newsHeadlines = doc.select("div.novel_view");
            text = newsHeadlines.toString().replaceAll("　", "");
            text = text.replaceAll("<br>", "");
            text = text.replaceAll("</div>", "").replaceAll("<div>", "").replaceAll("<div id=\"novel_honbun\" class=\"novel_view\">", "");


            System.out.println(text);
        } catch (IOException e) {
            System.out.println("エラー");
        }
    }

}
