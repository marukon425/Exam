<%-- ログイン画面JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:import url="/scoremanager/main/common/base.jsp">
    <c:param name="title">
        得点管理システム
    </c:param>
 
    <c:param name="scripts">
	<script>
	
	</script>
    </c:param>
    <c:param name="styles">
    </c:param>
 
    <c:param name="content">
		<form class="login-form" action="LoginExecute.action" method="post">
			<h2 style="text-align: center;">ログイン</h2>
			<p>${notlogin}</p>
			<div style="position: relative; margin:0 auto; width:100%; display: flex; justify-content: center;">
			<input name="id" id="login-id" type="text" placeholder="半角でご入力ください" value="admin1" required maxlength="10" pattern="^[a-zA-Z0-9]+$" title="半角英数字で入力してください">
			<!-- <input name="id" id="login-id" type="text" placeholder="半角でご入力ください" value="${id}" required maxlength="10" pattern="^[a-zA-Z0-9]+$" title="半角英数字で入力してください">-->
			</div>
			<div style="position: relative; margin:0 auto; width:100%; display: flex; justify-content: center;">
			<input name="password" id="login-password" type="password" value="password" placeholder="30文字以内の半角英数字でご入力ください" required maxlength="30" pattern="^[a-zA-Z0-9]+$" title="半角英数字で入力してください">
			</div>			
			<div class="password-show"><input id="preview" name="chk_d_ps" type="checkbox">パスワードの表示</div>
			<input name="login" type="submit" value="ログイン">
			
		</form>
		<style>eg
			.login-form{
			border: 1px solid #EEEEEE;
			display: flex;
			flex-direction: column;
			justify-content: center;
			width: 700px;
			margin: 0 auto;
			}
			.login-form h2{
			background-color: #EEEEEE;
			padding: 10px 0
			}
			.login-form input[type="text"], .login-form input[type="password"]{
		    height: 70px;
			width: 80%;
			margin:10px auto;
			border: 1px solid #CCCCCC;
			border-radius: 5px;
			}
			.login-form input[type="submit"]{
			width: 150px;
			margin:10px auto;
			padding: 10px 30px;
		    background-color: #3380ff;
		    color: white;
		    border: none;
		    border-radius: 10px;
		    display: flex;
    		justify-content: center;			}
			.password-show{
			margin: 10px auto;
		    display: flex;
    		justify-content: center;
			}
		</style>
		
	<script>
      document.addEventListener('DOMContentLoaded', function (event) {
            const input = document.getElementById("login-password");
            const check = document.getElementById("preview");
     
            check.addEventListener('change', function () {
                console.log("触った");
                if (check.checked) {
                    input.type = 'text';
                } else {
                    input.type = 'password';
                }
            });
        });    
    </script>
 
 
    </c:param>
</c:import>
 
