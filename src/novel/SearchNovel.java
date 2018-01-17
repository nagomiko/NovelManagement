/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package novel;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author C0116289
 */

@WebServlet(urlPatterns = {"/novel/SearchNovel"})
public class SearchNovel extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        //コネクションとステートメントの宣言
        Connection con = null;
        Statement stmt = null;
        PreparedStatement ps = null;


        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //Class.forNameの記述
            Class.forName("com.mysql.jdbc.Driver");

            //データベースへの接続
            String driverUrl = "jdbc:mysql://localhost:3306/novel";
            con = DriverManager.getConnection(driverUrl, "root", "root");
            stmt = con.createStatement();


            String ID = request.getParameter("ID");
            String  selectDB = request.getParameter("selectDB");

            if (selectDB.equals("1")) {
                String sql = "select * from test_fetch where ID=?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(ID));
                ResultSet rs = ps.executeQuery();

                List<NovelData> novelDataList = new ArrayList<>();


                //データベースから値を取得
                while (rs.next()) {
                    NovelData novel = new NovelData();
                    novel.setID(rs.getInt("ID"));
                    novel.setUrl(rs.getString("url"));
                    novel.setTitle(rs.getString("title"));
                    novelDataList.add(novel);
                }

                request.setAttribute("novelDataList", novelDataList);
                RequestDispatcher dispatcher = request.getRequestDispatcher("OutputFetchTargetRecord.jsp");
                dispatcher.forward(request, response);
                //ResultSetのclose
                rs.close();

            } else {
                String sql = "select * from test_episode where ID=?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(ID));
                ResultSet rs = ps.executeQuery();


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
                RequestDispatcher dispatcher = request.getRequestDispatcher("OutputEpisodeRecord.jsp");
                dispatcher.forward(request, response);
                //ResultSetのclose
                rs.close();

            }
        } catch (Exception e) {
            //サーブレット内での例外をアプリケーションのエラーとして表示
            throw new ServletException(e);
        } finally {
            //例外が発生する・しないにかかわらず確実にデータベースから切断
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new ServletException(e);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new ServletException(e);
                }
            }

            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    throw new ServletException(e);
                }
            }
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
