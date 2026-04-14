<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="content">
	<section style="border: 1px solid #F0F1F2; display: flex; flex-direction: column; ">
	    <h2 style="background-color:#F0F1F2;">ログイン</h2>
	    <div style="display: flex; flex-direction: column; style="width:200px; margin:auto; align-items: center;	">
		    <input type="text" placeholder="ID" style="width:60%">
		    <input type="password" placeholder="パスワード" style="width:60%">
		    <label>パスワードを表示<input type="checkbox"></label>
		    <input type="submit" value="ログイン">	    
	    </div>
	</section>
</c:set>

<c:import url="../common/base.jsp">
    <c:param name="title" value="得点管理システム" />
    <c:param name="content" value="${content}" />
</c:import>