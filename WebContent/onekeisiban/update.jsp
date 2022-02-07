<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<%@include file="../naviontop.jsp"%>
<div class="col-md-12">
	<div class="row">
		<div class="col-md-9" style="float: none; margin: 0 auto;">
			<h2 style="text-align: center;">${dokopage}</h2>
			<div class="col-md-12">
				<div class="row">
					<form name="write" method="post" action="${doko}.modify"
						id="write" 
						onsubmit="return writecheck()" class="write">
						<table class="col-md-12 col-xs-12 write">
						<tr>
							<td>
								<input type="text" id="title" name="title" placeholder="件名" class="col-md-12 col-xs-12"
								value="${modify.title}">
								<p id="titlemsg" class="alertmsg"></p> 
							</td>
						</tr>
						<tr>
							<td  style="text-align: left">
							<textarea name="content" id="summernote" 
							class="summernote" 
							placeholder="本文">${modify.content}</textarea></td>
						</tr>
						<tr>
						<td>
							<div class="col-xs-1 col-md-1"></div>
							<button type="submit" class="btn btn-default col-xs-3 col-md-3">作成</button>
							<div class="col-xs-4 col-md-4"></div>
							<a href="${doko}.read?bno=${modify.bno}&pagenum=${cri.pagenum}
										&howmany=${cri.howmany}&type=${cri.type}
										&word=${cri.keyword}" 
										class="btn btn-default col-xs-3 col-md-3">キャンセル</a>
						</td>
						</tr>
						</table>
							<input type="hidden" name="bno" value="${modify.bno}">
							<input type="hidden" name="pagenum" value="${cri.pagenum}">
							<input type="hidden" name="howmany" value="${cri.howmany}">
							<input type="hidden" name="type" value="${cri.type}">
							<input type="hidden" name="word" value="${cri.keyword}">
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<%@include file="../scripts.jsp"%>
<%@include file="../footer.jsp"%>