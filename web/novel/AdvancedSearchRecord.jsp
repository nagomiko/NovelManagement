<%--
  Created by IntelliJ IDEA.
  User: C0116289
  Date: 2018/01/17
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AdvancedSearchRecord</title>
</head>
<body>
<div>
    <h3>ちょっとだけ詳しい検索</h3>
    <div>
        titleと話数で検索できます
        <form action="AdvancedSearchRecord" method="post">
            <div>
                title:<br>
                <input type="text" name="title">
            </div>
            <div>
                No:<br>
                <input type="number" name="No">
            </div>
            <div>
                <button name="btn" type="submit" value="1">検索</button>
            </div>
        </form>
    </div>
    <div>
        nCodeで検索できます
        <form action="AdvancedSearchRecord" method="post">
            <div>
                nCode:<br>
                <input type="text" name="ncode">
            </div>
            <div>
                <button name="btn" type="submit" value="2">検索</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
