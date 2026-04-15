<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="..//common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts">
<style>
    #filter .col-2,
    #filter .col-3,
    #filter .col-4 {
        display: flex;
        flex-direction: column;
        justify-content: flex-end;
    }

    #filter.row {
        --bs-gutter-x: 0.5rem !important;
    }
</style>
    </c:param>
    
    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
            
            <form method="get">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">

                    <div class="col-2">
                        <label class="form-label" for="student-f1-select">入学年度</label>
                        <select class="form-select" id="student-f1-select" name="f1" style="width:120px;">
                            <option value="0">--------</option>
                            <c:forEach var="year" items="">
                                <option value="${year}">${year}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-2">
                        <label class="form-label" for="student-f2-select">クラス</label>
                        <select class="form-select" id="student-f2-select" name="f2" style="width:120px;">
                            <option value="0">--------</option>
                            <c:forEach var="num" items="">
                                <option value="${num}">${num}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-4">
                        <label class="form-label" for="student-f3-select">科目</label>
                        <select class="form-select" id="student-f3-select" name="f3" style="width:250px;">
                            <option value="0">--------</option>
                            <c:forEach var="sub" items="">
                                <option value="${sub.id}">${sub.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-2">
                        <label class="form-label" for="student-f4-select">回数</label>
                        <select class="form-select" id="student-f4-select" name="f4" style="width:120px;">
                            <option value="0">--------</option>
                            <c:forEach var="cnt" items="">
                                <option value="${cnt}">${cnt}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-2 text-center">
                        <button class="btn btn-secondary" id="filter-button">検索</button>
                    </div>

                    <div class="mt-2 text-warning"></div>

                </div>
            </form>

        </section>
    </c:param>
</c:import>