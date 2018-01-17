package novel;

import com.mysql.jdbc.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/novel/AddRecord"})
public class AddRecord extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // リクエストパラメーターの取得
        request.setCharacterEncoding("UTF-8");
        String title = request.getParameter("title");
        String ncode = request.getParameter("ncode");
        String pagedata = request.getParameter("pageData");
        int No = Integer.parseInt(request.getParameter("No"));
        String URL = "YourNovelData/" + ncode;

        // 登録するデータの情報を設定
        NovelData entryData = new NovelData(4, No, URL, title, pagedata, 0);

        // セッションスコープに登録データを保存
        HttpSession session = request.getSession();
        session.setAttribute("entryData", entryData);

        // フォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("ConfirmEntry.jsp");
        dispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Connection con = null;

        // フォワード先
        String forwardPath = null;

        // サーブレットクラスの動作を決定する「action」の値を
        String action = request.getParameter("action");

        // 「登録の開始」をリクエストされた時の処理
        if (action == null) {
            // フォワード先を指定
            forwardPath = "AddYourNovel.jsp";
        }

        // 登録確認画面から「登録実行」をリクエストされた時の処理
        else if (action.equals("done")) {
            // セッションスコープに保存された登録ユーザーを取得
            HttpSession session = request.getSession();
            NovelData entryData = (NovelData) session.getAttribute("entryData");

            // 登録処理

            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            //データベースへの接続
            String driverUrl = "jdbc:mysql://localhost:3306/novel";
            try {
                con = DriverManager.getConnection(driverUrl, "root", "root");
            } catch (SQLException e) {
                e.printStackTrace();
            }

            String sql = "INSERT INTO test_episode(target_ID, no, url, title, page_data, is_read)  VALUES (?,?,?,?,?,?)";

            //PreparedStatementの作成
            try {
                assert con != null;
                java.sql.PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, entryData.getTarget_ID());
                ps.setInt(2, entryData.getNo());
                ps.setString(3, entryData.getUrl());
                ps.setString(4, entryData.getTitle());
                ps.setString(5, entryData.getPageData());
                ps.setInt(6, entryData.getIsRead());
                ps.executeUpdate();


                String sql2 = "INSERT INTO test_fetch(url,title) VALUES (?,?,?)";
                java.sql.PreparedStatement ps1 = con.prepareStatement(sql2);
                ps1.setString(1, entryData.getUrl());
                ps1.setString(2, entryData.getTitle());
                ps1.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                //例外が発生する・しないにかかわらず確実にデータベースから切断
                if (con != null) {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        throw new ServletException(e);
                    }
                }
            }
//


            // 不要となったセッションスコープ内のインスタンスを削除
            session.removeAttribute("entryData");

            // 登録後のフォワード先を設定
            forwardPath = "RegisterEntry.jsp";
        }

        // 設定されたフォワード先にフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
        dispatcher.forward(request, response);
    }


}
