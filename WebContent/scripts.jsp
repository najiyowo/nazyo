<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="js/jquery-3.6.0.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/summernote.min.js"></script>

<script>
//summernote
	$(function(){
		$('#summernote').summernote({
			height: 300,
			fontNames : [ '맑은고딕', 'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', ],
			fontNamesIgnoreCheck : [ '맑은고딕' ],
			focus: true,
	
			callbacks: {
			onImageUpload: function(files, editor, welEditable) {
				for (var i = files.length - 1; i >= 0; i--) {
					sendFile(files[i], this);
					}
				}
			}
		});
	}) 

	function sendFile(file, el) {
		var form_data = new FormData();
		form_data.append('file', file);
		$.ajax({
			data: form_data,
			type: "POST",
			url: './upload.do',
			cache: false,
			contentType: false,
			enctype: 'multipart/form-data',
			processData: false,
			success: function(img_name) {
				$(el).summernote('editor.insertImage', img_name);
			}
		});
	}
	
	//error message
	function message(doko,msg,color){
		var alert = "#"+doko+"msg";
		var dore = "#"+doko;
		$(alert).css({"color": "#"+color});
		$(alert).html(msg);
		if(color!="00ff80"){$(dore).focus();}
	}// message
	
	//paging
	$(function() {
		var actionForm = $("#actionForm");

		$(".paging > a").on("click", function(e) {
			e.preventDefault();
			actionForm.find("input[name='pagenum']").val($(this).attr("href"));
			actionForm.submit();
		})
	})
</script>