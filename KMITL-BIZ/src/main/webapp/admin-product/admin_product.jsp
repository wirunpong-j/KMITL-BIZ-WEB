<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp" />

<div class="container">
    <div class="jumbotron">
        <h1>เลือกกลุ่มสินค้า</h1>
    </div>
    <div class="back_panel">
        <button class="btn btn-info btn-inline" onclick="addGroupProduct()" style="margin-bottom: 20px; padding-top: 10px; padding-bottom: 10px;"><i class="fa fa-plus" aria-hidden="true"></i>  สร้างกลุ่มใหม่</button>
        
        <div class="row">
            <c:forEach var="grPro" items="${sessionScope.allGroupPro}">
                <a href="${SITE_URL}/SelectGroupProduct/?product=${grPro.getGroup_id()}">
                    <div class="col-sm-2">
                        <div class="product_panel" id="product_data_1">
                            <i class="fa fa-shopping-basket fa-5x" aria-hidden="true"></i>
                            <h3>${grPro.getGroup_name()}</h3>
                        </div>
                    </div>
                </a>
            </c:forEach>
        </div>
    </div>
    
</div>

<script>
    function addGroupProduct() {
        alertify.prompt( 'สร้างกลุ่มสินค้าใหม่', '<h3>กรอกชื่อกลุ่มสินค้า</h3>', ''
            , function(evt, value) { 
                $.ajax({
                    type: "POST",
                    url: "${SITE_URL}/ManageGroupProduct/?action=addGroup",
                    datatype: 'json',
                    data: {gName:value},
                    success: function(data) {
                        if ($.trim(data) === 'ADDED') {
                            alertify.alert('สร้างกลุ่มสำเร็จ', '<h3> กลุ่ม '+ value +' สร้างสำเร็จ</h3>', 
                                function(){
                                    window.location = "${SITE_URL}/admin-product/admin_product.jsp";
                                });
                        } else {
                            alertify.alert('เกิดข้อผิดพลาด', '<h3 style="color:red;">ไม่สามารถสร้างกลุ่มได้ เนื่องจากชื่อกลุ่มซ้ำหรือเกิดข้อผิดพลาดอื่น ๆ</h3>');
                        }
                    }
                });
            }
            , function() { 
                alertify.error('Cancel');
            });
    }
</script>


<jsp:include page="/template/footer.jsp" />