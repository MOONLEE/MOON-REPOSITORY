function inputCheck(){
	/*
	if(document.joinForm.member_id.value==""){
		alert("�����ID�� �Է��� �ּ���.");
		document.joinForm.member_id.focus();
		return;
	}
	if(document.joinForm.password.value==""){
		alert("��й�ȣ�� �Է��� �ּ���.");
		document.joinForm.password.focus();
		return;
	}
	if(document.joinForm.repassword.value==""){
		alert("��й�ȣ�� Ȯ���� �ּ���.");
		document.joinForm.repassword.focus();
		return;
	}
	if(document.joinForm.password.value != document.joinForm.repassword.value){
		alert("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		document.joinForm.member_id.focus();
		return;
	}
	if(document.joinForm.name.value==""){
		alert("�̸��� �Է��� �ּ���.");
		document.joinForm.name.focus();
		return;
	}
	if(document.joinForm.province==""){
		alert("��,����(Ư��)�ø� �Է��� �ּ���.");
		document.joinForm.province.focus();
		return;
	}
	if(document.joinForm.town==""){
		alert("��, ��, ���� �Է��� �ּ���.");
		document.joinForm.town.focus();
		return;
	}
	if(document.joinForm.address.value==""){
		alert("�ּҸ� �Է��� �ּ���.");
		document.joinForm.address.focus();
		return;
	}
	if(document.joinForm.phone_number.value==""){
		alert("��ȭ��ȣ�� �Է��� �ּ���.");
		document.joinForm.phone_number.focus();
		return;
		
	}
	if(document.joinForm.mail.value==""){
		alert("�̸����� �Է��� �ּ���.");
		document.joinForm.mail.focus();
		return;
	}
	*/
	document.joinForm.submit();
}


function idCheck(id){
	if(id == ""){
		alert("���̵� �Է����ּ���.");
		document.joinForm.member_id.focus();
	}else{
		url="idCheck.jsp?member_id=" + id;
		window.open(url,"post","witdh=50 height=30");
	}
}

