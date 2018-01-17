<%@ page import="java.util.List" %>
<%@ page import="novel.NovelData" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: C0116289
  Date: 2018/01/17
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OutputTitle</title>
</head>
<body>
<%
    List<NovelData> Title = (ArrayList<NovelData>) request.getAttribute("Title");
    if (Title.isEmpty()) {
        out.println("データがありません");
    } else {
        for (NovelData title : Title) {

%>
<div>
    <a href="OutputTitle?action=sub&ID=<%=title.getID()%>"><%=title.getTitle()%>
    </a>
</div>
<%
        }
    }
%>
<div>
    <a href="user_page.jsp">userpageに戻る</a><br>
    <a href="top_page.html">toppageに戻る</a>
</div>

</body>
</html>
