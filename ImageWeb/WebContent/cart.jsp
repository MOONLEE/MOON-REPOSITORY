<%@page import="ShoppingSite.HomeShoppingDBCP"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>cart</title>
<meta name="generator" content="Namo WebEditor(Trial)">
<script src="cart.js"></script>
</head>
<body>
<h1> <font color="black">장바구니</font></h1>
<font color="black"><%@page import="ShoppingSite.CartBoardEntity,java.util.ArrayList" %>
<jsp:useBean id="homeshoppingdbcp" class="ShoppingSite.HomeShoppingDBCP" scope="page" />
<jsp:useBean id="loginfo" class="ShoppingSite.MemberEntity" scope="session"/>
<%
		request.setCharacterEncoding("euc-kr");
%>

<%
		String member_id = loginfo.getMember_id();
		ArrayList<CartBoardEntity> list = null;
		list = homeshoppingdbcp.getProductsOfCart(member_id);
		int count = list.size();
		int totalPrice = 0;
%>
</font><form action="cartprocess.jsp" method="post" name="cartForm">
<font color="black"><input type="hidden" name="menu" value="">
<input type="hidden" name="product_id" value="">
<input type="hidden" name="color" value="">
<input type="hidden" name="size" value="">
</font><table  width="465" border=2 style="background-color:white;" height="121">
    <tr>
        <th scope="col" width="106" height="20"><b><font color="black">사진</font></b><font color="black"></font></th>
        <th scope="col" width="74" height="20"><b><font color="black">이름</font></b><font color="black"></font></th>
        <th scope="col" width="56" height="20"><b><font color="black">색상</font></b><font color="black"></font></th>
        <th scope="col" width="67" height="20"><b><font color="black">사이즈</font></b><font color="black"></font></th>
        <th scope="col" width="53" height="20"><b><font color="black">수량</font></b><font color="black"></font></th>
        <th scope="col" width="67" height="20"><b><font color="black">가격</font></b><font color="black"></font></th>
            <th width="67" height="20">
                <p align="center"><font color="black">삭제</font></p>
            </th>
    </tr>
    <%
   	if (count > 0){
    	for(CartBoardEntity cbe : list){
    		totalPrice += cbe.getAmount() * cbe.getPrice();
    %>
    <tr onmouseover="this.style.backgroundColor='SkyBlue'"
    	onmouseout="this.style.backgroundColor='white'">
        <td align=left width="106" height="84">
            <p align="center"><a href="categoryprocess.jsp?menu=productInfomation&product_id=<%=cbe.getProduct_id()%>"><font color="black"><img src="GetProductImage?product_id=<%=cbe.getProduct_id()%>" width="103" height="83"></font></a><font color="black"></font></p>
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
            <td width="67" height="84" align="left">
                <p align="center"><font color="black"><input type="button" value="삭제" onclick="doDelete('<%=cbe.getProduct_id()%>','<%=cbe.getColor()%>','<%=cbe.getSize()%>')"></font></p>
            </td>
    </tr>
    <%
    	}
    }
	%>
</table>
<div>
    <p align="center"><span style="font-size:18pt;"><font color="black">총 가격 :  <%=totalPrice%></font></span></p>
</div>
    <div>
        <p align="center"><input type="image" src="images/button3.gif" onclick="doOrderInCart()"></p>
    </div>
</form>
</body>
</html>