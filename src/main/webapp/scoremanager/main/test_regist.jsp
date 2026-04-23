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
            <form action="TestRegist.action" method="get">
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

            <c:choose>
                <c:when test="${tests.size() > 0}">

                    <!-- 科目ラベル -->
                    <div class="ms-3 mb-2">
                        <c:forEach var="subject" items="${subjects}">
                            <c:if test="${subject.cd == f3}">
                                科目：${subject.name}（${f4}回）
                            </c:if>
                        </c:forEach>
                    </div>

                    <!-- ▼ 成績一覧テーブル -->
                    <form method="post" action="TestRegistExecute.action">

                        <!-- ★ forEach の外に hidden を移動 ★ -->
                        <input type="hidden" name="count" value="${f4}">
                        <input type="hidden" name="subject" value="${f3}">

                        <table class="table mx-3">
                            <thead class="table-light border-bottom">
                                <tr>
                                    <th>入学年度</th>
                                    <th>クラス</th>
                                    <th>学生番号</th>
                                    <th>氏名</th>
                                    <th>点数</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="test" items="${tests}">
                                    <tr class="border-bottom">
                                        <td>${test.entYear}</td>
                                        <td>${test.classNum}</td>
                                        <td>${test.studentNo}</td>
                                        <td>${test.studentName}</td>
                                        <td>
                                            <!-- ★ 学生番号で一意にする ★ -->
                                            <input type="text"
                                                   class="form-control"
                                                   name="point_${test.studentNo}"
                                                   value="${test.point}">
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                        <!-- 登録して終了ボタン -->
                        <div class="my-3">
                            <button type="submit" class="btn btn-secondary">登録して終了</button>
                        </div>

                    </form>
                    <!-- ▲ 成績一覧テーブル -->

                </c:when>
                <c:otherwise>
                </c:otherwise>
            </c:choose>

        </section>
    </c:param>
</c:import>
