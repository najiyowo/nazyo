<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<%@include file="../naviontop.jsp"%>
<div class="col-md-12">
	<div class="row">
		<div class="col-md-8 col-xs-11" style="float: none; margin: 0 auto;">
			<h2 style="text-align: center;">${donopage}</h2>
			<div class="col-md-12">
				<div class="row">
					<table class=contenttable style="margin-bottom: 10px;">
						<colgroup>
							<col width="30%">
							<col width="70%">
						</colgroup>
						<tr>
							<th>件名</th>
							<td>${list.title}</td>
						</tr>
						<tr>
							<th>日付</th>
							<td>${list.wrdate}</td>
						</tr>
						<tr>
							<th>作者</th>
							<td>${list.writer}</td>
						</tr>
						<tr>
							<td colspan="2" style="white-space: pre-wrap; text-align:left;">
								<div>
									<div class="table_img">${list.content}</div>
								</div>
							</td>
						</tr>
					</table>
					<div class="prenext">
						<div class="button3 col-md-12 col-xs-12 col-sm-12">
							<div class="row">
								<div class="flex">
									<a href="${doko}.modify?bno=${list.bno}&pagenum=${cri.pagenum}
										&howmany=${cri.howmany}&type=${cri.type}
										&word=${cri.keyword}"
										class="btn btn-default col-xs-3 col-md-3 samerow">アップデート</a> <a
										href="${doko}.do?pagenum=${cri.pagenum}
										&howmany=${cri.howmany}&type=${cri.type}
										&word=${cri.keyword}"
										class="btn btn-default col-xs-3 col-md-3 samerow" id="back">掲示板へ</a>
									<c:if test="${userid==list.writer||admin==1}">
									<a href="${doko}.delete?bno=${list.bno}"
										class="btn btn-default col-xs-3 col-md-3 samerow"
										onClick="return confirm('本当に消しますか？')">消す</a>
									</c:if>
								</div>
							</div>
						</div>
						<div class=" col-md-6 col-xs-12 col-sm-12">
							<span> <c:if test="${prev.bno!=0}">
									<a
										href="${doko}.read?bno=${prev.bno}&pagenum=${cri.pagenum}
										&howmany=${cri.howmany}&type=${cri.type}
										&word=${cri.keyword}">&lt;PREV
										${prev.title}</a>
								</c:if>
							</span>
						</div>

						<div class="col-md-6 col-xs-12 col-sm-12">
							<p style="text-align: right;">
								<c:if test="${next.bno!=0}">
									<a
										href="${doko}.read?bno=${next.bno}&pagenum=${cri.pagenum}
										&howmany=${cri.howmany}&type=${cri.type}
										&word=${cri.keyword}">
										${next.title} NEXT&gt;</a>
								</c:if>
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<table class="coment_tble">
						<colgroup>
							<col style="width: 15%">
							<col style="width: 5%">
							<col style="width: 80%">
						</colgroup>
						<tr>
							<td colspan="3">
								<div class="comment_box">

									<textarea id="comment_text" name="comment_text"
										class="comment_text" placeholder="コメント入力"></textarea>

									<button class="comment_submit" id="comment_submit"
										onclick="comment_submit();">コメント作成</button>

								</div>
						</tr>
					</table>
					<table class="coment_tble" id="newcomment">
						<colgroup>
							<col style="width: 15%">
							<col style="width: 5%">
							<col style="width: 80%">
						</colgroup>
						<c:forEach var="comments" items="${commentlist }">
							<tr>
								<td>
									<h3>${comments.userid }</h3> 
									<fmt:parseDate var='wdate' value="${comments.wdate}" pattern="yy-mm-dd" /> 
									<span><fmt:formatDate value="${wdate}" pattern="yyyy-mm-dd" /></span>
								</td>
								<td></td>
								<td>${comments.comment_content }</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<input type="hidden" value="${userid}" id="userid">
<input type="hidden" value="${doko}" id="doko">
<input type="hidden" value="${list.bno}" id="bno">
<%@include file="../scripts.jsp"%>
<script>
	function comment_submit() {
		var userid = "${sessionScope.userid}"
		if ($("#comment_text").val() == "") {
			message("comment", "内容を入力お願いします", "ff0000");
			return false;
		}
		if (userid == "") {
			alert("ログインが必要です");
			return false;
		}
		$.ajax({
			type : "post",
			url : "Comment.do",
			data : {
				"userid" : $("#userid").val(),
				"doko" : $("#doko").val(),
				"bno" : $("#bno").val(),
				"comment_text" : $("#comment_text").val()
			},
			success : function(cvo) {
				$("#comment_text").val("");
				getcommentlist();
			},
			error : function() {
				alert("error")
			}
		});
	}

	function getcommentlist() {
		$.ajax({
			type : "get",
			url : "Comment.do",
			data : {
				"doko" : $("#doko").val(),
				"bno" : $("#bno").val()
			},
			contentType : "application/json:charset=UTF-8",
			success : function(result) {
				var output = "";
				for ( var i in result) {
					output += "<colgroup>";
					output += "<col style='width: 15%'>";
					output +="<col style='width: 5%'>";
					output +="<col style='width: 80%'>";
					output +="</colgroup>";
					output += "<tr>"
					output += "<td>";
					output += "<h3>" + result[i].userid + "</h3>";
					output += "<span>" + result[i].wdate + "</span>";
					output += "</td>";
					output += "<td></td>";
					output += "<td>" + result[i].comment_content + "</td>";
					output += "</tr>"
				}
				$("#newcomment").html(output);
			}
		})
	}
</script>
<%@include file="../footer.jsp"%>