<!DOCTYPE html>
<html>
<style>
input[type=submit], a {
	width: 50%;
	background-color: #4CAF50;
	font-size: 16px;
	color: white;
	padding: 14px 20px;
	margin: 8px 0;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

input[type=file] {
	width: 50%;
	padding: 12px 20px;
	margin: 8px 0;
	display: inline-block;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

input[type=submit]:hover {
	background-color: #45a049;
}

div {
	border-radius: 5px;
	background-color: #f2f2f2;
	padding: 20px;
	align-items: center;
	align-content: center;
}
</style>
<body>

	<h4>
		File named
		<%=request.getParameter("name")%>
		has been downloaded successfully
	</h4>


	<h3>Download file from server</h3>

	<div>
		<form action="FileDownloadServlet">
			Choose a file to download : <br>
			<br> Dummy File : <input type="submit" name="Download"
				value="file.txt" /> <br>
			<br> Image File : <input type="submit" name="Download"
				value="image.jpg" /> <br>
			<br> Resume : <input type="submit" name="Download"
				value="resume.docx" />
		</form>


	</div>

</body>
</html>

