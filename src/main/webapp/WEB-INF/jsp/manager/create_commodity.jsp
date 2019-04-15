<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>添加商品</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="../../js/lib/jquery3.3.1.js"></script>
</head>

<body>
	<div class="form-group">
		<label class="sr-only" for="name">名称</label> <input type="text"
			class="form-control" id="new_name" placeholder="名称">
	</div>
	<div class="form-group">
		<label class="sr-only" for="stu_count">数量</label> <input type="text"
			class="form-control" id="new_count" placeholder="数量">
	</div>
	
	<div class="form-group">
		<button type="button" id="btn_save" class="btn btn-primary">保存</button>
	</div>
	<script type="text/javascript" src="../../js/manager/create_commodity.js"></script>
</body>
</html>
