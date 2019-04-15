var file = document.getElementById("file");
var currentImages = document.getElementById("currentImage").value;
var currentImage = isNull(currentImages)?[]:[currentImages];
var filePaths = currentImages.indexOf(",") != -1?currentImages.split(","):currentImage;
var headerPath = "/file";
var shoeId = document.getElementById("shoeId").value;
file.addEventListener('change', function(e) {
	//获取文件
	var fileName = $('#file')[0].files[0];
	var fd = new FormData();
	fd.set('upload_image', fileName);
	uploadImage(fd);
}, false);

function showImages(filePath){
	var image = document.getElementById('image');
	var img = document.createElement("img");
	img.classList.add("add_pic_one");
	img.setAttribute("height", 100);
	img.setAttribute("src", headerPath+filePath);
	image.appendChild(img);
	var span = document.createElement("span");
	span.classList.add("add_pic_delete");
	span.onclick = function(){
		var this_ = $(this);
		var imagePath = this_.prev().attr("src");
		var index = filePaths.indexOf(imagePath);
		filePaths.splice(index, 1);
		this_.prev().remove();this_.remove();
	}
	span.innerText = "x";
	image.appendChild(span);
}

function uploadImage(formData){
	 $.ajax({
         //接口地址
         url: "/shoe/outside/web/shoe/upload.htm",
         type: 'post',
         data: formData,
         async: false,
         cache: false,
         contentType: false,
         processData: false,
         success: function (data) {
         	$("#file").val("");
         	filePaths.push(headerPath+JSON.parse(data).content);
         	showImages(JSON.parse(data).content);
         }
     });
}

var btn_update = document.getElementById("btn_update");
btn_update.onclick = btnUpdateEvent;
function btnUpdateEvent(){
	var this_ = this;
	var name = getInputValue("name");
	var price = parseFloat(getInputValue("price"));
	var color = getInputValue("color");
	var count = parseFloat(getInputValue("count"));
	var saleCount = getInputValue("saleCount");
	var value = radioValue("sale");
	if(isNull(name) || isNull(price) || isNull(count)){
		alert("请填写信息");
		return;
	}
	var n = Number(price);
	
	if(isNaN(n)){
		alert("价格只能是数字");
		return;
	}
	n = Number(count);
	if(isNaN(n)){
		alert("数量只能是数字");
		return;
	}
	
	n = Number(saleCount);
	if(isNaN(n)){
		alert("出售数量只能是数字");
		return;
	}
	
	btnSaveData(name,price,color,count,filePaths.toString(),saleCount,value,function(data){
		alert("修改成功");
	});
	
}

function btnSaveData(name,price,color,count,filePaths,saleCount,value,callback){
	console.log(filePaths);
	$.post("/shoe/outside/web/shoe/update.htm",{
		"name":name,
		"price":price,
		"color":color,
		"count":count,
		"filePaths":filePaths,
		"saleCount":saleCount,
		"shoeId":shoeId,
		"isSale":value
	},function(data){
		if(data.header.flag != 1){
			return;
		}
		callback(data);
	},"json");
}


function getInputValue(id){
	return document.getElementById(id).value;
}

function isNull(content){
	if(content === "" || content === null || content === undefined){
		return true;
	}
	return false;
}

function updateImage(this_){
	var this2 = $(this_);
	var imagePath = this2.prev().attr("src");
	var index = filePaths.indexOf(imagePath);
	filePaths.splice(index, 1);
	this2.prev().remove();
	this2.remove();
	if(isNull(filePaths.toString())){
		filePaths = [];
	}
}

function radioValue(attr){
	var radios = document.getElementsByName(attr);
	for(var i = 0; i< radios.length;i++){
		 if(radios[i].checked){
            return radios[i].value;
        }
	}
}




