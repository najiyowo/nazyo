<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>

$(function(){
	$("#id").blur(function(){
		checkid();
	});//id blur
	
	$("#pw").blur(function(){
		checkpw();
	});//pw blur
	
	$("#cpw").blur(function(){
		checkpcw();
	});//cpw blur
	
	$("#email").blur(function(){
		emailcheck();
	});//mail blur	
})//check blur

function submit(){
	if(check()){
		$.ajax({
			type:"post",
			url:"membertouroku.do",
			data:{
				"id":$("#id").val(),
				"pw":$("#pw").val(),
				"email":$("#email").val(),
				"phone":$("#phone").val()
			},
			success: function(data){
				if(data==1){
					alert("良い時間をお過ごしください");
					window.location.replace("login.do");
				}
			}
		});
	}
}//submit

function check(){
	if(!checkchk()){
		return false;
	}else if(!checkid()){ //false가 나와야 실행함
		return false;
	}else if(!checkpw()){
		return false;
	}else if(!checkpcw()){
		return false;
	}else if(!emailcheck()){
		return false;
	}else if(!checkemailpw()){
		return false;
	}
	
	return true;
}//check()

function checkid(){
	if($("#id").val()==""){
		message("id","IDは必須です","ff0000");
		return false;
	}else{
		$.ajax({
			type:"post",
			url:"checkid.do",
			data:{
				"id":$("#id").val()
				,"pw":$("#pw").val()
				},
			success: function(data){
				if(data==1){
					message("id","現在使われているIDです","ff0000");
				}else{
					message("id","使用可能です","00ff80");
				}
			}
		})
	}
	return true;
}//check id
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

function checkchk(){
	var controller1 = $("input[id='check1']").is(":checked");
	var controller2 = $("input[id='check2']").is(":checked");
	if(controller1==false||controller2==false){
		message("chk","必須同義が必要されます","ff0000");
		return false;
	}else{
		message("chk","","ff0000");
		return true;
	}
}
$(function(){
	$("input[name=chkall]").on("click",function (){
		$('.chk').prop('checked', this.checked );
	});
})
</script>