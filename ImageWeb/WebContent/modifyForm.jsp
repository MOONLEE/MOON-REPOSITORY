<%@page import="ShoppingSite.HomeShoppingDBCP"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import = "ShoppingSite.MemberEntity" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원정보수정</title>
<link href="style.css" rel="stylesheet" type="text/css" />
<script src="modifyForm.js"></script>
<meta name="generator" content="Namo WebEditor(Trial)">
</head>
<body>
<font color="black"><jsp:useBean id="loginfo" class="ShoppingSite.MemberEntity" scope="session"/>
<jsp:useBean id="loginfodbcp" class="ShoppingSite.HomeShoppingDBCP" scope="page" />




<%
String id = loginfo.getMember_id();
MemberEntity mem = loginfodbcp.selectMember(id);
%>	</font>
	
<form name="modifyForm" action = "modifyProc.jsp" method="post">
<table width="500" border="0" cellpadding="5"  cellspacing="5" align="center"> 
	<tr>
		<td colspan="2" align=center>
			<b> <font size="7" color="black">회원 정보 수정 </font></b>
<font color="black"></font>		</td>
	</tr>
	<tr>
		<td width=100><font color="black">사용자ID</font></td>
		<td><font color="black"><%=mem.getMember_id() %> </font></td>
		
	</tr>
	<tr>
		<td><font color="black"></font></td>
		<td><font color="black"></font></td>
	</tr>
	<tr>
		<td width=100><font color="black">비밀번호</font></td>
		<td><font color="black"><input type="password" name="password" value=""> </font></td>
	</tr>
	<tr>
		<td width=100><font color="black">비밀번호 확인</font></td>
		<td><font color="black"><input type="password" name="repassword" value=""></font></td>
	</tr>
	<tr>
		<td width=100><font color="black">이름</font></td>
		<td><font color="black"><input type="text" name="name" value="<%=mem.getName()%>"></font></td>
	</tr>
	<tr>
    	<td><font color="black">주소</font></td>
		<td width="430"><font color="black">도,광역(특별)시 입력: <input name="province" type="text" style="width:56px;" value="<%=mem.getProvince() %>" /></font></td>
	</tr>
	<tr>
		<td><font color="black"></font></td>	
		<td width="430"><font color="black">시,군 입력: <input type="text" name="town" style="width:56px" value="<%=mem.getTown()%>"/></font></td>
	</tr>
	<tr>
		<td><font color="black"></font></td>
		<td colspan="2"><font color="black">나머지 주소입력: <input name="address" type="text" style="width:310px;" value="<%=mem.getAddress() %>" /></font></td>
			</tr>
		
	<tr>
		<td width=100><font color="black">전화번호</font></td>
		<td><font color="black"><input type="text" name="phone_number" value="<%=mem.getPhone_number()%>"></font></td>
	</tr>
	<tr>
		<td><font color="black"></font></td>
		<td><font size="2" color="black">※  -없이 입력</font><font color="black"></font></td>
	</tr>
	<tr>
		<td width=100><font color="black">E-mail</font></td>
		<td><font color="black"><input type="text" name="mail" size="30" value="<%=mem.getMail()%>"></font></td>
	</tr>
	
	
	
	
	<tr>
		<td colspan="2" align = right>
			<font color="black"><input type="button" value="수 정" onClick="inputCheck()">
</font>		  <td><font color="black"><input type="button" value="취 소" onclick="location.href='index.jsp'"></font></td>			
	</tr>

</table>
</form>
</body>

</html>