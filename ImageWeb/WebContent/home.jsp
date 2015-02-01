<%@page import="ShoppingSite.SalesVolumeBoardEntity"%>
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
<h1> <font color="black">베스트 상품!!!</font></h1>
<%@page import="java.util.ArrayList,ShoppingSite.SalesVolumeBoardEntity" %>
<jsp:useBean id="homeshoppingdbcp" class="ShoppingSite.HomeShoppingDBCP" scope="page" />
<% 
	request.setCharacterEncoding("euc-kr");
%>

<%
	ArrayList<SalesVolumeBoardEntity> svbeList = homeshoppingdbcp.getTop4Product();
	int count = svbeList.size();
%>
<table  width="464" border=2 style="background-color:white;" height="116">
    <tr>
    	<th width="68"><b><font color="black">순위</font></b></th>
        <th scope="col" width="194"><b><font color="black">사진</font></b></th>
        <th scope="col" width="87"><b><font color="black">이름</font></b></th>
        <th scope="col" width="85"><b><font color="black">가격</font></b></th>
    </tr>
    <%
   	if (count > 0){
   		int i = 1;
    	for(SalesVolumeBoardEntity svbe : svbeList){
    %>
    <tr onmouseover="this.style.backgroundColor='SkyBlue'"
    	onmouseout="this.style.backgroundColor='white'">
        <td width="68">
            <p align="center">  <font color="black"><%=i%></font></p>
        </td>
        <td align=left width="194" height="84">
            <p align="center"><a href="categoryprocess.jsp?menu=productInfomation&product_id=<%=svbe.getProduct_id()%>"><font color="black"><img src="GetProductImage?product_id=<%=svbe.getProduct_id()%>" width="136" height="83"></font></a></p>
        </td>
        <td align=left width="87" height="84">
            <p align="center"><a href="categoryprocess.jsp?menu=productInfomation&product_id=<%=svbe.getProduct_id()%>"><b><font color="black"><%=svbe.getName() %></font></b></a></p>
        </td>
        <td align=left width="85" height="84">
            <p align="center"><a href="categoryprocess.jsp?menu=productInfomation&product_id=<%=svbe.getProduct_id()%>"><b><font color="black"><%=svbe.getPrice() %></font></b></a></p>
        </td>
    </tr>
    <%
    		i++;
    		if(i==5){
    			break;
    		}
    	}
    }
	%>
</table>
</body>
</html>