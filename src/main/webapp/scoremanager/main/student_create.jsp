<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/scoremanager/main/common/base.jsp">
	
	<c:param name="title">
		得点管理システム
	</c:param>
	
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
		<section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>
            <form style="margin : 20px ;" method="get" action="StudentCreateExecute.action">
				<div class="col-4">
                    <label class="form-label" for="student-f1-select">入学年度</label>
	              	<select name="ent_year" class="form-select" style="width:300%;">
	                	<option value="0">--------</option>
	                	<c:forEach var="year" items="${ent_year_set}">
	                  		<option value="${year}" <c:if test="${year == ent_year}" >selected</c:if>>${year}</option>
	                	</c:forEach>
	              	</select>
                    
                    <p style="border:none;color:#FFCC00;">${notent}</p>
				</div>      
				
				<div class="col-4">
                    <label class="form-label" name="">学生番号</label>
                    <input type="text" style="width:300%; padding: 7px 7px; border-radius: 5px;  border: 1px solid #ced4da;" placeholder="学生番号を入力してください" class="form-input" name="no" value="${no }" required>
                    <div style="color: #FFCC00;">${notaddd}</div>
                </div>
                
                <div class="col-4">
                    <label class="form-label">氏名</label>
                    <input type="text" style="width:300%; padding: 7px 7px; border-radius: 5px;  border: 1px solid #ced4da;" placeholder="氏名を入力してください" class="form-input" name="name" value="${name }" required>
                </div>  
                
                <div class="col-4">
                    <label class="form-label" for="student-f2-select">クラス</label>
                    <select style="width:300%"  class="form-select" name="class_num">
                    <option class="" value="0">--------</option>
                        <c:forEach var="num" items="${class_num_set }">
                            <option value="${num }"<c:if test="${num == class_num}" >selected</c:if>>${num }</option>
                        </c:forEach> 
                    </select>
                    
                </div>
                
                <br><div name="end">
                    <button class="btn btn-secondary" id="filter-button">登録して終了</button>
                </div>
                
                <br><a href="StudentList.action">戻る</a>
                
            </form>
        </section>
    </c:param>
</c:import>