<%@ page import="novel.NovelData" %><%--
  Created by IntelliJ IDEA.
  User: C0116289
  Date: 2018/01/17
  Time: 9:09
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>ConfirmEntry</title>
</head>
<body>
<%
    NovelData entryData = (NovelData) session.getAttribute("entryData");
    request.setCharacterEncoding("UTF8");
%>
<div>
    <h3>入力されたデータを確認します</h3>
    <div>
        Title:<br><%=entryData.getTitle()%>
    </div>
    <div>
        No:<br><%=entryData.getNo()%>
    </div>
    <did>
        pageData:<br><%=entryData.getPageData()%>
    </did>
    <div>
        <a href="AddEpisodeRecord">戻る</a>
        <a href="AddEpisodeRecord?action=done">登録する</a>
    </div>


</div>
</body>
</html>
