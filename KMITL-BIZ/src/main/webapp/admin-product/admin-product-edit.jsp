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
            <button class="btn btn-info" id="selectProduct" style="margin-right: 20px;"><i class="fa fa-check-square-o" aria-hidden="true"></i>  เลือกสินค้าทั้งหมดในกลุ่มนี้</button>
            <button class="btn btn-success" id="addProduct" style="margin-right: 20px;"><i class="fa fa-plus" aria-hidden="true"></i>  เพิ่มสินค้าใหม่ในกลุ่มนี้</button>
            <button class="btn btn-warning btn-manage" id="moveProduct" style="margin-right: 20px;" disabled><i class="fa fa-arrows" aria-hidden="true"></i>  ย้ายสินค้าที่เลือกไปกลุ่มอื่น</button>
            <button class="btn btn-danger btn-manage" id="removeProduct" disabled><i class="fa fa-trash" aria-hidden="true"></i>  ลบสินค้าที่เลือก</button>
        </div>
        <input type="hidden" id="groupID" value="${requestScope.gProduct.getGroup_id()},${requestScope.gProduct.getGroup_name()}">
        <div class="row funkyradio">
            <c:forEach var="pro" items="${requestScope.gProduct.getAllProduct()}">
                <span class="funkyradio-success col-sm-3">
                    <input class="checkbox1" type="checkbox" name="checkbox" id="pro-${pro.getProduct_id()}" value="${pro.getProduct_id()}"/>
                    <label for="pro-${pro.getProduct_id()}">${pro.getProduct_name()}</label>
                </span>
            </c:forEach>
        </div>
    </div>
    
</div>
        
<script>
    var checked = false;
    var product = '';
    let groupID = $('#groupID').val().split(',');
    
    $('#addProduct').click(function() {
        alertify.prompt('เพิ่มสินค้าใหม่', '<h3>ใส่ชื่อสินค้า</h3>', ''
            ,function(evt, value) { 
                 $.ajax({
                     type: "POST",
                     url: "${SITE_URL}/ManageGroupProduct/?action=addProduct",
                     data: {productName:value, groupID:groupID[0]},
                     success: function(data) {
                         if ($.trim(data) === 'ADDED') {
                            alertify.alert('เพิ่มสินค้าใหม่เสร็จสิ้น', '<h3>ทำการเพิ่มสินค้า <strong>"' + value + '"</strong> ลงในกลุ่ม <strong>"' + groupID[1] + '"</strong> เรียบร้อยแล้ว</h3>', 
                                function() {
                                    window.location = "${SITE_URL}/SelectGroupProduct/?product=" + groupID[0];
                                });                       
                         } else {
                             alertify.error('ERROR');
                         }
                     }
                 });
            }
            ,function() { 
                alertify.error('Cancel');
            });
    });
    
    $('#moveProduct').click(function() {
        var text = '<h3>เลือกกลุ่มที่ต้องการจะย้าย</h3><select class="form-control" id="moveTo">';
        <c:forEach var="grPro" items="${sessionScope.allGroupPro}">
                text += '<option>${grPro.getGroup_id()}:${grPro.getGroup_name()}</option>';
        </c:forEach>
        text += '</select>';
        alertify.confirm('ย้ายสินค้าไปกลุ่มอื่น', text
            , function(){
                $.ajax({
                     type: "POST",
                     url: "${SITE_URL}/ManageGroupProduct/?action=moveProduct",
                     data: {product:product, groupID:$('#moveTo').val().split(':')[0]},
                     success: function(data) {
                         if ($.trim(data) === 'MOVED') {
                            alertify.alert('ย้ายสินค้าเสร็จสิ้น', '<h3>ทำการย้ายสินค้าที่เลือกไปยังกลุ่ม <strong>"' + $('#moveTo').val().split(':')[1] + '"</strong> เรียบร้อย </h3>', 
                                function() {
                                    window.location = "${SITE_URL}/SelectGroupProduct/?product=" + groupID[0];
                                });                       
                         } else {
                             alertify.error('ERROR');
                         }
                     }
                 });
            }
            , function(){ 
                alertify.error('Cancel');
            });
    });
    
    $('#selectProduct').click(function() {
        this.checked = !this.checked;
        $('input:checkbox').prop('checked', this.checked);
        product = $('.checkbox1:checked').map(function() {return this.value;}).get().join(',');
        $('.btn-manage').attr('disabled', productIsEmpty());
    });
    
    $('input.checkbox1').on('click', function() {
        product = $('.checkbox1:checked').map(function() {return this.value;}).get().join(',');
        $('.btn-manage').attr('disabled', productIsEmpty());
    });
    

    function toSelectGroupProduct() {
        window.location = "${SITE_URL}/admin-product/admin_product.jsp";
    }
    
    function productIsEmpty() {
        if (product === '') {
            return true;
        } else {
            return false;
        }
    }
    
    
</script>

<jsp:include page="/template/footer.jsp" />
