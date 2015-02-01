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

<%
	request.setCharacterEncoding("euc-kr");
	String menu = request.getParameter("manu");
		String product_id = request.getParameter("product_id");
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		int price = Integer.parseInt(request.getParameter("price"));
		String imagePath = request.getParameter("imagePath");
		String content = request.getParameter("content");
		
		boolean success = homeshoppingdbcp.setProduct(product_id, type, name, price, imagePath, content);
		
		if(success){
			response.sendRedirect("upload.jsp?success=true&product_id="+product_id);
		}else{
			response.sendRedirect("upload.jsp?success=false");
		}

			
%>
</body>
</html>