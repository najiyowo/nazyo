<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="../header.jsp"%>
<%@include file="../naviontop.jsp"%>
<div class="col-xs-12 col-md-12">
	<div class="row">
		<div class="subtitleinsubpage">
			<h2>${donopage}</h2>
		</div>
		<%@include file="../kensaku.jsp"%>
		<table class="col-xs-12 col-md-7" style="float: none; margin: 0 auto;">
			<colgroup>
				<col width="60%">
				<col width="15%">
				<col width="15%">
				<col width="10%">
			</colgroup>
			<tr>
				<th style="text-align: center;">件名</th>
				<th style="text-align: center;">日付</th>
				<th style="text-align: center;">表示回数</th>
				<th style="text-align: center;">作者</th>
			</tr>
			<c:set var="num" value="${count - ((pageMaker.cri.pagenum-1)*10)}" />
			<c:forEach var="list" items="${donolist}">
				<tr>
					<td><a href="${doko}.read?bno=${list.bno}&pagenum=${pageMaker.cri.pagenum}
					&howmany=${pageMaker.cri.howmany}&type=${pageMaker.cri.type}
					&word=${pageMaker.cri.keyword}"
						style="color: #333;">${list.title}</a></td>
					<td style="white-space: nowrap;"><fmt:parseDate var="wrdate"
							value="${list.wrdate}" pattern="yyyy-mm-dd" /> <fmt:formatDate
							value="${wrdate}" pattern="yy年mm月dd日" /></td>
					<td>${list.view_count}</td>
					<td>${list.writer}</td>
				</tr>
			</c:forEach>
			<c:set var="num" value="${num-1}" />
			<tr>
				<c:if test="${donopage=='お知らせ'&&admin==1}">
					<td colspan="4" style="text-align: right;border-bottom: none;">
						<a href="${doko}.write" class="writeabutton">書き込み</a>
					</td>
				</c:if>
				<c:if test="${donopage!='お知らせ'}">
					<td colspan="4" style="text-align: right;border-bottom: none;">
						<a href="" id="gowrite" class="writeabutton">書き込み</a>
					</td>
				</c:if>
			</tr>
		</table>
	</div>
</div>
<div class="col-xs-12 col-sm-12 col-md-12">
	<div class="row">
		<%@include file="../paging.jsp"%>
	</div>
</div>
<form id="gowriteForm" action="${doko}.write" method="get">
	<input type="hidden" name="userid" value="${userid}">
</form>
<%@include file="../scripts.jsp"%>
<script>
$("#gowrite").on("click", function(e) {
	var actionForm=$("#gowriteForm");
	e.preventDefault();
	var userid = actionForm.find("input[name='userid']").val();
	if(userid==""){
		alert("ログインが必要です");
		return false;
	}
	actionForm.submit();
})
</script>
<%@include file="../footer.jsp"%>
