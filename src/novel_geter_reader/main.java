package novel_geter_reader;

import com.sun.xml.internal.bind.v2.model.core.ID;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.sql.*;

public class main {
    private static String general_url = "https://ncode.syosetu.com";
//    private static String target_ncode = "n9669bk";

//    private static String target_ncode = "n8611bv";

    private static String target_ncode = "n6316bn";

    private static int target_id = 3;

    private static Connection con = null;
    private static int no = 0;
    private static int is_read = 0;


    public static void main(String[] args) {
        conDB();
    }

    private static void conDB() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // MySQLに接続
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/novel", "root", "root");
            System.out.println("MySQLに接続できました。");

            getTitle();
//            タイトル取得

        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.out.println("JDBCドライバのロードに失敗しました。");
        } catch (SQLException e) {
            System.out.println("MySQLに接続できませんでした。");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println("MySQLのクローズに失敗しました。");
                }
            }
            System.out.println("終了");
        }
    }


    private static void getTitle() throws Exception {
        String target_url = general_url + "/" + target_ncode + "/";//home
        int count = 0;
        String page_data = "メインページ";

        Document doc = Jsoup.connect(target_url).get();

        Elements wikiUrl = doc.select("dd a");//url_get
        Elements title = doc.select("p.novel_title");//title_get

        insertEpisode(target_id, no, target_url, title.text(), page_data, is_read);
//        DBに書き込み
        insertFetch(target_url,title.text());

        for (Element element : wikiUrl) {
            getSub(element.attr("href"));
//            各話を取得
            count += 1;

            if (count == 3) {
//                テスト用　10回で終了
                break;
            }
        }
    }


    private static void getSub(String subncode) throws Exception {
        String text;
        no += 1;
        String target_suburl = general_url + subncode;
        Document doc = Jsoup.connect(target_suburl).get();

        Elements subtitle = doc.select("p.novel_subtitle");//subtitle_get

        Elements newsHeadlines = doc.select("div.novel_view");

        text = newsHeadlines.toString().replaceAll("　", "").replaceAll("<br>", "").replaceAll("</div>", "").replaceAll("<div>", "").replaceAll("<div id=\"novel_honbun\" class=\"novel_view\">", "");

        insertEpisode(target_id, no, target_suburl, subtitle.text(), text, is_read);


    }

    private static void insertEpisode(int ID, int no, String url, String title, String page_data, int is_read) {

        String sql = "INSERT INTO test_episode(target_ID, no, url, title, page_data, is_read)  VALUES (?,?,?,?,?,?)";

        //PreparedStatementの作成
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, ID);
            ps.setInt(2, no);
            ps.setString(3, url);
            ps.setString(4, title);
            ps.setString(5, page_data);
            ps.setInt(6, is_read);
            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
//        ps.setString(1,"SAO");
        //ResultSet rs=ps.executeQuery();
    }

    private static void insertFetch(String url,String title){

        String sql="INSERT INTO test_fetch(url,title) VALUES (?,?)";

        try {
            PreparedStatement ps =con.prepareStatement(sql);

            ps.setString(1,url);
            ps.setString(2,title);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}

