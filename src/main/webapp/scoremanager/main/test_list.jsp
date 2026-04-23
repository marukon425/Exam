<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/scoremanager/main/common/base.jsp">
  <c:param name="title">得点管理システム</c:param>

  <c:param name="scripts"></c:param>

  <c:param name="content">
    <section class="me-4">
      <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>

      <!-- 全体枠 -->
      <div class="border rounded p-3 mb-3" id="grade-filter">

        <!-- 上段: 科目情報（ラベル左、コントロール右 横並び） -->
        <div class="row align-items-center mb-3">
          <div class="col-auto" style="min-width:100px;">
            <strong>科目情報</strong>
          </div>

          <div class="col">
            <!-- 科目検索フォーム（独立フォーム） -->
            <form method="get" action="TestListSubjectExecute.action" role="search" aria-label="科目検索" class="d-flex flex-wrap align-items-end gap-2">
              <input type="hidden" name="type" value="subject" />

              <div class="d-flex flex-column">
                <label class="form-label mb-1" for="f1">入学年度</label>
                <select id="f1" name="f1" class="form-select form-select-sm" style="min-width:140px;">
                  <option value="">--------</option>
                  <c:forEach var="year" items="${ent_year_set}">
                    <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                  </c:forEach>
                </select>
              </div>

              <div class="d-flex flex-column">
                <label class="form-label mb-1" for="f2">クラス</label>
                <select id="f2" name="f2" class="form-select form-select-sm" style="min-width:120px;">
                  <option value="">--------</option>
                  <c:forEach var="classNum" items="${class_num_set}">
                    <option value="${classNum}" <c:if test="${classNum == f2}">selected</c:if>>${classNum}</option>
                  </c:forEach>
                </select>
              </div>

              <div class="d-flex flex-column">
                <label class="form-label mb-1" for="subject">科目</label>
                <select id="f3" name="f3" class="form-select form-select-sm" style="min-width:260px;">
                  <option value="">--------</option>
                  <c:forEach var="subject" items="${subjects}">
                    <option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>${subject.name}</option>
                  </c:forEach>
                </select>
              </div>

              <!-- 右端に寄せたい場合は flex-grow のダミーを入れる -->
              <!-- <div class="flex-grow-1"></div> -->

              <div class="d-flex align-items-end" >
                <button class="btn btn-secondary" id="filter-button" style="margin-left:20px;">検索</button>
              </div>
            </form>
          </div>
        </div>
        
        <hr>

        <!-- 下段: 学生情報（ラベル左、入力＋ボタン右 横並び） -->
        <div class="row align-items-center">
          <div class="col-auto" style="min-width:100px;">
            <strong>学生情報</strong>
          </div>

          <div class="col">
            <!-- 学生番号検索フォーム（独立フォーム） -->
            <form method="get" action="TestListStudentExecute.action" role="search" aria-label="学生番号検索" class="d-flex flex-wrap align-items-end gap-2">
              <input type="hidden" name="f4" value="student" />

              <div class="d-flex flex-column">
                <label class="form-label mb-1" for="studentId">学生番号</label>
                <input id="studentId" name="studentId" type="text" class="form-control form-control-sm" placeholder="学生番号を入力してください" value="${fn:escapeXml(param.studentId)}" style="min-width:220px;" />
              </div>

              <div class="d-flex align-items-end">
                <button class="btn btn-secondary" id="filter-button" style="margin-left: 30px;">検索</button>
              </div>
            </form>
          </div>
        </div>
        
    </section>
    <p style="color: #00B0F0;">科目情報または学生情報を入力して検索ボタンをクリックしてください</p>
  </c:param>
</c:import>
