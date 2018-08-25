<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>下载文件列表</title>
</head>

<body>
<c:if test="${empty fileNameMap}">
    <c:redirect url="file/listFile"/>
</c:if>
<!-- 遍历Map集合 -->
<c:forEach var="me" items="${fileNameMap}" >
    <c:url value="http://localhost:8080/file/downFile" var="downurl">
        <c:param name="filename" value="${me.key}"></c:param>
    </c:url>
    ${me.value}<a href="${downurl}">下载</a>
    <br/>
</c:forEach>

</body>
</html>
