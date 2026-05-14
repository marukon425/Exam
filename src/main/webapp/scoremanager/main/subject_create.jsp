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
				<label>科目コード</label>
				<div class="mb-4">
                    <input type="text"
                           name="cd"
                           placeholder="科目コードを入力してください"
                           maxlength="3"
                           required
                           class="form-control">
                </div>
				<div style="color: #FFCC00;">${errorcd}</div>
				<div style="color: #FFCC00;">${errorlength}</div>
				<label>科目名</label>
				<div class="mb-4">
                    <input type="text"
                           name="name"
                           placeholder="科目名を入力してください"
                           maxlength="20"
                           required
                           class="form-control">
                </div>
				<input class="touroku" type="submit" value="登録" style="background-color: #3399FF; color: white; border: none; border-radius: 5px; padding: 5px 10px; font-size: 15px"> <br><br>
				<a href="SubjectList.action" >戻る</a>
			</form>
        </section>

    </c:param>
</c:import>