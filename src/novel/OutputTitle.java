package novel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/novel/OutputTitle"})
public class OutputTitle extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String forwardPath = null;

        Connection con;
        PreparedStatement ps;
        Statement stmt = null;

        String action = request.getParameter("action");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String driverURL = "jdbc:mysql://localhost:3306/novel";
            con = DriverManager.getConnection(driverURL, "root", "root");
            stmt = con.createStatement();

            if (action == null) {
                forwardPath = "OutputTitle.jsp";
                String sql = "select * from test_fetch";
                ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                List<NovelData> Title = new ArrayList<>();


                while (rs.next()) {
                    NovelData title = new NovelData();
                    title.setID(rs.getInt("ID"));
                    title.setTitle(rs.getString("title"));

                    Title.add(title);
                }

                request.setAttribute("Title", Title);

            } else if (action.equals("sub")) {
                forwardPath = "OutputSubTitle.jsp";
                int ID = Integer.parseInt(request.getParameter("ID"));
                String sql = "select * from test_episode where target_ID = ?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, ID);
                ResultSet rs = ps.executeQuery();

                List<NovelData> SubTitle = new ArrayList<>();

                while (rs.next()) {
                    NovelData subTitle = new NovelData();
                    subTitle.setTitle(rs.getString("title"));
                    subTitle.setNo(Integer.parseInt(rs.getString("no")));
                    subTitle.setUrl(rs.getString("url"));
                    SubTitle.add(subTitle);
                }

                request.setAttribute("SubTitle", SubTitle);

            } else if (action.equals("episode")){
                forwardPath="OutputEpisode.jsp";
                String url=request.getParameter("url");
                String sql="select * from test_episode where url=?";
                ps=con.prepareStatement(sql);
                ps.setString(1,url);
                ResultSet rs=ps.executeQuery();

                List<NovelData> novelDataList = new ArrayList<>();

                //データベースから値を取得
                while (rs.next()) {
                    NovelData novel = new NovelData();
                    novel.setID(rs.getInt("ID"));
                    novel.setTarget_ID(rs.getInt("target_ID"));
                    novel.setNo(rs.getInt("no"));
                    novel.setUrl(rs.getString("url"));
                    novel.setTitle(rs.getString("title"));
                    novel.setPageData(rs.getString("page_data"));
                    novel.setIsRead(rs.getInt("is_read"));

                    novelDataList.add(novel);
                }
                request.setAttribute("novelDataList", novelDataList);

            }



            // 設定されたフォワード先にフォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher(forwardPath);
            dispatcher.forward(request, response);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
        }

    }
}

