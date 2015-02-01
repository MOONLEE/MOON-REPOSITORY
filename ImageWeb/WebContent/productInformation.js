function doCart() {
    document.informationForm.menu.value="cart";
    document.informationForm.submit();
}

function opinionCheck() {
    if ( document.commentForm.comment.value=="" ) {
            alert("코멘트를 입력해 주세요.");
            document.commentForm.comment.focus();
            return;
    }
    if ( document.commentForm.goodbad.value=="" ) {
        alert("좋아요/싫어요를 선택해 주세요.");
        document.commentForm.goodbad.focus();
        return;
    }
    if ( document.loginForm.id.value=="" ) {
        alert("ID를 입력해 주세요.");
       document.loginForm.id.focus();
        return;
    }
    document.commentForm.menu.value="comment";
    document.commentForm.submit();
}