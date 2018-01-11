/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package novel;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author C0116289
 */

@WebServlet(urlPatterns = {"/novel/OutputEpisodeRecord"})
public class OutputEpisodeRecord extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>OutputEpisodeRecord</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Servlet OutputEpisodeRecord at " + request.getContextPath() + "</h3>");

            //Class.forNameの記述
            Class.forName("com.mysql.jdbc.Driver");

            //データベースへの接続
            String driverUrl = "jdbc:mysql://localhost:3306/novel";
            con = DriverManager.getConnection(driverUrl, "root", "root");
            stmt = con.createStatement();

            //SQL文の発行
            String sql = "SELECT * FROM test_episode";
            ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();


            //データベースから値を取得して出力
            while (rs.next()) {
                out.println("ID=" + rs.getInt("ID") + "<br>");
                out.println("TargetID=" + rs.getInt("target_ID") + "<br>");
                out.println("No=" + rs.getInt("no") + "<br>");
                out.println("URL=" + rs.getString("url") + "<br>");
                out.println("Title=" + rs.getString("title") + "<br>");
                out.println("PageData=" + rs.getString("page_data") + "<br>");
                out.println("IsRead=" + rs.getInt("is_read") + "<br>");
                out.println("<hr><br>");

            }


            //ResultSetのclose
            rs.close();

            out.println("</body>");
            out.println("</html>");
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
