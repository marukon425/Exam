<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="..//common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">
			<h2>科目情報登録</h2>
			<label>科目コード</label><br>
			<input type="text" name="cd" value="${cd}"><br>
			<label>科目名</label><br>
			<input type="text" name="name" value="${name}"><br>
			<input type="submit" value="登録"> <br>
			<a href="" >戻る</a>
        </section>
    </c:param>
</c:import>