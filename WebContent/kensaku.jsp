<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="col-md-12">
	<div class="col-md-7"></div>
	<div class="col-md-5">
		<form name="myform" method="get" action="osirase.do">
			<select name="type" class="select">
				<option value="title">件名</option>
				<option value="content">内容</option>
				<option value="writer">作者</option>
			</select> <input type="text" name="word" class="search_word">
			<button class="btn_search" type="submit" style = "border:solid 1px #000; padding:3px;">検索
			</button>
		</form>
	</div>
</div>