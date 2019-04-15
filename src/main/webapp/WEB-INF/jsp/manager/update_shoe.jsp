<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML>
<html>
<head>
<title>修改商品</title>
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
	<input type="hidden" value="${imagePath}" id="currentImage"/>
	<input type="hidden" value="${shoe.id}" id="shoeId"/>
	<div class="form-group">
		名称：<input type="text"
			 id="name" value="${commodity.name}" readonly="readonly">
	</div>
	<div class="form-group">
		价格： <input type="text"
			 id="price" value="${shoe.price}">
	</div>
	<div class="form-group">
		颜色： <input type="text"
			 id="color" value="${shoe.color}">
	</div>
	<div class="form-group">
		数量： <input type="text"
			 id="count" value="${shoe.count}">
	</div>
	<div >
		已售数量： <input type="text"
			 id="saleCount" value="${shoe.saleCount}">
	</div>
	<div>
		是否出售： <input type="radio" value="1" name="sale" <c:if test="${shoe.sale}">checked</c:if>/>是
		<input type="radio" name="sale" value="0" <c:if test="${!shoe.sale}">checked</c:if>/>否
	</div>
	<div class="form-group">
		创建时间： 
			 <fmt:formatDate value="${commodity.createDate}" pattern="yyyy年MM月dd日"/>
	</div>
	<div class="form-group">
		修改时间： 
			  <fmt:formatDate value="${commodity.modifyDate}" pattern="yyyy年MM月dd日"/>
	</div>
	
	<div class="form-group">
       <label>上传图片
          	<span class="txt_gray">（每张图片不超过2m，只能传一张）</span>
        </label>
       <form id="form">
           <input type="file" id="file" name="file"/>
       </form>
       <a class="add_pic mui-pull-left" id="image">
      		<c:forEach items="${imagePaths}" var="image">
      			<img class="add_pic_one" src="${image}" height="100"/>
          		<span class="add_pic_delete" onclick="updateImage(this)">x</span>
      		</c:forEach>
       </a>
  	</div>
	
	<div class="form-group">
		<button type="button" id="btn_update" class="btn btn-primary">保存</button>
	</div>
	<script type="text/javascript" src="../js/manager/update.js"></script>
</body>
</html>
