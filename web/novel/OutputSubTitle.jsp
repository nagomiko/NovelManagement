<%@ page import="java.util.List" %>
<%@ page import="novel.NovelData" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: C0116289
  Date: 2018/01/17
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OutputSubTitle</title>
</head>
<body>
<%
    List<NovelData> SubTitle = (ArrayList<NovelData>) request.getAttribute("SubTitle");
    if (SubTitle.isEmpty()) {
        out.println("データがありません");
    } else {
        for (NovelData subTitle : SubTitle) {
            if (subTitle.getNo() == 0) {
            } else {
%>
<div>
    <a href="OutputTitle?action=episode&url=<%=subTitle.getUrl()%>"><%=subTitle.getTitle()%>
    </a>
</div>
<%
            }
        }
    }
%>
<div>
    <a href="user_page.jsp">userpageに戻る</a><br>
    <a href="top_page.html">toppageに戻る</a>
</div>
</body>
</html>
