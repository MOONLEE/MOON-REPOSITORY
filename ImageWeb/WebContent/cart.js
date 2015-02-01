function doOrderInCart() {
    document.cartForm.menu.value='order';
    document.cartForm.submit();
}

function doDelete(product_id,color,size){
    document.cartForm.menu.value='delete';
    document.cartForm.product_id.value= product_id;
    document.cartForm.color.value=color;
    document.cartForm.size.value=size;
    document.cartForm.submit();
}