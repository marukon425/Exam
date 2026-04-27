<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:import url="/scoremanager/main/common/base.jsp">
  <c:param name="title">得点管理システム</c:param>
  <c:param name="scripts"></c:param>

  <c:param name="content">
    <section class="me-4">
      <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
        成績一覧（科目）
      </h2>
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
				                	<option value="" disabled selected>--------</option>
				                	<c:forEach var="year" items="${ent_year_set}">
				                  		<option value="${year}" <c:if test="${param.f1 == year}">selected</c:if>>${year}</option>
				                	</c:forEach>
				              	</select>
				            </div>
			
			            	<div class="d-flex flex-column">
			              		<label class="form-label mb-1" for="f2">クラス</label>
			              		<select id="f2" name="f2" class="form-select form-select-sm" style="min-width:120px;">
			                		<option value="">--------</option>
			                		<c:forEach var="num" items="${class_num_set}">
			                  			<option value="${num}" <c:if test="${param.f2 == num}">selected</c:if>>${num}</option>
			                		</c:forEach>
			              		</select>
			            	</div>
			
			            	<div class="d-flex flex-column">
			              		<label class="form-label mb-1" for="subject">科目</label>
			              		<select id="subject" name="f3" class="form-select form-select-sm" style="min-width:260px;">
			                		<option value="">--------</option>
			                		<c:forEach var="subject" items="${subjects}">
			                  			<option value="${subject.cd}" <c:if test="${subject.cd == f3 }">selected</c:if>>${subject.name }</option>
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
			            	<input type="hidden" name="type" value="student" />
			
			            	<div class="d-flex flex-column">
			              		<label class="form-label mb-1" for="studentId">学生番号</label>
			              		<input id="studentId" name="f4" type="text" class="form-control form-control-sm" placeholder="学生番号を入力してください" value="${fn:escapeXml(f4)}" style="min-width:220px;" />
			            	</div>
			
			            	<div class="d-flex align-items-end">
			              		<button class="btn btn-secondary" id="filter-button" style="margin-left: 30px;">検索</button>
			            	</div>
			          	</form>
			       </div>
			   </div>
		   </div>
 
    </section>

    <!-- 科目表示 -->
    <div class="ms-3 mb-2">
      <label>科目：${subject.name}</label>
    </div>

    <style>
  table {
    border-collapse: collapse;
  }

  table th,
  table td {
    border-bottom: 1px solid #dee2e6;
    padding: 8px 12px;
    vertical-align: middle;
  }

  table th {
    font-weight: 600;
  }


</style>
<c:choose>
	<c:when test="${tests.size() > 0}">
 
    <!--成績一覧テーブ -->
    <table style="width: 100%;">
      <tr>
        <th>入学年度</th>
        <th>クラス</th>
        <th>学生番号</th>
        <th>氏名</th>
        <th>1回</th>
        <th>2回</th>
      </tr>


      <c:forEach var="test" items="${tests}">
        <tr>
          <td>${test.entYear}</td>
          <td>${test.classNum}</td>
          <td>${test.studentNo}</td>
          <td>${test.studentName}</td>

          <!-- 1回目 -->
          <td>
            <c:choose>
              <c:when test="${test.points['1'] != null}">
  ${test.points['1']}
</c:when>
              <c:otherwise>-</c:otherwise>
            </c:choose>
          </td>

          <!-- 2回目 -->
          <td>
            <c:choose>
              <c:when test="${test.points['2'] != null}">
  ${test.points['2']}
</c:when>
              <c:otherwise>-</c:otherwise>
            </c:choose>
          </td>
        </tr>
      </c:forEach>
    </table>

	</c:when>
    <c:otherwise>
        <div>成績情報が存在しませんでした。</div>
    </c:otherwise>
</c:choose>
 

  </c:param>
</c:import>