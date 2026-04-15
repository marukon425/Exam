<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="..//common/base.jsp">
	
	<c:param name="title">
		得点管理システム
	</c:param>
	
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
		<section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>
            <form style="margin : 20px ;" method="get">
				<div class="col-4">
					<label class="form-label" for="student-f1-select">入学年度</label>
                    <select style="width:300%" class="form-select" name="">
                        <option class="" value="0">--------</option>
                        <%-- <c:forEach var="year" items="${ent_year_set }">
                            <option value="${year}" <c:if test="${year==f1 }">selected</c:if>>${year }</option>
                        </c:forEach>--%>
                    </select>
				</div>      
				
				<div class="col-4">
                    <label class="form-label" name="">学生番号</label>
                    <input type="text" style="width:300%; padding: 7px 7px; border-radius: 5px;  border: 1px solid #ced4da;" placeholder="学生番号を入力してください" class="form-input" name="">
                </div>
                
                <div class="col-4">
                    <label class="form-label">氏名</label>
                    <input type="text" style="width:300%; padding: 7px 7px; border-radius: 5px;  border: 1px solid #ced4da;" placeholder="氏名を入力してください" class="form-input" name="">
                </div>  
                
                <div class="col-4">
                    <label class="form-label" for="student-f2-select">クラス</label>
                    <select style="width:300%"  class="form-select" name="">
                        <%--<c:forEach var="num" items="${class_num_set }">
                            <option value="${num }" <c:if test="${num==f2 }">selected</c:if>>${num }</option>
                        </c:forEach> --%>
                    </select>
                </div>
                
                <br><div name="end">
                    <button class="btn btn-secondary" id="filter-button">登録して終了</button>
                </div>
                
                <br><a href="student_list.jsp">戻る</a>
                
            </form>
        </section>
    </c:param>
</c:import>