<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/scoremanager/main/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">


            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">
                学生情報変更
            </h2>
            <form action="StudentUpdateExecute.action" method="post"> 


                <!-- 入学年度（表示のみ） -->
                <div class="mb-2">
                    <label class="form-label">入学年度</label>
                </div>
                <div class="mb-3">
                    <span>${ent_year}</span>
                </div>
                <input type="hidden" name="ent_year" value="${ent_year}">

                <!-- 学生番号（表示のみ） -->
                <div class="mb-2">
                    <label class="form-label">学生番号</label>
                </div>
                <div class="mb-3">
                    <span>${no}</span>
                </div>
                <input type="hidden" name="no" value="${no}">

                <!-- 氏名 -->
                <div class="mb-2">
                    <label class="form-label">氏名</label>
                </div>
                <div class="mb-4">
                    <input type="text"
                           name="name"
                           value="${name}"
                           class="form-control"
                           required>
                </div>

					<div class="col-12">
					    <label class="form-label" for="student-f2-select">クラス</label>
					    <select class="form-select" id="student-f2-select" name="class_num">
					        <option value="0"></option>
					        <c:forEach var="num" items="${class_num_set}">
					            <option value="${num}" <c:if test="${num == class_num}">selected</c:if>>
					                ${num}
					            </option>
					        </c:forEach>
					    </select>
					</div>



                    <%-- <div class="col-4">
                        <label class="form-label" for="student-f2-select">クラス</label>
                        <select class="form-select" id="student-f2-select" name="f2">
                            <option value="0">--------</option>
                            <c:forEach var="num" items="${class_num_set }">
                            
                                <option value="${num }" <c:if test="${num==f2 }">selected</c:if>>${num }</option>
                            </c:forEach>
                        </select>
                    </div>--%>

                <!-- 在学中 -->
                <div class="mb-2">
                    <label class="form-label">在学中</label>
                </div>
                <div class="mb-4">
                    <input type="checkbox"
                           name="is_attend"
                           value="1"
                           checked>
                    在学中
                </div>


               <!-- 変更ボタン -->
                <button type="submit" class="btn btn-primary">
                    変更
                </button>

            </form>

            <!-- 戻る -->
            <div class="mt-3">
</a>
            </div>

        </section>
    </c:param>
</c:import>