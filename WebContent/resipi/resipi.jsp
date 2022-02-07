<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<%@include file="../naviontop.jsp"%>

<div class="col-md-12">
	<div class="row">
		<div class="subtitleinsubpage">
			<h2>レシピ</h2>
		</div>
		<div class="col-xs-12 col-md-8" style="float: none; margin: 0 auto;">
			<c:forEach var="list" items="${donolist}">
				<div class="col-xs-12 col-md-12 content_reispi">
					<div class="row">
						<div class="col-xs-12 col-md-4">
							<a href="${doko}.read?bno=${list.bno}"> <img
								src="${pageContext.request.contextPath}/upload/${list.imgurl}"
								class="col-xs-12 col-md-12 resipi_img">
							</a>
						</div>
						<div class="col-xs-12 col-md-8 contenttext">
							<a href="${doko}.read?bno=${list.bno}
							&pagenum=${pageMaker.cri.pagenum}
							&howmany=${pageMaker.cri.howmany}&type=${pageMaker.cri.type}
							&word=${pageMaker.cri.keyword}"> <span
								class="resipi_title">${list.title}</span> <span
								style="display: block;">${list.wrdate}</span> <span
								style="text-align: right">${list.writer}</span>
							</a> <span class="glyphicon glyphicon-eye-open" aria-hidden="true"
								style="float: right;"> ${list.view_count} </span>

						</div>
					</div>
				</div>
			</c:forEach>
			<div class="w100per">
				<div class="col-md-12">
					<div class="row">
						<a href="resipi.write" class="writeabutton" style="float:right">書き込み</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="col-xs-12 col-sm-12 col-md-12">
	<div class="row">
		<%@include file="../paging.jsp"%>
	</div>
</div>

<%@include file="../scripts.jsp"%>
<%@include file="../footer.jsp"%>
