<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/scoremanager/main/common/base.jsp">
	<c:param name="title">得点管理システム</c:param>
	
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
		<section class="me-4">
		    <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績削除選択</h2>
			<p>削除するデータを選択してください</p>
		   <c:choose>
		       <c:when test="${tests_list.size() > 0}">
					<table class="table mx-3">
						<thead class="table-light border-bottom">
		                 	<tr>
		                     	<th>科目名</th>
								<th>科目コード</th>
								<th>回数</th>
								<th>点数</th>
								<th></th>
		                 	</tr>
		                 </thead>
		                 <thead>
			                 <c:forEach var="test" items="${tests_list}">
			                     <tr class="border-bottom">
			                         <td>${test.subjectName}</td>
			                         <td>${test.subjectCd }</td>
			                         <td>${test.no }</td>
			                         <td>${test.point}</td>		
			                         <td><a href="TestListSubjectDeleteSelect.action?studentNo=${test.studentNo}&subjectCd=${test.subjectCd}&no=${test.no}">削除</a></td>			
			                     </tr>
			                 </c:forEach>
						</thead>
	         		</table>
		       </c:when> 
		       <c:otherwise>
                    <div>成績情報が存在しませんでした。</div>
                </c:otherwise>
			</c:choose>
			<a href="TestListSubjectExecute.action?f1=${tests.entYear}&f2=${tests.classNum}&f3=${tests.subjectCd}">戻る</a>
		 </section>
	</c:param>
</c:import>
