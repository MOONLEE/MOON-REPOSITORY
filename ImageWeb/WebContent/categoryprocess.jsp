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
<jsp:useBean id="homeshoppingdbcp" class="ShoppingSite.HomeShoppingDBCP" scope="page" />
<jsp:useBean id="loginfo" class="ShoppingSite.MemberEntity" scope="session"/>
<jsp:useBean id="categoryNumber" class="ShoppingSite.WhichBoard" scope="session"/>
<%
	request.setCharacterEncoding("euc-kr");
%>


<%

	String menu = request.getParameter("menu");
	if(menu.equals("pants")){		
		int number = 1;
%>
		
	 	<jsp:setProperty property="pageNumber" name="categoryNumber" value='<%=number %>'/>

			
<%
		response.sendRedirect("index.jsp");
	}else if(menu.equals("shoes")){		
		int number = 2;
%>
		
	 	<jsp:setProperty property="pageNumber" name="categoryNumber" value='<%=number %>'/>

			
<%
		response.sendRedirect("index.jsp");
	}else if(menu.equals("tshirts")){	
		int number = 3;
%>
		
	 	<jsp:setProperty property="pageNumber" name="categoryNumber" value='<%=number %>'/>

			
<%
		response.sendRedirect("index.jsp");
	}else if(menu.equals("etc")){	
		int number = 4;
%>
		
	 	<jsp:setProperty property="pageNumber" name="categoryNumber" value='<%=number %>'/>

			
<%
		response.sendRedirect("index.jsp");
	}else if(menu.equals("outer")){	
		int number = 5;
%>
		
	 	<jsp:setProperty property="pageNumber" name="categoryNumber" value='<%=number %>'/>

			
<%
		response.sendRedirect("index.jsp");
	}else if(menu.equals("cart")){	
		int number = 6;
%>
		
	 	<jsp:setProperty property="pageNumber" name="categoryNumber" value='<%=number %>'/>

			
<%
		response.sendRedirect("index.jsp");
	}else if(menu.equals("checkOrder")){
		int number = 7;
%>
		
	 	<jsp:setProperty property="pageNumber" name="categoryNumber" value='<%=number %>'/>

			
<%
		response.sendRedirect("index.jsp");
	}else if(menu.equals("order")){	
		int number = 8;
%>
		
	 	<jsp:setProperty property="pageNumber" name="categoryNumber" value='<%=number %>'/>

			
<%
		response.sendRedirect("index.jsp");
	}else if(menu.equals("refund")){	
		int number = 9;
%>
		
	 	<jsp:setProperty property="pageNumber" name="categoryNumber" value='<%=number %>'/>

			
<%
		response.sendRedirect("index.jsp");
	}else if(menu.equals("productInfomation")){
		int number = 10;
		String product_id = request.getParameter("product_id");
%>
	<jsp:setProperty property="pageNumber" name="categoryNumber" value='<%=number %>'/>		
		
<%
		response.sendRedirect("index.jsp?product_id=" + product_id);
	}
	else if(menu.equals("search")){	
		int number = 11;
%>

	<jsp:setProperty property="pageNumber" name="categoryNumber"
		value='<%=number %>' />
	<%
		String category = request.getParameter("category");
		String textForSearch = request.getParameter("input_Search");
		session.setAttribute("textForSearch", textForSearch);
		response.sendRedirect("index.jsp?category="+category);
	}else{
		int number = 0;
%>
		<jsp:setProperty property="pageNumber" name="categoryNumber" value='<%=number %>'/>		
<%
		response.sendRedirect("index.jsp");
	}









%>
</body>
</html>