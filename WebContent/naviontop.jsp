<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<div>
				<a class="navbar-brand" href="main.do" style="padding: 0 15px;"><img
					src="images/logo.png" class="logo"></a>
			</div>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="osirase.do">お知らせ <span class="sr-only">(current)</span></a></li>
				<li><a href="gyarari.do">ギャラリー</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false">レシピ一覧
						<span class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="resipi.do">全体</a></li>
						<li class="divider"></li>
						<li><a href="#">果物</a></li>
						<li><a href="#">野菜</a></li>
						<li><a href="#">肉</a></li>
					</ul></li>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<c:if test="${admin==1}">
						<li><a href="admin.do">アドミン</a>
				</c:if>
				<c:choose>
					<c:when test="${empty userid}">
						<li><a href="login.do">ログイン</a></li>
						<li><a href="membertouroku.do">会員登録</a></li>
					</c:when>
					<c:when test="${not empty userid}">
						<li><a href="logout.do">ログアウト</a></li>
						<li><a href="memberupdate.do">情報修正</a></li>
					</c:when>
				</c:choose>
				
			</ul>
		</div>
	</div>
</nav>