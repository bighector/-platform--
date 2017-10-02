<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
%>
<form method="post" action="<%=path%>/fileUpload/uploadFile" enctype="multipart/form-data">
    文件名: <input type="text" name="fileName"/><br/>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <input type="file" name="clientFile"/><br/>
    <input type="submit" value="上传文件 "/>
</form>