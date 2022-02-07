<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<%@include file="../naviontop.jsp"%>

<div class="col-xs-12 col-md-12">
	<div class="row">
		<div class="col-xs-12 col-md-4" style="float: none; margin: 0 auto;">
			<h2 style="text-align: center;">会員登録</h2>
			<div class="col-xs-12 col-md-12">
				<div class="row">
					<div class="member">
						<div>
							<div class="flex">
								<div class="samerow">
									<input type="checkbox" class="chk" name="chkall" id="chkall">
									<label for="chkall" class="chk">全体同義</label>
								</div><div class="samerow">
									<input type="checkbox" name="chk" class="chk" id="check1" value="checking1">
									<label for="check1" class="chk">同意します　必須</label>
								</div>
							</div>
							<div class="flex">
								<div class="samerow">
									<input type="checkbox" name="chk"  class="chk" id="check2" value="checking2">
									<label for="check2" class="chk">同意します　必須</label>
								</div><div class="samerow">
									<input type="checkbox" name="chk"  class="chk" id="check3">
									<label for="check3" class="chk">同意します　選択</label>
								</div>
							</div>
							<p id="chkmsg" class="alertmsg">
						</div>
						<input type="text" name="id" id="id" 
						placeholder="アイディー" class="col-xs-12 col-md-12">
						
						<p id="idmsg" class="alertmsg"></p> 
						
						<input type="password" name="pw" id="pw" 
						placeholder="暗証番号 " class="col-xs-12 col-md-12">
						
						<p id="pwmsg" class="alertmsg"></p> 
						
						<input type="password" name="cpw" id="cpw" 
						placeholder="暗証番号のご確認" 	class="col-xs-12 col-md-12"> 
						
						<p id="cpwmsg" class="alertmsg"></p>
						
						<input type="email"	name="email" id="email" 
						placeholder="メール" class="col-xs-12 col-md-12">
						
						<p id="emailmsg" class="alertmsg"></p> 
						
						<button id="checkemail" class="btn btn-default col-xs-12 col-md-12" 
						style="margin-top: 5px;" onclick="emailpassword()">認証番号の要請</button>
						
						<input type="text" name="checkemailpw" id="checkemailpw" 
						placeholder="メールを確認してください" class="col-xs-8 col-md-8">
						
						<button id="chkmail" class="btn btn-default col-xs-4 col-md-4" 
						onclick="checkemailpw()" style="margin-top: 5px;">確認</button>
						
						<p id="kakuninmsg" class="alertmsg"></p> 
						
						<input type="text"  oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');"
						name="phone" id="phone" placeholder="携帯番号(選択)番号だけ入力お願いします" class="col-xs-12 col-md-12">
						
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
<%@include file="memberscript.jsp" %>
<%@include file="../footer.jsp"%>