<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="pagination center-block"
	style="position: relative;">
	<div class= "paging"
	style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%)">
		<c:if test="${pageMaker.prev}">
			<a href="${pageMaker.startPage-1}"> 
			<i class="fa  fa-angle-double-left"></i>
			</a>
		</c:if>

		<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
			<a href="${num}" class="${pageMaker.cri.pagenum ==num?'active':''} forpaging">${num}</a>
		</c:forEach>

		<c:if test="${pageMaker.next}">
			<a href="${pageMaker.startPage+1}"> 
			<i class="fa  fa-angle-double-right"></i>
			</a>
		</c:if>
	</div>
</div>
<form id="actionForm" action="${doko}.do" method="get">
	<input type="hidden" name="pagenum" value="${pageMaker.cri.pagenum}">
	<input type="hidden" name="howmany" value="${pageMaker.cri.howmany}">
	<input type="hidden" name="type" value="${pageMaker.cri.type}">
	<input type="hidden" name="word" value="${pageMaker.cri.keyword}">
</form>