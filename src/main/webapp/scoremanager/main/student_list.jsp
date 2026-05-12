<%-- 学生一覧JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/scoremanager/main/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>

    <c:param name="scripts"></c:param>

    <c:param name="content">
        <section class="me-4">
            <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生管理</h2>
            <div class="my-2 text-end px-4">
                <button class="button11" id="csv-download">csvをダウンロードする</button><a href="StudentCreate.action">新規登録</a>
            </div>
            <form method="get">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
                    <div class="col-4">
                        <label class="form-label" for="student-f1-select">入学年度</label>
                        <select class="form-select" id="student-f1-select" name="f1">
                            <option value="0">--------</option>
                            <c:forEach var="year" items="${ent_year_set }">
                                <%-- 現在のyearと選択されていたf1が一致していた場合selectedを追記 --%>
                                <option value="${year}" <c:if test="${year==f1 }">selected</c:if>>${year }</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-4">
                        <label class="form-label" for="student-f2-select">クラス</label>
                        <select class="form-select" id="student-f2-select" name="f2">
                            <option value="0">--------</option>
                            <c:forEach var="num" items="${class_num_set }">
                                <%-- 現在のnumと選択されていたf2が一致していた場合selectedを追記 --%>
                                <option value="${num }" <c:if test="${num==f2 }">selected</c:if>>${num }</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-2 form-check text-center">
                        <label class="form-check-label" for="student-f3-check">在学中
                            <%-- パラメーターf3が存在している場合checkedを追記 --%>
                            <input class="form-check-input" type="checkbox"
                                id="student-f3-check" name="f3" value="t"
                            <c:if test="${!empty f3 }">checked</c:if>>
                        </label>
                    </div>

                    <div class="col-2 text-center">
                        <button class="btn btn-secondary" id="filter-button">絞込み</button>
                    </div>
                    <div class="mt-2 text-warning">${errors.get("f1") }</div>
                </div>
            </form>

            <c:choose>
                <c:when test="${students.size()>0 }">
                    <div>検索結果：${students.size() }件</div>
                    <table class="table table-hover">
                        <tr>
                            <th>入学年度</th>
                            <th>学生番号</th>
                            <th>氏名</th>
                            <th>クラス</th>
                            <th class="text-center">在学中</th>
                            <th></th>
                            <th></th>
                        </tr>
                        <c:forEach var="student" items="${students }">
                            <tr>
                                <td class="stu-ent">${student.entYear }</td>
                                <td class="stu-no">${student.no }</td>
                                <td class="stu-name">${student.name }</td>
                                <td class="stu-class">${student.classNum }</td>
                                <td class="text-center stu-at">
                                    <%-- 在学フラグがたっている場合「○」それ以外は「×」を表示 --%>
                                    <c:choose>
                                        <c:when test="${student.isAttend() }">
                                            ○
                                        </c:when>
                                        <c:otherwise>
                                            ×
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><a href="StudentUpdate.action?no=${student.no }">変更</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <div>学生情報が存在しませんでした。</div>
                </c:otherwise>
            </c:choose>
        </section>
    <style>
    </style>
	<script>
	 function downloadCSV() {
		 //CSVデータ
		 const filename = "student_list.csv"
			 // テキストを抽出
		const stuentent = document.querySelectorAll(".stu-ent");
		const stuno     = document.querySelectorAll(".stu-no");
		const stuname   = document.querySelectorAll(".stu-name");
		const stuclass  = document.querySelectorAll(".stu-class");
		const stuatt    = document.querySelectorAll(".stu-at");
			 
             var students = [];
             for (let i = 0; i < stuentent.length; i++){
                 var studentls = [];
                 studentls.push(
                         	// 前後左右の空白を削除してプッシュ
                		    stuentent[i].textContent.trim(),
                		    stuno[i].textContent.trim(),
                		    stuname[i].textContent.trim(),
                		    stuclass[i].textContent.trim(),
                		    //空白・改行を全部除去
                		    stuatt[i].textContent.trim().replace(/\s+/g, "")
                		);
                 students.push(studentls)
                 }
	  	 const head = ["入学年度", "学生番号", "氏名", "クラス", "在学中"];
			 // csv用に成形
			 const content = [head, ...students]
		    .map(row => row.join(","))
		    .join("\n");
		 //BOMを付与
		 const bom = new Uint8Array([0xef, 0xbb, 0xbf])
		 
		 //BlobからオブジェクトURLを作成
		 const blob = new Blob([bom, content], { type: "text/csv" })

		 //リンク先にダウンロード用リンクを指定する
		 const link = document.createElement('a')
		 link.download = filename
		 link.href = URL.createObjectURL(blob)
		 link.click()

		 //createObjectURLで作成したオブジェクトURLを開放する
		 URL.revokeObjectURL(link.href)
		}

		//ボタンを取得する
		const downloadBtn = document.getElementById("csv-download");
		//ボタンがクリックされたら「downloadCSV」を実行する
		downloadBtn.addEventListener("click", downloadCSV, false);
   
    </script>
    </c:param>
</c:import>