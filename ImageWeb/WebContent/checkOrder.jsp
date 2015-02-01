<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<meta name="generator" content="Namo WebEditor(Trial)">
</head>
<body>
<font color="black"><%@page import="ShoppingSite.OrderBoardEntity,java.util.ArrayList,ShoppingSite.OrdersEntity" %>
<jsp:useBean id="homeshoppingdbcp" class="ShoppingSite.HomeShoppingDBCP" scope="page" />
<jsp:useBean id="loginfo" class="ShoppingSite.MemberEntity" scope="session"/>
<%
		request.setCharacterEncoding("euc-kr");
%>
</font><h1><font color="black">주문확인</font></h1>
<font color="black"><%
		String member_id = loginfo.getMember_id();
		ArrayList<OrdersEntity> oeList = homeshoppingdbcp.getOrderInformation(member_id);
		ArrayList<OrderBoardEntity> list = null;
		list = homeshoppingdbcp.getProductsInOrder(member_id);
		int count = list.size();
		int totalPrice = 0;
%>
</font><table  width="465" border=2 style="background-color:white;" height="116">
    <tr>
        <th scope="col" width="106"><b><font color="black">사진</font></b><font color="black"></font></th>
        <th scope="col" width="74"><b><font color="black">주문번호</font></b><font color="black"></font></th>
        <th scope="col" width="74"><b><font color="black">이름</font></b><font color="black"></font></th>
        <th scope="col" width="56"><b><font color="black">색상</font></b><font color="black"></font></th>
        <th scope="col" width="67"><b><font color="black">사이즈</font></b><font color="black"></font></th>
        <th scope="col" width="53"><b><font color="black">수량</font></b><font color="black"></font></th>
        <th scope="col" width="67"><b><font color="black">가격</font></b><font color="black"></font></th>
        <th scope="col" width="67"><b><font color="black">배송</font></b><font color="black"></font></th>
    </tr>
    <%
   	if (count > 0){
    	for(OrderBoardEntity cbe : list){
    		totalPrice += cbe.getAmount() * cbe.getPrice();
    %>
    <tr onmouseover="this.style.backgroundColor='SkyBlue'"
    	onmouseout="this.style.backgroundColor='white'">
        <td align=left width="106" height="84">
            <p align="center"><a href="categoryprocess.jsp?menu=productInfomation&product_id=<%=cbe.getProduct_id()%>"><font color="black"><img src="GetProductImage?product_id=<%=cbe.getProduct_id()%>" width="103" height="83"></font></a><font color="black"></font></p>
        </td>
        <td align=left width="74" height="84">
            <p align="center"><a href="categoryprocess.jsp?menu=productInfomation&product_id=<%=cbe.getProduct_id()%>"><b><font color="black"><%=cbe.getOrder_number() %></font></b></a><font color="black"></font></p>
        </td>
        <td align=left width="74" height="84">
            <p align="center"><a href="categoryprocess.jsp?menu=productInfomation&product_id=<%=cbe.getProduct_id()%>"><b><font color="black"><%=cbe.getName() %></font></b></a><font color="black"></font></p>
        </td>
        <td align=left width="56" height="84">
            <p align="center"><a href="categoryprocess.jsp?menu=productInfomation&product_id=<%=cbe.getProduct_id()%>"><b><font color="black"><%=cbe.getColor() %></font></b></a><font color="black"></font></p>
        </td>
        <td align=left width="67" height="84">
            <p align="center"><a href="categoryprocess.jsp?menu=productInfomation&product_id=<%=cbe.getProduct_id()%>"><b><font color="black"><%=cbe.getSize() %></font></b></a><font color="black"></font></p>
        </td>
        <td align=left width="53" height="84">
            <p align="center"><a href="categoryprocess.jsp?menu=productInfomation&product_id=<%=cbe.getProduct_id()%>"><b><font color="black"><%=cbe.getAmount() %></font></b></a><font color="black"></font></p>
        </td>
        <td align=left width="67" height="84">
            <p align="center"><a href="categoryprocess.jsp?menu=productInfomation&product_id=<%=cbe.getProduct_id()%>"><b><font color="black"><%=cbe.getPrice() %></font></b></a><font color="black"></font></p>
        </td>
        <td align=left width="67" height="84">
            <p align="center"><a href="categoryprocess.jsp?menu=productInfomation&product_id=<%=cbe.getProduct_id()%>"><b><font color="black"><%=cbe.getDelivery_info() %></font></b></a><font color="black"></font></p>
        </td>
    </tr>
    <%
    	}
    }
	%>
</table>
<div>
    <p align="center"><span style="font-size:18pt;"><font color="black">총 가격 :  <%=totalPrice%></font></span><font color="black"></font></p>
</div>
<h1><font color="black">주문자 정보</font></h1>
<font color="black"><%
	int count2  = oeList.size(); 
   	if (count2 > 0){
    	for(OrdersEntity oe : oeList){
    %>
</font>

<table width="465"><tr>
	<table border="1" width="467" border=2 style="background-color:white;" height="53">
	<tr>
        <td width="118" height="21">
            <p align="center"><font color="black">주문번호</font></p>
        </td>
        <td width="82" height="21">
            <p align="center"><font color="black"><%=oe.getOrder_number() %></font></p>
        </td>
        <td width="114" height="21">
            <p align="center"><font color="black">받는사람</font></p>
        </td>
        <td width="123" height="21">
            <p align="center"><font color="black"><%=oe.getReceiver() %></font></p>
        </td>
	</tr>
	<tr>
        <td width="118">
            <p align="center"><font color="black">가격</font></p>
        </td>
        <td width="82">
            <p align="center"><font color="black"><%=oe.getPayment() %></font></p>
        </td>
        <td width="114">
            <p align="center"><font color="black">전화번호</font></p>
        </td>
        <td width="123">
            <p align="center"><font color="black"><%=oe.getPhone_number() %></font></p>
        </td>
	</tr>
	</table>
<font color="black">	</tr>
	<tr>
</font>	<table border="1" width="465" border=2 style="background-color:white;">
	<tr>
        <td width="81">
            <p align="center"><font color="black">주소</font></p>
        </td>
		<td width="171"><font color="black"><%=oe.getReceiver_province() %><%=oe.getReceiver_town() %><%=oe.getReceiver_address() %></font></td>
        <td width="76">
            <p align="center"><font color="black">주문날짜</font></p>
        </td>
		<td width="107"><font color="black"><%=oe.getPayment_day() %></font></td>
		</tr>
		<tr>
		<td width="81">
            <p align="center"><font color="black">notice</font></p>
		</td>
		<td width="171"><font color="black"><%=oe.getNotice() %></font></td>		
        <td width="76">
            <p align="center"><font color="black">결재상태</font></p>
        </td>
		<td width="107"><font color="black"><%=oe.getDeposit_confirmation() %>

</font>	</tr>
	</table>
<font color="black">	</tr>
</table>
<br>
<br>
<%
    	}
    }
%>
</font></body>
</html>