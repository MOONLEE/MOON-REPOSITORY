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
<h1> <font color="black">��ȭ</font></h1>
<font color="black">
<%@page import="ShoppingSite.ProductEntity,java.util.ArrayList" %>
<jsp:useBean id="loginfodbcp" class="ShoppingSite.HomeShoppingDBCP" scope="page" />
<%
		request.setCharacterEncoding("euc-kr");
%>
<%
	
		String type = "etc";
		ArrayList<ProductEntity> list = null;
		list = loginfodbcp.getListOfProduct(type);
		
		
		int counter = list.size();
		int row = 0;
			
%>
</font>
  <table  width="100%" border=2 align="center" style="background-color:ivory;">
     <tr>
       <th width="100" scope="col"><b><font color="black">����</font></b><font color="black"></font></th>
       <th width="100" scope="col"><b><font color="black">�̸�</font></b><font color="black"></font></th>
       <th width="100" scope="col"><b><font color="black">����</font></b><font color="black"></font></th>
    </tr>
    <%
   	if (counter > 0){
    	for(ProductEntity pe : list){
    %>
    <tr onmouseover="this.style.backgroundColor='SkyBlue'"
    	onmouseout="this.style.backgroundColor='white'">
       <td align=left><a href="categoryprocess.jsp?menu=productInfomation&product_id=<%=pe.getProduct_id()%>"><font color="black"><img src="GetProductImage?product_id=<%=pe.getProduct_id()%>" width="200" height="200"></font></a><font color="black"></font></td>
       <td align=left><a href="categoryprocess.jsp?menu=productInfomation&product_id=<%=pe.getProduct_id()%>"><b><font color="black"><%=pe.getName() %></font></b></a><font color="black"></font></td>
       <td align=left><a href="categoryprocess.jsp?menu=productInfomation&product_id=<%=pe.getProduct_id()%>"><b><font color="black"><%=pe.getPrice() %></font></b></a></td>
    </tr>
	<%
    	}
    }
	%>
	</table>
</body>
</html>