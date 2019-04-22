<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hello World Home</title>
</head>
<body>
	<h2>Hello World Home</h2>
	<form method="post" action="http://localhost:8080/helloworld-webapp/fileupload.do" enctype="multipart/form-data">
		<input type="text" name="name">
		<input type="file" name="picture" accept="image/jpeg, image/png, image/gif">
		<input type="submit" value="upload">
	</form>
</body>
</html>