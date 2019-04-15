<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>首页</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<link rel="stylesheet" type="text/css" href="../bootstrap_lib/bootstrap/css/bootstrap.min.css" >
<link rel="stylesheet" type="text/css" href="../js/lib/pagination/style/pagination.css" >
<link rel="stylesheet" type="text/css" href="../css/show/index.css" >
<script type="text/javascript" src="../js/lib/jquery3.3.1.js"></script>
<script type="text/javascript" src="../bootstrap_lib/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="../js/lib/pagination/js/jquery.pagination.min.js"></script>
</head>
<body>
	<div class="vke_sreen">
		<ul class="container_teaweb clearfix">
			<li>
				<div class="input-group web_teacher_search pull-right" role="search">
			      	<input type="text" id="search_name" placeholder="根据名称" />
					<input type="text" id="search_price1" placeholder="价格1" />
					<span>-</span>
					<input type="text" id="search_price2" placeholder="价格2" />
			      	<button class="btn btn-default" type="submit"  id="btn_search">搜索</button>
			    </div>
			</li>
		</ul>
	</div>
	<div class="shoe_sreen">
		<div class="clearfix">
			<div class="container_teaweb shoe clearfix">
				<div class="good_shoe clearfix" id="shoe_list_container"></div>
			</div>
		</div>
		<a class="shoe_one clearfix" id="one_shoe" style="display: none;"> 
			<img src="" class="shoe_cover" />
			<p class="shoe_title ellipsis_2"></p>
			<p class="shoe_bottom">
				<span></span>
				<span></span>
				<span></span>
			</p>
		</a>
	</div>
	<!-- 页码 -->
	<div class="panel-footer text-center">
		<div class="M-box3"></div>
	</div>
	<script type="text/javascript" src="/shoe/js/show/index.js"></script>
</body>
</html>
