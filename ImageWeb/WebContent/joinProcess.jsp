<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%@ page import = "ShoppingSite.MemberEntity, ShoppingSite.HomeShoppingDBCP" %>
<jsp:useBean id="homeshoppingdbcp" class="ShoppingSite.HomeShoppingDBCP" scope="page" />

<jsp:useBean id="categoryNumber" class="ShoppingSite.WhichBoard" scope="session"/>



<% request.setCharacterEncoding("EUC-KR"); %>

<jsp:useBean id="member" class="ShoppingSite.MemberEntity"/>
<jsp:setProperty name="member" property="*"/>


<%

HomeShoppingDBCP manager = new HomeShoppingDBCP();
manager.insertMember(member);


%>

<%

	int number = 0;
%>
	<jsp:setProperty property="pageNumber" name="categoryNumber" value='<%=number %>'/>


<script>
alert("가입되셨습니다.");
location.href="index.jsp";
</script>




</body>
</html>