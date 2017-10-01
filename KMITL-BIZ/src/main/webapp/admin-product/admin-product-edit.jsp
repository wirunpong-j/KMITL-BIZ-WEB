<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp" />

<div class="container">
    <div class="jumbotron">
        <h1>กลุ่ม ${requestScope.gProduct.getGroup_name()}</h1>
    </div>
    <div class="back_panel">
        <div style="display:inline-block;">
            <button class="btn btn-default" style="margin-right: 100px;" onclick="toSelectGroupProduct()" ><i class="fa fa-arrow-circle-o-left" aria-hidden="true"></i>  กลับไปเลือกกลุ่ม</button>
            <button class="btn btn-info" style="margin-right: 20px;"><i class="fa fa-check-square-o" aria-hidden="true"></i>  เลือกสินค้าทั้งหมดในกลุ่มนี้</button>
            <button class="btn btn-success" style="margin-right: 20px;"><i class="fa fa-plus" aria-hidden="true"></i>  เพิ่มสินค้าใหม่ในกลุ่มนี้</button>
            <button class="btn btn-warning" style="margin-right: 20px;"><i class="fa fa-arrows" aria-hidden="true"></i>  ย้ายสินค้าที่เลือกไปกลุ่มอื่น</button>
            <button class="btn btn-danger"><i class="fa fa-trash" aria-hidden="true"></i>  ลบสินค้าที่เลือก</button>
        </div>
        <div class="row funkyradio">
            <c:forEach var="pro" items="${requestScope.gProduct.getAllProduct()}">
                <span class="funkyradio-success col-sm-3">
                    <input class="checkbox1" type="checkbox" name="checkbox" id="pro-${pro.getProduct_id()}" value="${pro.getProduct_name()}"/>
                    <label for="pro-${pro.getProduct_id()}">${pro.getProduct_name()}</label>
                </span>
            </c:forEach>
        </div>
    </div>
    
</div>
        
<script>

    function toSelectGroupProduct() {
        window.location = "${SITE_URL}/admin-product/admin_product.jsp";
    }
    
    
</script>

<jsp:include page="/template/footer.jsp" />
