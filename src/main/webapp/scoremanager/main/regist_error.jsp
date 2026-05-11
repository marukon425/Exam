<%
    Exception e = (Exception) request.getAttribute("e");
    if (e != null) {
%>
    <p>エラー内容：<%= e.getMessage() %></p>
<%
    }
%>
