var file = document.getElementById("file");
var filePaths = [];
var headerPath = "/file";
findShoeTypeList();
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

var btn_save = document.getElementById("btn_save");
btn_save.onclick = btnSaveEvent;
function btnSaveEvent(){
	var this_ = this;
	var price = parseFloat(getInputValue("new_price"));
	var color = getInputValue("new_color");
	var commodityId = $('#select_shoe option:selected').val();
	var count = parseInt(getInputValue("new_count"));
	if(isNull(price) || isNull(color)){
		alert("请填写信息");
		return;
	}
	
	if(parseInt(commodityId) === 0){
		alert("请选择鞋子品牌类型");
		return;
	}
	var n = Number(price);
	
	if(isNaN(n)){
		alert("价格只能是数字");
		return;
	}
	
	btn_save.onclick = null;
	
	btnSaveData(price,count,color,filePaths.toString(),commodityId,function(data){
		alert("创建成功");
		btn_save.onclick = btnSaveEvent;
		initValue();
	});
	
}


function findShoeTypeList(){
	$.post("/shoe/outside/web/shoe/find_shoe_type_list.htm",{
	},function(data){
		if(data.header.flag != 1){
			return;
		}
		renderSelect(data);
	},"json");
}
function renderSelect(data){
	var selectShoe = $("#select_shoe");
	selectShoe.empty();
	var option = $("<option></option>");
	option.attr("value",0);
	option.text("——请选择品牌——");
	selectShoe.append(option);
	if(data.content.length == 0){
		return;
	}
	$.each(data.content,function(index,oneSelect){
		var oneOption = $("<option value='"+oneSelect.id+"'>"+oneSelect.name+"</option>");
		selectShoe.append(oneOption);
	});
}

function initValue(){
	document.getElementById("new_price").value="";
	document.getElementById("new_color").value="";
	document.getElementById("new_count").value="";
	$("#image").empty();
	filePaths = [];
}
function btnSaveData(price,count,color,filePaths,commodityId,callback){
	$.post("/shoe/outside/web/shoe/create.htm",{
		"price":price,
		"color":color,
		"count":count,
		"filePaths":filePaths,
		"commodityId":commodityId
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




