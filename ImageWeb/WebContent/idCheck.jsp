<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ID �ߺ�Ȯ��</title>
<link href="style.css" rel="stylesheet" type="text/css" />
<script src="join.js"></script>
</head>
<body>

<jsp:useBean id="check" class="ShoppingSite.HomeShoppingDBCP" scope="page"/>

<%
String member_id = request.getParameter("member_id");
boolean chk = check.checkId(member_id);
%>
<center><p>
<b> <%= member_id %></b>
<%
if(chk)
	out.println("��(��) �̹� �����ϴ� ���̵� �Դϴ�.<p>");
else
	out.println("��(��) ��� �����մϴ�.<p>");
%>

<a href="#" onclick="self.close()">�ݱ�</a>
</center>

</body>
</html>