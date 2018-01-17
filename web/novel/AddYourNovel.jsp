<%--
  Created by IntelliJ IDEA.
  User: C0116289
  Date: 2018/01/09
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AddYourNovel</title>
</head>
<body>
<div>
    小説追加ページです
</div>
<div>
    <form action="/novel/AddEpisodeRecord" method="post">
        <div>Title:<br><input type="text" name="title"></div>
        <div>No:<br><input type="number" name="No">(数字を入力してください)</div>
        <div>nCode:<br><input type="text" name="ncode"></div>
        <div>pageData<br><textarea name="pageData" cols="30" rows="4"></textarea></div>
        <div><input type="submit" value="送信"></div>
    </form>
</div>
</body>
</html>
