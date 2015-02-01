<%@page import="ShoppingSite.CartEntity"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>  
<%@page import="java.util.ArrayList,java.util.Date,ShoppingSite.CartEntity" %>
<jsp:useBean id="categoryNumber" class="ShoppingSite.WhichBoard" scope="session"/>
<jsp:useBean id="loginfo" class="ShoppingSite.MemberEntity" scope="session"/>
<jsp:useBean id="homeshoppingdbcp" class="ShoppingSite.HomeShoppingDBCP" scope="page" />

<%
	request.setCharacterEncoding("euc-kr");
	String member_id = loginfo.getMember_id();
	String receiver = request.getParameter("r_name");
	String receiver_address = request.getParameter("r_address");
	String receiver_province = request.getParameter("r_province");
	String receiver_town = request.getParameter("r_town");
	int phone_number = Integer.parseInt(request.getParameter("r_phone"));
	String notice = request.getParameter("notice");//text?
	String deposit_confirmation = "결제확인중";
	int payment = Integer.parseInt(request.getParameter("payment"));
	ArrayList<CartEntity> ceList = null;
	boolean success = homeshoppingdbcp.doOrders(member_id, receiver, receiver_address, receiver_province, receiver_town, phone_number, notice, deposit_confirmation, payment);
	 
	if(success){
		int orderNumber = homeshoppingdbcp.getOrderNumber();
		ceList = homeshoppingdbcp.getCartEntityByMemberId(member_id);		
		success = homeshoppingdbcp.setOrderProduct(orderNumber, ceList,receiver_province,receiver_town);
		if(success){
			homeshoppingdbcp.deleteProductInCartByMember(member_id);
			int number = 7;
			%>
					<jsp:setProperty property="pageNumber" name="categoryNumber" value='<%=number %>'/>		
			<%
			
			response.sendRedirect("index.jsp");
		}else{
			
		}
	}else{
		response.sendRedirect("order.jsp");
	}
%>





<script>
alert("주문처리 되었습니다.");
location.href="checkOrder.jsp";
</script>

</body>
</html>