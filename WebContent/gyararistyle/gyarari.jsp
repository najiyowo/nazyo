<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<%@include file="../naviontop.jsp"%>

<div class="col-md-12">
	<div class="row">
		<div class="subtitleinsubpage">
			<h2>ギャラリー</h2>
		</div>
		<div class="col-md-1"></div>
		<div class="col-md-10">
			<div class="row">
				<div class="col-md-12">
					<div class="col-md-8"></div>
					<div class="col-md-4">
						<form class="form-inline">
							<div class="form-group">
								<select id="searchtype" style="height:30px;">
									<option value="title">件名
									<option value="writer">作者
								</select>
								<label class="sr-only" for="kensaku">検索</label> <input
									type="text" class="form-control" id="kensaku" placeholder="検索">
							</div>
							<button type="submit" class="btn btn-default">検索</button>
						</form>
					</div>
				</div>
				<c:forEach var="glist" items="${gyararilist}">
					<div class="col-sm-6 col-md-3 contentgyarari">
						<div class="thumbnail">
							<img
								src="${pageContext.request.contextPath}/upload/${glist.imgurl}"
								alt="">
							<div class="caption">
								<h3>${glist.title}</h3>
								<p>${glist.content}</p>
								<p>${glist.writer}</p>
								<fmt:parseDate var="date" value="${glist.wrdate}"
									pattern="yyyy-mm-dd" />
								<p style="text-align: right; font-size: 12px">
									<fmt:formatDate value="${date}" pattern="yyyy.mm.dd" />
								</p>
								<a href="#" class="btn btn-primary buttona" role="button">게시글보기</a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<div class="col-md-1"></div>
	</div>
</div>

<div class="w100per">
	<div class="col-md-12">
		<div class="row">
			<div class="writeabutton posrel" align="right">
				<a href="gyarariwrite.do">書き込み</a>
			</div>
		</div>
	</div>
	<div class="pagination center-block" style="width: 420px;">
		<a href="#">&laquo;</a> <a href="#">1</a> <a class="active" href="#">2</a>
		<a href="#">3</a> <a href="#">4</a> <a href="#">5</a> <a href="#">6</a>
		<a href="#">7</a><a href="#">8</a><a href="#">9</a><a href="#">10</a>
		<a href="#">&raquo;</a>
	</div>
</div>

<%@include file="../scripts.jsp"%>
<%@include file="../footer.jsp"%>