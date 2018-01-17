<%@ page import="java.util.List" %>
<%@ page import="novel.NovelData" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="novel.NovelData" %>
<%--
  Created by IntelliJ IDEA.
  User: C0116289
  Date: 2018/01/11
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OutputEpisodeRecord</title>
</head>
<body>
<h3>OutputEpisodeRecord</h3>

<%
    List<NovelData> novelDataList = (ArrayList<NovelData>) request.getAttribute("novelDataList");
    if (request.getAttribute("ID") != null) {
        out.println("ID=" + request.getAttribute("ID") + "が削除されました" + "<br><hr>");
    }
    if (novelDataList.isEmpty()) {
        out.println("データがありません" + "<br>");
    } else {
        for (NovelData novelData : novelDataList) {

%>
<form action="OutputEpisodeRecord" method="post">
    <div>
        <div><%="ID = " + novelData.getID()%>
        </div>
        <div><%="TargetID = " + novelData.getTarget_ID()%>
        </div>
        <div><%="No = " + novelData.getNo()%>
        </div>
        <div><%="URL = " + novelData.getUrl()%>
        </div>
        <div><%="Title = " + novelData.getTitle()%>
        </div>
        <div><%="PageData = " + "<br>" + novelData.getPageData()%>
        </div>
        <div><%="isRead = " + novelData.getIsRead()%>
        </div>
        <div>
            <button type="submit" name="ID" value=<%=novelData.getID()%>>削除</button>
        </div>
        <div>
            <hr>
        </div>
    </div>
</form>
<%
        }
    }
%>

<a href="admin_page.jsp">adminPageに戻る</a><br>
<a href="top_page.html">topPageに戻る</a>

</body>
</html>
