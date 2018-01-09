package novel_geter_reader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.*;


public class iroiro {
    public static void main(String[] args) throws Exception {
        Connection con = null;
        int target_id=1;
        String target_url="https://ncode.syosetu.com/n9669bk/";

        Document doc = Jsoup.connect(target_url).get();

        Elements wikiUrl = doc.select("dd a");
        Elements title =doc.select("p.novel_title");

        for (
                Element element : wikiUrl)

        {
            System.out.println(element);
        }





        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/novel", "root", "root");

            String sql = "INSERT INTO novel.test_fetch(target_ID, url, title) VALUES (?,?,?)";

            //PreparedStatementの作成
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "SAO");
            ResultSet rs = ps.executeQuery();

        } catch (
                SQLException e)

        {

            e.printStackTrace();
        } finally

        {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

}