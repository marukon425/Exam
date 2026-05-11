<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 
<c:import url="/scoremanager/main/common/base.jsp">
<c:param name="title">得点管理システム</c:param>
<c:param name="scripts"></c:param>
<c:param name="content">
	<section class="me-4">
	<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績情報削除</h2>
	 	<p class="alert alert-success px-4 py-1 text-center small rounded-0"style="width:100%; background-color:#8CC3A9; text-align:center; margin: 1px 0px 10%; ">削除が完了しました</p>
		<a href="TestListSubjectExecute.action?f1=${entYear}&f2=${classNum}&f3=${subjectCd}">科目別成績一覧</a>
		<a href="TestList.action" style="margin:40px">成績一覧</a>
	</section>
</c:param>
</c:import>
 
