<%@ page import="java.util.List" %>
<%@ page import="novel.NovelData" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: C0116289
  Date: 2018/01/17
  Time: 21:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OutputEpisode</title>
</head>
<body>
<%
    List<NovelData> novelDataList = (ArrayList<NovelData>) request.getAttribute("novelDataList");
    if (novelDataList.isEmpty()) {
        out.println("データがありません" + "<br>");
    } else {
        for (NovelData novelData : novelDataList) {
%>
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
        <hr>
    </div>
</div>

<%
        }
    }
%>
</body>
</html>
