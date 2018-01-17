package novel_geter_reader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.sql.*;

public class adaaa {
    private static Connection con = null;

    public static void main(String[] args) {
        getEpisodeList();
    }

    private static void connectDB() {

        try {
            //SQL接続
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/novel", "root", "root");

            String sql = "INSERT INTO novel.test_fetch(target_ID, url, title) VALUES (?,?,?)";

            //PreparedStatementの作成
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "SAO");
            ResultSet rs = ps.executeQuery();

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
