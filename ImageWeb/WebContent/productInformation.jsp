<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<meta name="generator" content="Namo WebEditor(Trial)">
<script src="productInformation.js"></script>
</head>
<body>
<%@page import="java.util.ArrayList,ShoppingSite.AreaEntity,ShoppingSite.ProductEntity,ShoppingSite.OpinionEntity,ShoppingSite.ProductOptionEntity" %>
<jsp:useBean id="homeshoppingdbcp" class="ShoppingSite.HomeShoppingDBCP" scope="page" />
<jsp:useBean id="loginfo" class="ShoppingSite.MemberEntity" scope="session"/>
<%
	request.setCharacterEncoding("euc-kr");
	String product_id = request.getParameter("product_id");
	ProductEntity pe = homeshoppingdbcp.getProductInfomation(product_id);
	ArrayList<ProductOptionEntity> poList = homeshoppingdbcp.getProductOption(product_id);
	ArrayList<OpinionEntity> peList = homeshoppingdbcp.getOpinionInformation(product_id);

%>
<!--  �Խ��� ��ǰ���� ���̺� -->
<form action="productInformationprocess.jsp" method="post" name="informationForm">
<input type="hidden" name="menu" value="">
<input type="hidden" name="product_id" value='<%=pe.getProduct_id() %>'>
<table width="465" style="background-color:white;" cellpadding="0" cellspacing="0">
    <tr>
        <td width="210"><font color="black"><img src="GetProductImage?product_id=<%=pe.getProduct_id()%>" width=200 height=200></font></td>
        <td width="252">
            <table width="234" cellpadding="0" cellspacing="0" height="137" align="center">
                <tr>
                    <td width="76">
                        <p align="center"><font face="Arial Black" color="black">�̸�</font><font color="black"></font></p>
                    </td>
                    <td width="140">
                        <p align="center"><font face="Arial Black" color="black"><%=pe.getName() %></font><font color="black"></font></p>
                    </td>
                </tr>
                <tr>
                    <td width="76">
                        <p align="center"><font face="Arial Black" color="black">����</font><font color="black"></font></p>
                    </td>
                    <td width="140">
                        <p align="center"><font face="Arial Black" color="black"><%=pe.getPrice() %></font><font color="black"></font></p>
                    </td>
                </tr>
                <tr>
                    <td width="76">
                        <p align="center"><font face="Arial Black" color="black">����</font><font color="black"></font></p>
                    </td>
                    <td width="140">
                        <p align="center"><font face="Arial Black" color="black"><%=pe.getType() %></font><font color="black"></font></p>
                    </td>
                </tr>
                <tr>
                    <td width="76" height="47">
                        <p align="center"><font color="black">�ɼ�</font></p>
                    </td>
                    <td width="140" height="47">
                    
 
						<select size="1" id="options" name="options">
						<%
							for(ProductOptionEntity po : poList){
						%>
							<option value="<%=po.getColor()%>-<%=po.getSize()%>-<%=po.getAmount()%>"><%=po.getColor() %>-<%=po.getSize() %>-<%=po.getAmount() %></option>
						<%
							}
						%>
						</select>

					
                    </td>
                </tr>
                <tr>
                	<td width="76" height="47">
                		<p align="center"><font color="black">����</font></p>
                	</td>
                	<td width="76" height="47">-->
                	       <font face="Arial Black" color="black"><select size="1" id="count" name="count">
                	        <%
                	        	for(int i=1;i<5;i++){
                	        %>
                            <option value="<%=i%>"><%=i %></option>
                            <%
                            }
                            %>
			 			</select></font><font color="black">   </font>
                	</td>
                </tr>
            </table>
<font color="black">                <%
                if(loginfo.getMember_id()!=null){
            		String province = loginfo.getProvince();
            		String town = loginfo.getTown();
            		AreaEntity ae = homeshoppingdbcp.getSalesInformationForArea(product_id, province, town);
            		if(ae.getProvince()!=null){
                %>
</font>                
              <font color="black"><%=ae.getProvince() %> <%=ae.getTown() %>���� �ȸ� ��ǰ ���� : <%=ae.getAmount() %></font>
              <%
            		}
             	 }
              %>
              
            <div>
                <p align="center"><input type="button" value="��ٱ���" onclick="doCart()"></p>
            </div>			
        </td>
    </tr>
</table>

</form>

<!--  �Խ��� ���� ���̺� -->

 
<table width="465" height="188" cellpadding="0" cellspacing="0">
	<tr>
        <td width="465" height="180" bgcolor="white">
            <p align="center"><font color="black"><%=pe.getContent()%></font></p>
        </td>
	</tr>
	
	<!--  �Խ��� �ڸ�Ʈ ���̺� -->
</table>

<font color="black"><!-- ��ǰ�� �ǰ� ���̺� -->
</font>
<form action="productInformationprocess.jsp" method="post" name="commentForm">
<font color="black"><input type="hidden" name="menu" value="">
<input type="hidden" name="product_id" value='<%=pe.getProduct_id() %>'>

 </font><table width="464" height="118" cellpadding="0" cellspacing="0" bgcolor="white">
	<tr>
		<td width="458" align="center"><font color="black">��ǰ��</font></td>
	</tr>
	<%
	for(OpinionEntity oe : peList){
	%>
	<tr>
		<td width="458"><font color="black"><%=oe.getMember_id() %>&nbsp;&nbsp;<%=oe.getGoodbad() %></font></td>
	</tr>
	<tr>
		<td><p><font color="black"><%=oe.getComment() %></font></p></td>
	</tr>
	<%
	}
	%>
	<tr>
		<td>
			<font color="black"><input type="radio"	name="goodbad" value="���ƿ�"> ���ƿ�
			<input type="radio" name="goodbad" value="�Ⱦ��"> �Ⱦ��</font>
		</td>
	</tr>
    <tr>
        <td width="458" height="56"><font color="black"><textarea rows="1" cols="43" name="comment"></textarea>
        <input type="image" src="images/button2.gif" width="75" height="40" border="0" onclick="opinionCheck()"></font></td>
    </tr>
</table>
</form>
<p>&nbsp;</p>
</body>
</html>