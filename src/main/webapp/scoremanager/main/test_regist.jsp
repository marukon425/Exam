<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/scoremanager/main/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">

            <!-- 見出し -->
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                成績管理
            </h2>

            <!-- ▼ フィルタ部分 -->
            <form class="" action="TestRegistExecute.action" method="get">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">

                    <!-- 入学年度 -->
                    <div class="col-2">
                        <label class="form-label">入学年度</label>
                        <select class="form-select" name="f1" style="width:120px;">
                            <option value="">--------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year == f1}">selected</c:if>>
                                    ${year}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- クラス -->
                    <div class="col-2">
                        <label class="form-label">クラス</label>
                        <select class="form-select" name="f2" style="width:120px;">
                            <option value="">--------</option>
                            <c:forEach var="classNum" items="${class_num_set}">
                                <option value="${classNum}" <c:if test="${classNum == f2}">selected</c:if>>
                                    ${classNum}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- 科目 -->
                    <div class="col-4">
                        <label class="form-label">科目</label>
                        <select class="form-select" name="f3" style="width:250px;">
                            <option value="">--------</option>
                            <c:forEach var="subject" items="${subjects}">
                                <option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>
                                    ${subject.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- 回数 -->
                    <div class="col-2">
                        <label class="form-label">回数</label>
                        <select class="form-select" name="f4" style="width:120px;">
                            <option value="">--------</option>
                            <c:forEach var="time" items="${times}">
                                <option value="${time}" <c:if test="${time == f4}">selected</c:if>>
                                    ${time}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <!-- 検索ボタン -->
                    <div class="col-2 text-center">
                        <button type="submit" class="btn btn-secondary">検索</button>
                    </div>

                </div>
            </form>
            <!-- ▲ フィルタ部分 -->

            
        </section>
    </c:param>
</c:import>