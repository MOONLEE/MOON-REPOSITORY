function inputCheck(){
	
	if(document.modifyForm.password.value==""){
		alert("��й�ȣ�� Ȯ���� �ּ���.");
		document.modifyForm.password.focus();
		return;
	}
	if(document.modifyForm.repassword.value==""){
		alert("��й�ȣ�� Ȯ���� �ּ���.");
		document.modifyForm.repassword.focus();
		return;
	}
	if(document.modifyForm.password.value != document.modifyForm.repassword.value){
		alert("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
		document.modifyForm.member_id.focus();
		return;
	}
	
	if(document.modifyForm.name.value==""){
		alert("�̸��� �Է��� �ּ���.");
		document.modifyForm.name.focus();
		return;
	}
	if(document.modifyForm.province.value==""){
		alert("���� �Է��� �ּ���.");
		document.modifyForm.province.focus();
		return;
	}
	if(document.modifyForm.town.value==""){
		alert("�ּҸ� �Է��� �ּ���.");
		document.modifyForm.town.focus();
		return;
	}
	if(document.modifyForm.address.value==""){
		alert("�ּҸ� �Է��� �ּ���.");
		document.modifyForm.address.focus();
		return;
	}
	if(document.modifyForm.phone_number.value==""){
		alert("��ȭ��ȣ�� �Է��� �ּ���.");
		document.modifyForm.phone_number.focus();
		return;
		
	}
	if(document.modifyForm.mail.value==""){
		alert("�̸����� �Է��� �ּ���.");
		document.modifyForm.mail.focus();
		return;
	}
	document.modifyForm.submit();
}


