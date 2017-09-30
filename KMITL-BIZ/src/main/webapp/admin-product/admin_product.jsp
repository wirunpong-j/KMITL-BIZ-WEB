<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp" />

  <div class="container">
    <div class="jumbotron">
      <h1>จัดการกลุ่มสินค้า</h1>
    </div>
        <div class="row">
        <div class="col-md-12">
            <div class="back_panel">
                <div>
                    <ul style="display: inline-block;" class="nav nav-pills" id="myTab">
                        <li class="active dropdown"> <a href="#" class="dropdown-toggle" data-toggle="dropdown">เลือกกลุ่มสินค้า <span class="caret"></span></a>
                            <ul class="dropdown-menu" role="menu">
                                <c:forEach var="grPro" items="${sessionScope.allGroupPro}">
                                    <li><a class="proGroup" href="#group${grPro.getGroup_id()}" data-toggle="tab">${grPro.getGroup_name()}</a></li>
                                </c:forEach>
                            </ul>
                        </li>
                        <button class="btn btn-info btn-inline" style=" margin-left: 10px; padding-top: 9px; padding-bottom:9px;"><i class="fa fa-plus" aria-hidden="true"></i>  สร้างกลุ่มใหม่</button>
                    </ul>
                    
                    <div class="tab-content">
                        <c:forEach var="grPro" items="${sessionScope.allGroupPro}">
                            <div class="tab-pane fade in" id="group${grPro.getGroup_id()}">
                                <div style="display:inline-block; vertical-align: middle;">
                                    <h1 style="display: inline-block; margin-right: 150px;">${grPro.getGroup_name()}</h1>
                                    <div style="display:inline-block;">
                                        <button class="btn btn-info" style="margin-top: -15px; margin-right: 20px;" value="${grPro.getGroup_id()},${grPro.getGroup_name()}"><i class="fa fa-check-square-o" aria-hidden="true"></i>  เลือกสินค้าทั้งหมดในกลุ่มนี้</button>
                                        <button class="btn btn-success" style="margin-top: -15px; margin-right: 20px;" value="${grPro.getGroup_id()},${grPro.getGroup_name()}"><i class="fa fa-plus" aria-hidden="true"></i>  เพิ่มสินค้าใหม่ในกลุ่มนี้</button>
                                        <button class="btn btn-warning" style="margin-top: -15px; margin-right: 20px;" value="${grPro.getGroup_id()},${grPro.getGroup_name()}"><i class="fa fa-arrows" aria-hidden="true"></i>  ย้ายสินค้าที่เลือกไปกลุ่มอื่น</button>
                                        <button class="btn btn-danger" style="margin-top: -15px" value="${grPro.getGroup_id()},${grPro.getGroup_name()}"><i class="fa fa-trash" aria-hidden="true"></i>  ลบสินค้าที่เลือก</button>
                                    </div>
                                </div>
                                <div class="row funkyradio" id="funky${grPro.getGroup_id()}">
                                    <c:forEach var="pro" items="${grPro.getAllProduct()}">
                                        <span class="funkyradio-success col-sm-3">
                                            <input class="checkbox1" type="checkbox" name="checkbox" id="pro-${pro.getProduct_id()}" value="${pro.getProduct_name()}"/>
                                            <label for="pro-${pro.getProduct_id()}">${pro.getProduct_name()}</label>
                                        </span>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
  </div>


<jsp:include page="/template/footer.jsp" />