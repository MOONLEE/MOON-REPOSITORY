<%@page import="ShoppingSite.HomeShoppingDBCP"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<jsp:useBean id="homeshoppingdbcp" class="ShoppingSite.HomeShoppingDBCP" scope="page" />
<jsp:useBean id="loginfo" class="ShoppingSite.MemberEntity" scope="session"/>
<%
	request.setCharacterEncoding("euc-kr");
%>
<%
	String menu = request.getParameter("menu");
	
	if(menu.equals("delete")){
		String member_id = loginfo.getMember_id();
		String color = request.getParameter("color");
		String size = request.getParameter("size");
		String product_id = request.getParameter("product_id");		
		homeshoppingdbcp.deleteProductInCart(product_id, member_id, color, size);
		response.sendRedirect("index.jsp");
		
	}else if(menu.equals("order")){
		
		response.sendRedirect("categoryprocess.jsp?menu=order");
	}
	
	
%>
</body>
</html>