<%--
  Created by IntelliJ IDEA.
  User: C0116289
  Date: 2018/01/09
  Time: 15:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addNovel</title>
</head>
<body>
<div>
    <h3>小説追加ページです</h3>
    <form method="post" action="/novel/AddNovelToDB">
        <div>
            小説のncodeを入力してください<br>
        </div>
        <div>
            ncode:<input type="text" name="ncode"><br>
            <input type="submit" name="btn1" value="送信">
        </div>
    </form>
</div>
</body>
</html>
