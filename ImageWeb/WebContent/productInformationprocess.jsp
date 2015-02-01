<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

<jsp:useBean id="loginfo" class="ShoppingSite.MemberEntity" scope="session"/>
<jsp:useBean id="homeshoppingdbcp" class="ShoppingSite.HomeShoppingDBCP" scope="page" />
<%
	request.setCharacterEncoding("euc-kr");
%>

<%
	String menu = request.getParameter("menu");
	String options = request.getParameter("options");
	String product_id = request.getParameter("product_id");
	String member_id = loginfo.getMember_id();
	int amount = Integer.parseInt(request.getParameter("count"));
	if(member_id==null || member_id==""){
		response.sendRedirect("index.jsp?product_id="+product_id);
	}
%>

<% 
	if(menu.equals("cart")){		
		String color = null;
		String size = null;
		String temp = "";
		options.equals("");
		
		if(!options.equals("")&options!=null){
			String[] tempOption = options.split("-");
			color = tempOption[0];
			size = tempOption[1];			
		}else{
			response.sendRedirect("index.jsp?product_id="+product_id);
		}
		boolean success = homeshoppingdbcp.setProductIntoCart(member_id, product_id, color, size, amount);
		if(success){
			response.sendRedirect("categoryprocess.jsp?menu=cart");
		}else{
			out.println("<script>alert('상품입력이 되지 않았습니다. ');</script>");
			response.sendRedirect("index.jsp?product_id="+product_id);
		}				
	}else if(menu.equals("comment")){
		String comment = request.getParameter("comment");
		String goodbad = request.getParameter("goodbad");
		boolean success = homeshoppingdbcp.setProductOpnion(product_id, member_id, comment, goodbad);
		if(success){
			response.sendRedirect("index.jsp?product_id="+product_id);
		}else{
			out.println("<script>alert('코멘트가 입력되지 않았습니다. 다시 입력 하십시오');</script>");
			response.sendRedirect("index.jsp?product_id="+product_id);
		}			
	}
	
%>

</body>
</html>