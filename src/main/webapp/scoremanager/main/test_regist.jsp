<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/scoremanager/main/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>

    <c:param name="scripts">
        <style>
            /* 
            #filter .form-label {
                margin-bottom: 4px;
            }
            #filter select {
                height: 38px;
            }
            #filter .col-2,
            #filter .col-3,
            #filter .col-4 {
                display: flex;
                flex-direction: column;
                justify-content: flex-end;
            }
        </style>
    </c:param>

    <c:param name="content">

        <section class="me-4">

            <!-- 見出し -->
            <h2 class="h3 mb-3 fw-normal bg-secondary bg-opacity-10 py-2 px-4">
                成績管理
            </h2>

            <!-- ▼ フィルタ部分 -->
            <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">

                <div class="col-2">
                    <label class="form-label">入学年度</label>
                    <select class="form-select" style="width:120px;">
                        <option>2023</option>
                    </select>
                </div>

                <div class="col-2">
                    <label class="form-label">クラス</label>
                    <select class="form-select" style="width:120px;">
                        <option>131</option>
                    </select>
                </div>

                <div class="col-4">
                    <label class="form-label">科目</label>
                    <select class="form-select" style="width:250px;">
                        <option>Python1</option>
                    </select>
                </div>

                <div class="col-2">
                    <label class="form-label">回数</label>
                    <select class="form-select" style="width:120px;">
                        <option>1</option>
                    </select>
                </div>

                <div class="col-2 text-center">
                    <button class="btn btn-secondary">検索</button>
                </div>

            </div>
            <!-- ▲ フィルタ部分 -->

            <!-- 科目名 -->
            <div class="ms-3 mb-2 fw-bold">
                科目：Python1（1回）
            </div>

            <!-- ▼ 成績一覧テーブル -->
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
        <tr class="border-bottom">
            <td>2023</td>
            <td>131</td>
            <td>2325001</td>
            <td>大原一郎</td>
            <td><input type="text" class="form-control" value="65"></td>
        </tr>
        <tr class="border-bottom">
            <td>2023</td>
            <td>131</td>
            <td>2325002</td>
            <td>石川瘴気</td>
            <td><input type="text" class="form-control" value="2"></td>
        </tr>
        <tr class="border-bottom">
            <td>2023</td>
            <td>131</td>
            <td>2325003</td>
            <td>大原三郎</td>
            <td><input type="text" class="form-control" value="85"></td>
        </tr>
    </tbody>
</table>
				
				

            <!-- 登録ボタン -->
          		<div class="my-3">
    <button class="btn"
        style="
            background-color: #6c757d;
            color: white;
            border: none;
            border-radius: 6px;
            padding: 6px 20px;
        ">
        登録して終了
    </button>
</div>
          		
          		

           	
        </section>

    </c:param>
</c:import>
