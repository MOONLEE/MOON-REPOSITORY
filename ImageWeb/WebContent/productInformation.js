function doCart() {
    document.informationForm.menu.value="cart";
    document.informationForm.submit();
}

function opinionCheck() {
    if ( document.commentForm.comment.value=="" ) {
            alert("�ڸ�Ʈ�� �Է��� �ּ���.");
            document.commentForm.comment.focus();
            return;
    }
    if ( document.commentForm.goodbad.value=="" ) {
        alert("���ƿ�/�Ⱦ�並 ������ �ּ���.");
        document.commentForm.goodbad.focus();
        return;
    }
    if ( document.loginForm.id.value=="" ) {
        alert("ID�� �Է��� �ּ���.");
       document.loginForm.id.focus();
        return;
    }
    document.commentForm.menu.value="comment";
    document.commentForm.submit();
}