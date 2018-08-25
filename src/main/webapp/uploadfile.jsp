<%--
  Created by IntelliJ IDEA.
  User: sky丶风
  Date: 2018/8/23
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传页面</title>
</head>
<body>
    <div style="margin: 0 auto;margin-top: 100px;margin-left: 100px">
        <form method="post" action="file/upload" enctype="multipart/form-data">
            <p><span>文件：</span>
                <input type="file" name="file">
            </p>

            <p><input type="submit" value="提交"></p>
        </form>
    </div>
</body>
</html>
