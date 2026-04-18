<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/scoremanager/main/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>
			<form class="" action="SubjectCreateExcecute.action" method="post">
				<label>科目コード</label><br>
				<input type="text" name="cd" value="${cd}" placeholder="科目コードを入力してください" style="border-radius: 5px; width: 820px;"><br><br>
				<label>科目名</label><br>
				<input type="text" name="name" value="${name}" placeholder="科目名を入力してください" style="border-radius: 5px; width: 820px;"><br><br>
				<input class="touroku" type="submit" value="登録" style="background-color: #3399FF; color: white; border: none; border-radius: 5px; padding: 5px 10px; font-size: 15px"> <br><br>
				<a href="" >戻る</a>
			</form>
        </section>

    </c:param>
</c:import>