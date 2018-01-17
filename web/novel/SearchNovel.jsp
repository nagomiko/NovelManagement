<%--
  Created by IntelliJ IDEA.
  User: C0116289
  Date: 2018/01/09
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SearchNovel</title>
</head>
<body>
<div>
    <h3>主キーによる検索</h3>
    <form action="SearchNovel" method="post">
        <div>
            検索するDBを選択してください<br>
            <input type="radio" name="selectDB" value="1" checked>test_fetch
            <input type="radio" name="selectDB" value="2">test_episode
        </div>
        <div>ID:<input type="number" name="ID">(数字を入力して下さい)</div>
        <div><input type="submit" value="送信"></div>
    </form>
</div>
</body>
</html>
