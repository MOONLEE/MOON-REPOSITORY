<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link href="style.css" rel="stylesheet" type="text/css" />
<meta name="generator" content="Namo WebEditor(Trial)">
</head>
<body>
<h1> <font color="black">����</font></h1>
<font color="black"><jsp:useBean id="loginfo" class="ShoppingSite.MemberEntity" scope="session"/>
<jsp:useBean id="categoryNumber" class="ShoppingSite.WhichBoard" scope="session"/>


<%

	int number = 12;
%>
	<jsp:setProperty property="pageNumber" name="categoryNumber" value='<%=number %>'/>
<%
	response.sendRedirect("index.jsp");
	
%>
</font>
</body>
</html>