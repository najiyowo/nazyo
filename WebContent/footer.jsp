<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<footer>
	<div class="col-xs-12 col-sm-12 col-md-12 footer">
		<div class="row">
			<div class="col-sm-1 col-md-1"></div>
			<div class="col-sm-7 col-md-7">
				<p>대전 어덕대로 주소주소 주소주소</p>
			</div>
			<div class="col-sm-3 col-md-3 goiken">
				뭔가 만들게... 있나...?<br>
				내가... 뭘.. 만들려고 했나...?
			</div>
			<div class="col-md-1"></div>
		</div>
	</div>
</footer>

<script>
	function ikencheck() {
		if (goiken.iken.value == "") {
			alert("内容がありません")
			return false;
		}
	}
</script>


</body>
</html>
