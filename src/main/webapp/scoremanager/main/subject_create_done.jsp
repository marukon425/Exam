<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/scoremanager/main/common/base.jsp">
	
	<c:param name="title">
		得点管理システム
	</c:param>
	
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
		<section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>

			<p><label style="width:100%; background-color:#8CC3A9; text-align:center; margin: 1px 0px 10%; ">登録が完了しました</label></p>
            
            <a href="subject_create.jsp">戻る</a>
            <a href="SubjectList.action"style="margin:40px">科目一覧</a>
        </section>
    </c:param>
</c:import>