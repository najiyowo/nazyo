<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>

<div class="wh100per posrel">
	<div class="background_gogi"></div>
	<div class="blackop posrel"></div>
	<%@include file="../header.jsp"%>
	<div class="fakemodal posrel">
		<a href=index.jsp><img src="images/biglogo.png" alt="ホームへ"></a>
		<div class="login">
			<form name="loginmember" method="post" action="main.do"
				class="loginmember" onsubmit="return check();">
				<h3>
					<label for="id">Login</label>
				</h3>
				<input type="text" name="id" id="id" placeholder="アイディー">
				<p id="idmsg" class="alertmsg"></p> 
				<h3>
					<label for="pw">Password</label>
				</h3>
				<input type="password" name="pw" id="pw" placeholder="暗証番号">
				<p id="pwmsg" class="alertmsg"></p> 
				
				<button type="submit" class="btn btn-default col-md-12 col-xs-12">ログイン</button>
				<input type="button" value="戻る" onClick="history.go(-1)"
					class="btn btn-default col-md-12 col-xs-12">
			</form>
		</div>
	</div>


</div>


<%@include file="../scripts.jsp"%>

<script>
function check(){
	var result=true;
	var id = $("#id").val();
	var pw = $("#pw").val();
	if(id==""){
		message("id","IDをお書きください","ff0000");
		return false;
	}else{
		message("id","","ff0000");
	}
	if(pw==""){
		message("pw","暗証番号をお書きください","ff0000");
		return false;
	}else{
		message("pw","","ff0000");
	}
	$.ajax({
		type:"post",
		url:"login.do",
		data:{
			id:$("#id").val(),
			pw:$("#pw").val()
			},
		async: false,
		success: function (data){
			//1 비번이 같음 -1 비번이 다름 0 아이디가 없음
			if(data==-1){//비번이 다름
				message("id","","ff0000");
				message("pw","暗証番号のご確認をお願いします","ff0000");
				result= false;
			}else if(data==0){//아이디가 없음
				message("id","IDのご確認をお願いします","ff0000");
				message("pw","","ff0000");
				result= false;
			}else{
				result=true;
			}
		}
	});
	return result;
}

</script>