<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<%@include file="../naviontop.jsp"%>

<div class="col-xs-12 col-md-12">
	<div class="row">
		<div class="col-xs-12 col-md-4" style="float: none; margin: 0 auto;">
			<h2 style="text-align: center;">情報修正</h2>
			<div class="col-xs-12 col-md-12">
				<div class="row">
					<div class="member">
						
						<input type="text" name="id" id="id" 
						value="${mvo.id}" class="col-xs-12 col-md-12" readonly>
						
						<p id="idmsg" class="alertmsg"></p> 
						
						<input type="password" name="pw" id="pw" 
						placeholder="暗証番号 " class="col-xs-12 col-md-12">
						
						<p id="pwmsg" class="alertmsg"></p> 
						
						<input type="password" name="cpw" id="cpw" 
						placeholder="暗証番号のご確認" 	class="col-xs-12 col-md-12"> 
						
						<p id="cpwmsg" class="alertmsg"></p>
						
						<input type="email"	name="email" id="email" 
						value="${mvo.email}" class="col-xs-12 col-md-12">
						
						<p id="emailmsg" class="alertmsg"></p> 
						<div id= "checkingmail" style="display:none;">
							<button id="checkemail" class="btn btn-default col-xs-12 col-md-12" 
							style="margin-top: 5px;" onclick="emailpassword()">認証番号の要請</button>
							
							<input type="text" name="checkemailpw" id="checkemailpw" 
							placeholder="メールを確認してください" class="col-xs-8 col-md-8">
							<input type="hidden" id="maemail" value="${mvo.email}">
							
							<button id="chkmail" class="btn btn-default col-xs-4 col-md-4" 
							onclick="checkemailpw()" style="margin-top: 5px;">確認</button>
							
							<p id="kakuninmsg" class="alertmsg"></p> 
						</div>
						<input type="text"  oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
						name="phone" id="phone" value="${mvo.phone}"placeholder="携帯番号(選択)番号だけ入力お願いします" class="col-xs-12 col-md-12">
						
						<button id="submit" class="btn btn-default col-xs-6 col-md-6" 
						style="margin-top: 5px;" onclick="submit()">登録</button>
						
						<input type="button" value="戻る" onClick="history.go(-1)" 
						class="btn btn-default col-md-6 col-xs-6">
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-4"></div>
	</div>
</div>
<%@include file="../scripts.jsp"%>

<script>
$(function(){
	console.log($("#maemail").val());
	$("#pw").blur(function(){
		if($("#pw").val()!=""){
			if(!checkpw()){
				return false;
			}
			$("#cpw").focus();
		}
	});//pw not null
	
	$("#cpw").blur(function(){
		if($("#pw").val()!=""){
			checkpcw();
		}
	});//cpw blur
	
	$("#email").blur(function(){
		if($("#email").val()!=$("#maemail").val()){
			$("#checkingmail").css({"display":"block"});
			if(!checkemailpw()){
				return false;
			}
		emailcheck();
		}else{
			$("#checkingmail").css({"display":"none"});
		}
	});//mail blur	
})//check blur

function submit(){
	if(check()){
		$.ajax({
			type:"post",
			url:"memberupdate.do",
			data:{
				"id":$("#id").val(),
				"pw":$("#pw").val(),
				"email":$("#email").val(),
				"phone":$("#phone").val()
			},
			success: function(data){
				console.log(data);
				if(data==1){
					alert("修正完了しました");
					window.location.replace("main.do");
				}
			}
		});
	}
}//submit

function check(){
	if($("#pw").val()!=""){
		if(!checkpw()){
			return false;
		}
		if(!checkpcw()){
			return false;
		}
	}else if($("#email").val()!=$("#maemail").val()){
		$("#checkingmail").css({"display":"block"});
		if(!checkemailpw()){
			return false;
			}
	}
	return true;
}//check()
function checkpw(){
	if($("#pw").val()==""){
		message("pw","パスワードは必須です","ff0000");
		return false;
	}else{
		var checkpw = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
		
		if(!checkpw.test($("#pw").val())){
			message("pw","最低８文字、一つの数字と特集文字が必要です","ff0000");
			return false;
		}else{
			message("pw","使用可能です","00ff80");
			return true;
		}
	}
	return true;
}//check pw

function checkpcw(){
	if($("#cpw").val()!=""){
		if($("#cpw").val()!=$("#pw").val()){
			$("#cpwmsg").css({"color": "#ff0000"});
			$("#cpwmsg").html("暗証番号と同じくお書きください");
			return false;
		}else{
			$("#cpwmsg").css({"color": "#00ff80"});
			$("#cpwmsg").html("確認完了です");
			return true;
		}
	}else{
		$("#cpwmsg").css({"color": "#ff0000"});
		$("#cpwmsg").html("暗証番号と同じくお書きください");
		return false;
	}
	return true;
}//cpw check

function emailcheck(){
	if($("#email").val()==""){
		message("email","メールは必須です","ff0000");
		return false;
	}else{
		var regEmail=/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
		
		if(!regEmail.test($("#email").val())){
			message("email","メールのアドレスをご確認ください","ff0000");
			return false;
		}else{
			message("email","確認番号を要請してください","00ff80");
			return true;
		}
	}
	return true;
}//emailcheck

function emailpassword(){
	if(emailcheck()){
		$.ajax({
			type:"post",
			url:"sendemail.do",
			data:{"email":$("#email").val()},
			dataType:"json", //서버에서 보내주는 데이터 형식
			success:function(data) {
				$("#emailmsg").css({"color": "#00ff80"});
				$("#emailmsg").html(data.msg);
			},error:function() {
				alert("email error");
			}
		})
	}
}
function checkemailpw(){
	var check = false;
	var checkemailpw = $("#checkemailpw").val();
	if(checkemailpw==""){
		$("#kakuninmsg").css({"color": "#ff0000"});
		$("#kakuninmsg").html("メールをご確認ください");
		$("#checkemailpw").focus();
	}else {
		check = mailpwcheck();
	}
	return check;
}

function mailpwcheck(){
	var check = false;
	var checkemailpw = $("#checkemailpw").val();
	$.ajax({
		type:"post",
		url:"checkemail.do",
		data:{"checkemailpw":checkemailpw},
		dataType:"json", //서버에서 처리된 결과를 json형식으로 받겠다
		async:false,
		success:function(data) {
			if(data.check == "ok") {
				$("#kakuninmsg").css({"color": "#00ff80"});
				$("#kakuninmsg").html(data.msg);
				check= true;
			}else {
				$("#kakuninmsg").css({"color": "#ff0000"});
				$("#kakuninmsg").html(data.msg);
				check=false;
			}
		},error:function() {
			alert("통신에러");
		}
	})
	return check;
}
</script>
<%@include file="../footer.jsp"%>