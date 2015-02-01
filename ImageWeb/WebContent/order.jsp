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
<%@page import="java.util.ArrayList,java.util.Date,ShoppingSite.CartBoardEntity" %>
<jsp:useBean id="loginfo" class="ShoppingSite.MemberEntity" scope="session"/>
<jsp:useBean id="homeshoppingdbcp" class="ShoppingSite.HomeShoppingDBCP" scope="page" />


<script>
function send(){
var thisForm = document.orderForm;
thisForm.r_name.value = thisForm.name.value;
thisForm.r_province.value = thisForm.province.value;
thisForm.r_town.value = thisForm.town.value;
thisForm.r_address.value = thisForm.address.value;
thisForm.r_phone.value = thisForm.phone_number.value;
}

</script>

<form name=orderForm action="orderProc.jsp" method="post">
<table width="500" border="0" cellpadding="5"  cellspacing="5" align="center">
	<tr>
		<td colspan="2" align=center>
			<b> <font size="5" color="black">[주 문] </font></b>
<font color="black"></font>		</td>
	</tr>
</table>

<font color="black"><%
		Date date = new Date();
		String member_id = loginfo.getMember_id();
		ArrayList<CartBoardEntity> list = null;
		list = homeshoppingdbcp.getProductsOfCart(member_id);
		int count = list.size();
		int totalPrice = 0;
%>
</font><table  width="465" border=2 style="background-color:white;" height="116">
    <tr>
        <th scope="col" width="106"><b><font color="black">사진</font></b><font color="black"></font></th>
        <th scope="col" width="74"><b><font color="black">이름</font></b><font color="black"></font></th>
        <th scope="col" width="56"><b><font color="black">색상</font></b><font color="black"></font></th>
        <th scope="col" width="67"><b><font color="black">사이즈</font></b><font color="black"></font></th>
        <th scope="col" width="53"><b><font color="black">수량</font></b><font color="black"></font></th>
        <th scope="col" width="67"><b><font color="black">가격</font></b><font color="black"></font></th>
            <th width="67">
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
    <p align="center"><span style="font-size:18pt;"><font color="black">총 가격 :  <%=totalPrice%></font></span><font color="black"></font></p>
</div>



<table width="100%" border="0" cellpadding="5"  cellspacing="5" align="center">

	
	<tr>
		<td colspan="2" align=center>
			<b> <font size="4" color="black">주문자 정보 </font></b>
<font color="black"></font>		</td>
	</tr>
	<tr>
		<td width=100><font color="black">이름: </font></td>
		<td><font color="black"><input type="text" name="name" value="<%=loginfo.getName() %>"> </font></td>
	</tr>
	<tr>
    	<td><font color="black">주소</font></td>
		<td width="430"><font color="black">도,광역(특별)시 입력: <input name="province" type="text" style="width:56px;" value="<%=loginfo.getProvince() %>" /></font></td>
	</tr>
	<tr>
		<td><font color="black"></font></td>	
		<td width="430"><font color="black">시,군 입력: <input type="text" name="town" style="width:56px" value="<%=loginfo.getTown() %>" /></font></td>
	</tr>
	<tr>
		<td><font color="black"></font></td>
		<td colspan="2"><font color="black">나머지 주소입력: <input name="address" type="text" style="width:310px;" value="<%=loginfo.getAddress()%>"/></font></td>
	</tr>
	<tr>
		<td width=100><font color="black">전화번호: </font></td>
		<td><font color="black"><input type="text" name="phone_number" value="<%=loginfo.getPhone_number() %>"></font></td>
	</tr>

	<tr>
		<td colspan="2" align=center>
			<b> <font size="4" color="black">배송지 정보 </font></b>
<font color="black"></font>		</td>
	</tr>
	<tr>
		<td colspan="2">
		<font color="black"><input type="checkbox" name="chkbox" onClick="send()"> 위 내용과 동일할시 체크해주세요</font></td>
	</tr>	
	<tr>
		<td width=100><font color="black">이름: </font></td>
		<td><font color="black"><input type="text" name="r_name"></font></td>
	</tr>
	<tr>
    	<td><font color="black">주소</font></td>
		<td width="430"><font color="black">도,광역(특별)시 입력: <input name="r_province" type="text" style="width:56px;"  /></font></td>
	</tr>
	<tr>
		<td><font color="black"></font></td>	
		<td width="430"><font color="black">시,군 입력: <input type="text" name="r_town" style="width:56px" /></font></td>
	</tr>
	<tr>
		<td><font color="black"></font></td>
		<td colspan="2"><font color="black">나머지 주소입력: <input name="r_address" type="text" style="width:310px;" /></font></td>
	</tr>
	<tr>
		<td width=100><font color="black">전화번호: </font></td>
		<td><font color="black"><input type="text" name="r_phone"></font></td>
	</tr>	
	<tr>
		<td width=100><font color="black">요청사항: </font></td>
		<td><font color="black"><input type="text" name="notice" size="30"></font></td>
	</tr>
	
	
</table>

<font color="black"><input type="hidden" name="payment" value='<%=totalPrice %>'>
</font>
<table width="100%" height="60" border="0" cellpadding="5" cellspacing="5">
	<tr>
		<td colspan="2" align=center>
			<b> <font size="4" color="black">결제 정보 </font></b>
<font color="black"></font>		</td>
	</tr>

	<tr>
		<td width="80"> <font color="black">결제금액: </font></td>
		<td> <font color="black"><%=totalPrice %> </font></td>
	</tr>
	<tr>
		<td width="80"> <font color="black">결제일: </font></td>
		<td><font color="black"><%=date %> </font></td>
	</tr>

	<tr>
		<td colspan="2" align = right>
	<font color="black"><input type="submit" value="결제하기">
	<input type="button" value="취 소" onclick="location.href='cart.jsp'"></font></td></tr>
</table>
</form>

</body>
</html>