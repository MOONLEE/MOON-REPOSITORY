<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%@page import="ShoppingSite.HomeShoppingDBCP" %>
<%@page import="ShoppingSite.MemberEntity" %>
<jsp:useBean id="loginfodbcp" class="ShoppingSite.HomeShoppingDBCP" scope="page" />
<jsp:useBean id="loginfo" class="ShoppingSite.MemberEntity" scope="session"/>

<%
	request.setCharacterEncoding("euc-kr");
%>


<%
	String menu = request.getParameter("menu");
	if(menu.equals("login")){
		String member_id = request.getParameter("id");
		String password = request.getParameter("password");
		if(loginfodbcp.isValid(member_id, password)){	
		MemberEntity log = loginfodbcp.getLoginfo(member_id, password);
%>
			
		 	<jsp:setProperty property="member_id" name="loginfo" value='<%=log.getMember_id()%>'/>
			<jsp:setProperty property="password" name="loginfo" value='<%=log.getPassword()%>'/>
			<jsp:setProperty property="name" name="loginfo" value='<%=log.getName()%>'/>
			<jsp:setProperty property="mail" name="loginfo" value='<%=log.getMail()%>'/>
			<jsp:setProperty property="address" name="loginfo" value='<%=log.getAddress()%>'/>
			<jsp:setProperty property="province" name="loginfo" value='<%=log.getProvince()%>'/>
			<jsp:setProperty property="town" name="loginfo" value='<%=log.getTown()%>'/>
			<jsp:setProperty property="phone_number" name="loginfo" value='<%=log.getPhone_number()%>'/>	
		
<%
		}	
	}
	

	response.sendRedirect("index.jsp");

%>

</body>
</html>