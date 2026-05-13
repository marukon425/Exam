<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
 
<c:import url="/scoremanager/main/common/base.jsp">
    <c:param name="title">得点管理システム</c:param>
    <c:param name="scripts"></c:param>
 
    <c:param name="content">
        <section class="me-4">
 
            <!-- 見出し -->
             <h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
                <button id="csv-download" style="">csvをダウンロード</button>
 
            <!-- ▼ フィルタ部分 -->
            <form action="TestRegist.action" method="get">
                <div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
 
                    <!-- 入学年度 -->
                    <div class="col-2">
                        <label class="form-label">入学年度</label>
                        <select class="form-select" name="f1" style="width:120px;">
                            <option value="">--------</option>
                            <c:forEach var="year" items="${ent_year_set}">
                                <option value="${year}" <c:if test="${year == f1}">selected</c:if>>${year}</option>
                            </c:forEach>
                        </select>
                    </div>
 
                    <!-- クラス -->
                    <div class="col-2">
                        <label class="form-label">クラス</label>
                        <select class="form-select" name="f2" style="width:120px;">
                            <option value="">--------</option>
                            <c:forEach var="classNum" items="${class_num_set}">
                                <option value="${classNum}" <c:if test="${classNum == f2}">selected</c:if>>${classNum}</option>
                            </c:forEach>
                        </select>
                    </div>
 
                    <!-- 科目 -->
                    <div class="col-4">
                        <label  class="form-label">科目</label>
                        <select id="subject" class="form-select" name="f3" style="width:250px;">
                            <option value="">--------</option>
                            <c:forEach var="subject" items="${subjects}">
                                <option value="${subject.cd}" <c:if test="${subject.cd == f3}">selected</c:if>>${subject.name}</option>
                            </c:forEach>
                        </select>
                    </div>
 
                    <!-- 回数 -->
                    <div class="col-2">
                        <label class="form-label">回数</label>
                        <select class="form-select" name="f4" style="width:120px;">
                            <option value="">--------</option>
                            <c:forEach var="time" items="${times}">
                                <option value="${time}" <c:if test="${time == f4}">selected</c:if>>${time}</option>
                            </c:forEach>
                        </select>
                    </div>
 
                    <!-- 検索ボタン -->
                    <div class="col-2 text-center">
                        <button type="submit" class="btn btn-secondary">検索</button>
                    </div>
 					<c:if test="${not empty filter_error}">
					    <div class="small" style="color: #ff8c00">
					        ${filter_error}
					    </div>
					</c:if>
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
                        <input type="hidden" name="f1" value="${f1}">
					    <input type="hidden" name="f2" value="${f2}">
					    <input type="hidden" name="subject" value="${f3}">
					    <input type="hidden" name="count" value="${f4}">
                        
 
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
                                        <td class="stu-ent">${test.entYear}</td>
                                        <td class="stu-class">${test.classNum}</td>
                                        <td class="stu-no">${test.studentNo}</td>
                                        <td class="stu-name">${test.studentName}</td>
                                        <td class="stu-point">
                                            <!-- ★ 学生番号で一意にする ★ -->
                                            <input type="text"
                                                   class="form-control"
                                                   name="point_${test.studentNo}"
                                                   value="${test.point == -1 ? '' :test.point}">
                                             <c:forEach var="errNo" items="${errorStudentNos}">
										        <c:if test="${errNo == test.studentNo}">
										            <div class="small" style="color: #ff8c00;">
										                0〜100の範囲で入力してください
										            </div>
										        </c:if>
										    </c:forEach>      
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
	<script>
	 function downloadCSV() {
		 const subject = document.getElementById("id").value;
		 //CSVデータ
		 const filename = `${subject}_test_list.csv`
			 // テキストを抽出
		const stuentent = document.querySelectorAll(".stu-ent");
		const stuno     = document.querySelectorAll(".stu-no");
		const stuname   = document.querySelectorAll(".stu-name");
		const stuclass  = document.querySelectorAll(".stu-class");
		const stuatt    = document.querySelectorAll(".stu-point input");
			 
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
                		    stuatt[i].value
                		);
                 students.push(studentls)
                 }
	  	 const head = ["入学年度", "クラス", "氏名", "学生番号", "点数"];
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
 
 
