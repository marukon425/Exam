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
		     <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">
		         成績情報削除
		     </h2>
		
		
		     <p>「${tests.subjectName}(${tests.no }回目：${tests.point}点)」を削除してもよろしいですか</p>
		
		
		     <form action="TestListStudentDeleteExecute.action" method="post">
		
		          <input type="hidden" name="student_no" value="${tests.studentNo}">
				  <input type="hidden" name="subject_cd" value="${tests.subjectCd}">
				  <input type="hidden" name="no" value="${tests.no}">

		         <input type="submit"
		                value="削除"
		                style="background-color:red; color:white;
		                       border:none; border-radius:5px;
		                       padding:7px 12px;">
		     </form>
		
		     <br>
		
		
		     <a href="TestListStudentExecute.action?f4=${tests.studentNo }">戻る</a>
		</section>
	</c:param>
</c:import>
