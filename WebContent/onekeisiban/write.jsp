<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<%@include file="../naviontop.jsp"%>
<div class="col-md-12">
	<div class="row">
		<div class="col-md-9" style="float: none; margin: 0 auto;">
			<h2 style="text-align: center;">${donopage}</h2>
			<div class="col-md-12">
				<div class="row">
					<form name="write" method="post" action="${doko}.writepro"
						id="write" 
						onsubmit="return writecheck()" class="write">
						<table class="col-md-12 col-xs-12 write">
						<tr>
							<td>
								<input type="text" id="title" name="title" placeholder="件名" class="col-md-12 col-xs-12">
								<p id="titlemsg" class="alertmsg"></p> 
							</td>
						</tr>
						<tr>
							<td>
								<c:if test="${admin==1}">
								<input type="text" id="writer" name="writer" value="管理者" class="col-md-12 col-xs-12" readonly>
								</c:if>
								<c:if test="${admin!=1}">
								<input type="text" id="writer" name="writer" value="${userid}" class="col-md-12 col-xs-12" readonly>
								</c:if>
							</td>
						</tr>
						<tr>
							<td style="text-align: left">
							<textarea name="content" id="summernote" class="summernote" placeholder="本文"></textarea>
							</td>
						</tr>
						<tr>
						<td>
							<div class="flex">
								<button type="submit" class="btn btn-default col-xs-3 col-md-3 samerow">作成</button>
								<button onclick="goback()" class="btn btn-default col-xs-3 col-md-3 samerow" >戻る</button>
							</div>
						</td>
						</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="../scripts.jsp"%>
<script>
function goback(){
	window.history.back();
}

function writecheck(){
	if(!checktitle()){
		return false;
	}else if(!checkcontent()){
		return false;
	}
	return true;
}
function checktitle(){
	var title=$("#title").val();
	console.log(title);
	if(title==""){
		message("title","タイトルを書いてください","ff0000");
		return false;
	}else{
		message("title","","ff0000");
		return true;
	}
}

function checkcontent(){
	var content=$("#summernote").val();
	console.log(content);
	if(content==""){
		alert("内容を書いてください");
		return false;
	}else{
		return true;
	}
}
</script>
<%@include file="../footer.jsp"%>