<%--
  Created by IntelliJ IDEA.
  User: sunjing
  Date: 2015/11/17
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>book mark</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="style/main.css">
    <script language="JavaScript" src="js/jquery-1.9.1.min.js"></script>
    <script language="JavaScript" src="js/jquery-1.9.1.js"></script>
    <script language="JavaScript" src="js/main.js"></script>
</head>

<body>
<div class="search">
    <input id="search-content" type="text" oninput="textChanged()" value="">
</div>
<div id="content" class="content">
</div>
<div class="container">
    <div id="pages" class="pages">
    </div>
    <div class="buttons">
        <button onclick="addMarksDialog()">添加</button>
        <button onclick="marksDialog()">书签</button>
    </div>
</div>
<div class="addDialog">
    <h3 class="myH3">添加书签</h3>
    <div class="add_form">
        <label>书签名称:<input class="sprited" id="bookname"></label>
        <label>书签地址:<input class="sprited" id="bookurl"></label>
        <div class="actions">
            <button class="form_button sprited" id="cancle" href="#" onclick="cancleAddMark()">取消</button>
            <button class="form_button sprited" id="confirm" href="#" onclick="addMark()">确定</button>
        </div>
        <span class="hiddenMsg">请输入正确的书签名称/书签链接</span>
    </div>
</div>
<div class="deleteDialog">
    <h3 class="myH3">删除记录</h3>
    <div class="delete_form">
        <span>确定要删除记录吗？</span>
        <button class="delete"  href="#"  >是</button>
        <button class="notdelete"  href="#" onclick="cancleDeleteBook()">否</button>
    </div>
</div>
</div>
<div class="transparentLayer">
</div>
<div class="bookmarks">
    <p>书签</p>
    <div class="mark">
    </div>
</div>
</body>
</html>

