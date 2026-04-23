<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/scoremanager/main/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">

            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">
                科目情報変更
            </h2>

            <form action="SubjectUpdateExecute.action" method="post">


                <input type="hidden"
                       name="cd"
                       value="${cd}">


                <div class="mb-1">
                    <label class="form-label">
                        科目コード
                    </label>
                </div>

                <div class="mb-3">
                    <span class="fw-bold">
                        ${cd}
                    </span>
                    <div style="color: #FFCC00;">${deletecd}</div>
                </div>

                <!-- 科目名 -->
                <div class="mb-2">
                    <label class="form-label">
                        科目名
                    </label>
                </div>

                <div class="mb-4">
                    <input type="text"
                           name="name"
                           value="${name}"
                           maxlength="20"
                           required
                           class="form-control">
                </div>

                <!-- 変更ボタン -->
                <button type="submit" class="btn btn-primary">
                    変更
                </button>

            </form>

            <!-- 戻るリンク -->
            <div class="mt-3">
                <a href="SubjectList.action">戻る</a>
            </div>

        </section>
    </c:param>
</c:import>