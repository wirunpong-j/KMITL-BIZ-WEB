<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/template/header.jsp" />

  <div class="container">
    <div class="jumbotron" id="admin_cust_history">
        <h1>ดูข้อมูลการจอง</h1>
    </div>

    <form action="${SITE_URL}/SearchOrder" method="POST">
        <div class="form-group">
            <label for="search_cust">รหัสรับบริการ</label>
    <c:choose>
        <c:when test="${requestScope.status == 'true'}">
            <input type="text" class="form-control" id="search_cust" name="custid" placeholder="รหัสรับบริการ" value="${requestScope.customer.getCust_id_str()}">
        </c:when>
        <c:otherwise>
            <input type="text" class="form-control" id="search_cust" name="custid" placeholder="รหัสรับบริการ">
        </c:otherwise>
    </c:choose>
            <br>
            <button type="submit" class="btn btn-default" name="click">ค้นหา</button>
        </div>
    </form>
    <br><br><br>
    
    <c:choose>
        <c:when test="${requestScope.status == 'true'}">
            <h2>รหัสรับบริการ ${requestScope.customer.getCust_id_str()} : คุณ ${requestScope.customer.getFullname()} </h2>
            <table class="table table-bordered table-striped table-list" id="myTable">
                <thead>
                    <tr>
                        <th>รหัสการจอง</th>
                        <th>วันที่ออกบิล</th>
                        <th>วันที่จอง</th>
                        <th>พื้นที่ที่จอง</th>
                        <th>สินค้าที่ขาย</th>
                        <th>ราคาจอง</th>
                        <th>ค่าใช้จ่ายเพิ่มเติม</th>
                        <th>หมายเหตุ</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="id" items="${requestScope.keyOrder}">
                        <c:set var="order" scope="request" value="${requestScope.allOrder[id]}"></c:set>
                        <tr>
                            <td>${id}</td>
                            <td>${order.getOrder_date()}</td>
                            <td>${order.getRent_date()}</td>
                            <td>${order.getArrayZoneID()}</td>
                            <td>${order.getArrayProductName()}</td>
                            <td>${order.getPrice()}</td>
                            <td>${order.getExtra_price()}</td>
                            <td>${order.getNote()}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:when test="${requestScope.status == 'false'}">
            <script>
                alertify.error("ไม่มีข้อมูล");
            </script>
        </c:when>
        <c:otherwise>
        </c:otherwise>
    </c:choose>
    <br><br>
  </div>

<script>
    $(document).ready(function(){
        $('#myTable').DataTable();
    });
</script>

<jsp:include page="/template/footer.jsp" />