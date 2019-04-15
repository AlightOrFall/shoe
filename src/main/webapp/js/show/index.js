var currentPage = 1;
var pageSize = 6;
requestMethod();
function requestMethod(){
	var name = paraseString($("#search_name").val());
	var price1 = parseNumber($("#search_price1").val());
	var price2 = parseNumber($("#search_price2").val());
	$.post("/shoe/outside/web/shoe/find_params.htm",{
		"name":name,
		"price1":price1,
		"price2":price2,
		"currentPage":currentPage,
		"pageSize":pageSize
	},function(data){
		if(data.header.flag != 1){
			return;
		}
		renderShoe(data);
	},"json");
}

function paraseString(value){
	if(value === null ||value.trim() === "" || value === undefined || value === " "){
		return null
	}
	return value;
}

function isDefined(value){
	return value === undefined;
}

function parseNumber(value){
	if(value.trim().length === 0){
		return null;
	}
	var ins = parseInt(value);
	var num = Number(ins);
	if(isNaN(num)){
		alert("价格是数字形式");
		return;
	}
	console.log(ins);
	return ins;
}

function renderShoe(data){
	var parentNode = $("#shoe_list_container");
	parentNode.empty();
	if(data.content.shoes.length == 0){
		return;
	}
	var childNode = $("#one_shoe");
	$.each(data.content.shoes,function(index,shoe){
		var shoeNode = childNode.clone();
		shoeNode.removeAttr("style");
		shoeNode.removeAttr("id");
		var image = shoe.image_path;
		image.indexOf("," != -1)?showImage(shoeNode,image):shoeNode.find("img").attr("src",shoe.image_path);
		shoeNode.find("p:eq(0)").text("名字:"+shoe.name);
		var shoeP = shoeNode.find("p:eq(1)");
		var count  = isDefined(shoe.count)?0:shoe.count;
		var sale_count  = isDefined(shoe.sale_count)?0:shoe.sale_count;
		shoeP.find("span:eq(0)").text("价格:"+shoe.price+"元");
		shoeP.find("span:eq(1)").text("数量:"+count+"双");
		shoeP.find("span:eq(2)").text("已售数量:"+sale_count+"双");
		parentNode.append(shoeNode);
	});
	pagination(data.content.pagination);
}

function showImage(shoeNode,image){
	var images = image.split(",");
	shoeNode.find("img").attr("src",images[0]);
	for(var i = 1;i<images.length;i++){
		var img = $("<img class='shoe_cover'/>");
		img.attr("src",images[i]);
		shoeNode.prepend(img);
	}
}

function pagination(pagination){
	$('.M-box3').pagination({
        pageCount: pagination.pageCount,//总页数
        totalData: pagination.count,//显示总条数
        showData: pageSize,//每页显示多少条记录
        current: pagination.currentPage,
        jump: true,
        coping: true,
        homePage: '首页',
        endPage: '末页',
        prevContent: '上页',
        nextContent: '下页',
        callback: function (api) {
        	currentPage = api.getCurrent();
        	requestMethod();
        }
    });
}

var btn_search = $("#btn_search");
btn_search.on("click",function(){
	requestMethod();
});


