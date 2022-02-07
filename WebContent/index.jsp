<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="header.jsp"%>
<div class="wh100per">
	<div class="background_gogi"></div>

<%@include file="naviontop.jsp" %>

	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12 subtitle">
			<p>何か食べようか</p>
			<p>誰でもできるレシピ</p>
		</div>
	</div>
	<div class="indexbottom">
		<img src="images/spoon.png">
		<p>誰でもできるレシピ.</p>
	</div>
</div>
<div class="col-sm-12 col-md-12">
	<div class="row">
		<div class="col-sm-4 col-md-2"></div>
		<div role="tabpanel">
			<div class="col-sm-">
				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#osirase"
						aria-controls="home" role="tab" data-toggle="tab">お知らせ</a></li>
					<li role="presentation"><a href="#gyarari"
						aria-controls="profile" role="tab" data-toggle="tab">ギャラリー</a></li>
					<li role="presentation"><a href="#resipi"
						aria-controls="messages" role="tab" data-toggle="tab">レシピ</a></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- Tab panes -->
	<div class="col-sm-12 col-md-12">
		<div class="row">
			<div class="tab-content">
				<div role="tabpanel" class="tab-pane active" id="osirase">
					<ul>
						<li class="col-xs-6 col-sm-6 col-md-2 tabtitle"><p>お知らせ</p></li>
						<c:forEach var="osirase" items="${losirase}">
						<li class="col-xs-6 col-sm-6 col-md-3">
							<a href="osirase.read?bno=${osirase.bno}" style="cursor: pointer; color:#333;">
								<h2 style="white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${osirase.title}</h2>
								<p>${osirase.writer }</p>
								<p><fmt:parseDate var="date" value="${osirase.wrdate}"
								pattern="yyyy-mm-dd"/>
								<fmt:formatDate value="${date}" pattern="yy.mm.dd"/>
								</p>
							</a>
						</li>
						</c:forEach>
					</ul>
				</div>
				<div role="tabpanel" class="tab-pane" id="gyarari">
					<ul>
						<li class="col-xs-6 col-sm-6 col-md-2 tabtitle"><p>ギャラリー</p></li>
						<c:forEach var="gyarari" items="${lgyarari}">
						<li class="col-xs-6 col-sm-6 col-md-3">
							<a href="gyarari.read?bno=${gyarari.bno}" style="cursor: pointer; color:#333;">
							<h2>${gyarari.title}</h2>
							<p>${gyarari.writer }</p>
							<p><fmt:parseDate var="date" value="${gyarari.wrdate}"
							pattern="yyyy-mm-dd"/>
							<fmt:formatDate value="${date}" pattern="yy.mm.dd"/>
							</p>
							</a>
						</li>
						</c:forEach>
					</ul>
				</div>
				<div role="tabpanel" class="tab-pane" id="resipi">
					<ul>
						<li class="col-xs-6 col-sm-6 col-md-2 tabtitle"><p>レシピ</p></li>
						<c:forEach var="resipi" items="${lresipi}">
						<li class="col-xs-6 col-sm-6 col-md-3"><h2>${resipi.title}</h2>
							<a href="resipi.read?bno=${resipi.bno}" style="cursor: pointer; color:#333;">
							<p>${resipi.writer }</p>
							<p><fmt:parseDate var="date" value="${resipi.wrdate}"
							pattern="yyyy-mm-dd"/>
							<fmt:formatDate value="${date}" pattern="yy.mm.dd"/>
							</p></a>
						</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<%@include file="scripts.jsp" %>
<script>
$(function(){
	$('#myTab a').click(function (e) {
	  e.preventDefault()
	  $(this).tab('show')
	})
})
</script>
<%@include file="footer.jsp"%>