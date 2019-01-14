/**
 * 
 */
$(document).ready(function() {
	$("button").click(function() {
		$.ajax({
			type : 'POST',
			url : 'http://localhost:8080/shorturl',
			data : JSON.stringify({
				"originalUrl" : $("#originalURL").val()
			}),
			contentType : "application/json; charset=utf-8",
			success : function(data) {
				$("#shortURL").val(data.shortUrl);
				$("#creationDate").val(data.creationDate);
				$("#numberAccess").val(data.numberAccess);
			}
		});
	});
});