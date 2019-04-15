 <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
	<meta charset="utf-8"/>
	<title>首页</title>
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no"/>
	<meta name="apple-mobile-web-app-capable" content="yes"/>
	<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
	<link rel="stylesheet" type="text/css" href="../bootstrap_lib/bootstrap/css/bootstrap.min.css" >
	<script type="text/javascript" src="../js/lib/jquery3.3.1.js"></script>
	<script type="text/javascript" src="../bootstrap_lib/bootstrap/js/bootstrap.js"></script>
	<style type="text/css">
		.success{
			width: 100px;
		}
	</style>
</head>
<body>
	<a href="/shoe/manager/create.htm"><button type="button"  style="margin-left: 5%;" id="create" class="btn btn-primary" >创建</button></a>
	<button type="button" id="update" class="btn btn-primary" style="margin-left: 5%;"  disabled="disabled">修改</button>
	<button type="button" id="delete_" class="btn btn-primary" style="margin-left: 5%;"  disabled="disabled">删除</button>
	<table border="1" style="margin: 50px;">
		<thead>
			<th class="success">选择</th>
			<th class="success">名字</th>
			<th class="success">颜色</th>
			<th class="success">价格</th>
			<th class="success">总数量</th>
			<th class="success">已售数量</th>
			<th class="success">创建时间</th>
			<th class="success">修改时间</th>
		</thead>
		<tr id="tr" style="display: none">
			<td>
				<input type="checkbox" name="checkbox" id="check_shoe"/>
			</td>
			<td id="name"></td>
			<td id="color"></td>
			<td id="price"></td>
			<td id="count"></td>
			<td id="sale_count"></td>
			<td id="create_date"></td>
			<td id="modify_date"></td>
		</tr>
		<tbody id="td"></tbody>
	</table>
	<script type="text/javascript" src="../js/manager/index2.js"></script>
</body>
</html>
