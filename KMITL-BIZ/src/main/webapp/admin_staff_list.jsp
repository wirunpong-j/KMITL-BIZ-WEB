<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="Listener.Constant"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template/header.jsp" />


<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>KMITL Biz Way</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
  <link rel="stylesheet" href="css/style.css">
</head>

<body>
  <div class="container">
    <div class="jumbotron" id="admin_staff_history">
      <h1>รายการพนักงาน</h1>
    </div>

    <table class="table table-striped">
        <tr>
            <th>ชื่อผู้ใช้</th>
            <th>รหัสผ่าน</th>
            <th>ชื่อจริง</th>
            <th>นามสกุล</th>
            <th>ประเภท</th>
        </tr>
        <%
            Connection conn = null;
            try {
                conn = (Connection) Constant.getConnection();

                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM staff");
                
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()){ %>
                <tr>

                <td><%=rs.getString("staff_id")%></td>
                <td><%=rs.getString("password")%></td>
                <td><%=rs.getString("fname")%></td>
                <td><%=rs.getString("lname")%></td>
                <td><%=rs.getString("role")%></td>

                </tr>
                <%
                    }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            } finally {
                if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
            }
        %>
    </table>
  </div>

  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
  <script src="https://use.fontawesome.com/211152dd25.js"></script>
</body>

</html>

<jsp:include page="template/footer.jsp" />