<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp" />

  <div class="container">
    <div class="jumbotron">
      <h1>จัดการลูกค้า</h1>
    </div>
    <div class="row">
      <a href="${SITE_URL}/admin-customer/admin_cust_add.jsp">
        <div class="col-md-4">
          <div class="admin_panel" id="admin_cust_add">
            <i class="fa fa-user-plus fa-5x" aria-hidden="true"></i>
            <h3>เพิ่มลูกค้าใหม่</h3>
          </div>
        </div>
      </a>
      <a href="${SITE_URL}/admin-customer/admin_cust_edit.jsp">
        <div class="col-md-4">
          <div class="admin_panel" id="admin_cust_edit">
            <i class="fa fa-pencil fa-5x" aria-hidden="true"></i>
            <h3>ดู/แก้ไข ข้อมูลลูกค้า</h3>
          </div>
        </div>
      </a>
      <a href="${SITE_URL}/admin-customer/admin_cust_history.jsp">
      <div class="col-md-4">
        <div class="admin_panel" id="admin_cust_history">
          <i class="fa fa-file fa-5x" aria-hidden="true"></i>
          <h3>ดูข้อมูลการจอง</h3>
        </div>
      </div>
      </a>
    </div>
  </div>

</html>


<jsp:include page="/template/footer.jsp" />