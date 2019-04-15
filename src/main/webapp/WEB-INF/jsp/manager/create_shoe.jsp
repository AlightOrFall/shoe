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
<link rel="stylesheet" type="text/css"
	href="../bootstrap_lib/bootstrap/css/bootstrap.min.css">
<script type="text/javascript" src="../js/lib/jquery3.3.1.js"></script>
<script type="text/javascript"
	src="../bootstrap_lib/bootstrap/js/bootstrap.js"></script>
</head>

<body>
	<div class="form-group">
		<label class="sr-only" for="no">价格</label> <input type="text"
			class="form-control" id="new_price" placeholder="价格">
	</div>
	<div class="form-group">
		<label class="sr-only" for="no">价格</label> <input type="text"
			class="form-control" id="new_count" placeholder="数量">
	</div>
	<div class="form-group">
		<label class="sr-only" for="grade">颜色</label> <input type="text"
			class="form-control" id="new_color" placeholder="颜色">
	</div>
	<div class="form-group">
		<select id="select_shoe" class="form-control">
			<option>——选择鞋子品牌——</option>
		</select>
	</div>
	
	<div class="form-group">
       <label>上传图片
          	<span class="txt_gray">（每张图片不超过2m，只能传一张）</span>
        </label>
       <form id="form">
           <input type="file" id="file" name="file"/>
       </form>
       <a class="add_pic mui-pull-left" id="image">
          <!--  <img class="add_pic_one" src="/file/shoe/tmp/201904/1554945859107.jpg" height="100" id="img"/>
           <span class="add_pic_delete" id="del_img">x</span> -->
       </a>
  	</div>
	
	<div class="form-group">
		<button type="button" id="btn_save" class="btn btn-primary">保存</button>
	</div>
	<script type="text/javascript" src="../js/manager/create.js"></script>
</body>
</html>
