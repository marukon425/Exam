<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/scoremanager/main/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
    	<section class="me-4">
		     <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">
		         科目情報削除
		     </h2>
		
		
		     <p>
		         「${subject.name}（${subject.cd}）」を削除してもよろしいですか
		     </p>
		
		
		     <form action="SubjectDeleteExecute.action" method="post">
		
		         <input type="hidden" name="cd" value="${subject.cd}">
		
		         <input type="submit"
		                value="削除"
		                style="background-color:red; color:white;
		                       border:none; border-radius:5px;
		                       padding:7px 12px;">
		     </form>
		
		     <br>
		
		
		     <a href="SubjectList.action">戻る</a>
		</section>
	</c:param>
</c:import>
