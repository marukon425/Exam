<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/scoremanager/main/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
    <c:param name="content">

        <section class="me-4">


            <h2 class="h3 mb-4 fw-norma bg-secondary bg-opacity-10 py-2 px-4">
                ログアウト
            </h2>


<p class="alert alert-success px-4 py-1 mb-4 text-center small rounded-0">
    ログアウトしました
</p>


			<div class="mt-3 px-4">
			    <a href="${pageContext.request.contextPath}/scoremanager/Login.action">ログイン</a>
			</div>

        </section>

    </c:param>
</c:import>