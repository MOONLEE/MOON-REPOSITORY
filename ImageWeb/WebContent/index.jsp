<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<link href="style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<jsp:useBean id="loginfo" class="ShoppingSite.MemberEntity" scope="session"/>
<jsp:useBean id="categoryNumber" class="ShoppingSite.WhichBoard" scope="session"/>

	  <!--  �Ӹ� ����   -->
	 <div id="topheader">
	   <div class="logo"></div>

	  </div>
	 <div id="search_strip">
<!-- 	   <div class="freeregistration">
	
	 </div> -->	 
	   <div class="search_area">
	    <div class="search_box">
				<%--�˻� ���� --%>

				<form action="categoryprocess.jsp" method="POST">
				<input type="hidden" name="menu" value="search">
					 <SELECT name="category">
						<option value="pants">����</option>
						<option value="shoes">�Ź�</option>
						<option value="tshirts">Ƽ</option>
						<option value="etc">��ȭ</option>
						<option value="outer">�ƿ���</option>
					</SELECT> <input type="text" name="input_Search" />
					 		  <input type="submit" value="�˻�" />

				</form>

				<%--�˻� �� --%>
	    </div>
	  </div>
	 </div>
	  <!--  �ٵ� ����   -->
	<div id="body_area">
		<!--  �޴� �κ�  -->
	  <div class="left">
<%
		pageContext.include("category.jsp");
%>   
	  </div>	
	  	<!--  �޴� �κ�   �� -->
	  <!--  �Խ��� �κ�   -->
	  <div class="midarea">
<%
	int boardNumber = categoryNumber.getPageNumber();

	if(boardNumber==1){
		pageContext.include("pants.jsp");
	}else if(boardNumber==2){
		pageContext.include("shoes.jsp");
	}else if(boardNumber==3){
		pageContext.include("tshirts.jsp");
	}else if(boardNumber==4){
		pageContext.include("etc.jsp");
	}else if(boardNumber==5){
		pageContext.include("outer.jsp");
	}else if(boardNumber==6){
		pageContext.include("cart.jsp");
	}else if(boardNumber==7){
		pageContext.include("checkOrder.jsp");
	}else if(boardNumber==8){
		pageContext.include("order.jsp");
	}else if(boardNumber==9){
		pageContext.include("refund.jsp");
	}else if(boardNumber==10){
		pageContext.include("productInformation.jsp");
	}else if (boardNumber == 11) {
		pageContext.include("search.jsp");
	}else if(boardNumber==12){
		pageContext.include("joinForm.jsp");
	}else{
		pageContext.include("home.jsp");
	}

%>
	  </div>
 	<!--  �Խ��Ǻκ�  �� -->
 	  <!-- �α��� �κ� -->
	  <div class="right">
	  <form action="loginprocess.jsp" method="post" name="loginForm">
	<% 
	  	
		request.setCharacterEncoding("euc-kr");

		String id =loginfo.getMember_id();
		if (id != null){
			pageContext.include("loginfo.jsp"); 
		}else{
			pageContext.include("login.jsp");
		}
	%>
	<!--  �α׸� �κ� �� -->
		  </form>	
		</div>

</div>

<!--  
<div id="foot">
	<div class="foot_copyrights">
	  	<div align="center">
	  		&copy; Copyright Information Goes Here. All Rights Reserved
	  	</div>
	</div>
	<div class="foot_designed">
		<div align="center">
	       Designed By : HomPage
	    </div>
	  </div>
</div>
-->
</body>
</html>