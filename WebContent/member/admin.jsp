<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../header.jsp"%>
<%@include file="../naviontop.jsp"%>

<div class="col-md-12">
	<div class="row">
		<div class="subtitleinsubpage">
			<h2>管理者の設定</h2>
		</div>
		<div class="col-xs-12 col-md-8" style="float: none; margin: 0 auto;">
			<div class="col-xs-12 col-md-12 contentreispi">
				<div class="row">
						<table class="col-xs-12 col-md-12">
							<colgroup>
								<col width:28%/>
								<col width:70%/>
							</colgroup>
							<tr>
								<th style="text-align: center;">ID</th>
								<th style="text-align: center;">E-mail</th>
								<th style="text-align: center;">権限</th>
							</tr>
							<c:forEach var="list" items="${list}">
							<tr>
								<td>
									${list.id}
								</td>
								<td>${list.email}</td>
							<c:if test="${list.admin==1}">
								<td>
									<a href="admin.update?userid=${list.id}&admin=1">解除</a>
								</td>
								</c:if>
								<c:if test="${list.admin==0}">
								<td>
									<a href="admin.update?userid=${list.id}&admin=0">委任</a>
								</td>
								</c:if>
							</tr>	
							</c:forEach>
							<tr>
								<td colspan="3">
									<div style="width: 60%; margin: 0 auto;">
										<button type="submit"
											class="btn btn-default col-xs-5 col-md-5" style="float: left">作成</button>
										<button onclick="window.history.back();"
											class="btn btn-default col-xs-5 col-md-5"
											style="float: right;">戻る</button>
									</div>
								</td>
							</tr>
						</table>

				</div>
			</div>
		</div>
	</div>
</div>


<%@include file="../scripts.jsp"%>
<%@include file="../footer.jsp"%>