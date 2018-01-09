package novel_geter_reader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class outTitle_novel {
    private static Connection con=null;

    public static void main(String[] args) {
        conDB();
    }

    private static void conDB() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // MySQLに接続
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/novel", "root", "root");
            System.out.println("MySQLに接続できました。");


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




}
