<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<%@ page import="com.oreilly.servlet.MultipartRequest,com.oreilly.servlet.multipart.DefaultFileRenamePolicy,java.util.*,java.io.*" %>
<%@ page import="java.sql.*" %>
<%
 request.setCharacterEncoding("euc-kr");
 String realFolder = "";
 String filename1 = "";
 int maxSize = 1024*1024*5;
 String encType = "euc-kr";
 String savefile = "picture";
 ServletContext scontext = getServletContext();
 realFolder = scontext.getRealPath(savefile);
 
 try{
  MultipartRequest multi=new MultipartRequest(request, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
  Enumeration<?> files = multi.getFileNames();
     String file1 = (String)files.nextElement();
     filename1 = multi.getFilesystemName(file1);
 } catch(Exception e) {
  e.printStackTrace();
 }
 
 String fullpath = realFolder + "\\" + filename1;
%>
<body>

<form action="saveprocess.jsp" method="post">
<input type="hidden" name="menu" value="product">
<p>product_id : <input type="text" name="product_id"></p>
<p>type : <select name=type>
			<option value="pants">바지</option>
			<option value="etc">잡화</option>
			<option value="outer">외투</option>
			<option value="shoes">신발</option>
			<option value="tshirts">티셔츠</option>
		</select></p>
<p>name : <input type="text" name="name"></p>
<p>price : <input type="text" name="price"></p>
<p><input type="hidden" name="imagePath" value='<%=fullpath %>'></p>
<p><textarea rows="5" cols="30" name="content"></textarea></p>
<p><input type="submit" value="자료넣기"><p>

</form>
</body>
</html>