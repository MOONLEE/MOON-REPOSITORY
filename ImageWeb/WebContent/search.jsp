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
<%@page import="ShoppingSite.MemberEntity,java.util.ArrayList" %>
<%@page import="ShoppingSite.ProductEntity"%>
<jsp:useBean id="loginfo" class="ShoppingSite.MemberEntity"	scope="session" />
<jsp:useBean id="loginfodbcp" class="ShoppingSite.HomeShoppingDBCP" scope="page" />
<%

	request.setCharacterEncoding("euc-kr");
%>
 

<%
	String category = request.getParameter("category");
	String textForSearch = (String)session.getAttribute("textForSearch");
	ArrayList<ProductEntity> peList = loginfodbcp.searchingProductForPants(category, textForSearch);
	int count = peList.size();
	%>
	
	<h1> <font color="black">검색 페이지</font></h1>
<table  width="100%" border="4" align="center" style="background-color:white;">
     <tr>
       <th width="100" scope="col"><b><font color="black">사진</font></b><font color="black"></font></th>
       <th width="100" scope="col"><b><font color="black">이름</font></b><font color="black"></font></th>
       <th width="100" scope="col"><b><font color="black">가격</font></b><font color="black"></font></th>
    </tr>
    <%
    int i = 1;
   	if (count> 0){
    	for(ProductEntity pe : peList){
    %>
    <tr onmouseover="this.style.backgroundColor='SkyBlue'"
    	onmouseout="this.style.backgroundColor='white'">
       <td align=left><a href="categoryprocess.jsp?menu=productInfomation&product_id=<%=pe.getProduct_id()%>">
       <font color="black"><img src="GetProductImage?product_id=<%=pe.getProduct_id()%>" width="200" height="200"></font></a><font color="black"></font></td>
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