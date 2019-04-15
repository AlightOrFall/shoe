requestMethod();
function requestMethod(){
	$.post("/shoe/outside/web/shoe/find.htm",{},function(data){
		if(data.header.flag != 1){
			return;
		}
		renderShoe(data);
	},"json");
}

function renderShoe(data){
	var parantNode = $("#td");
	parantNode.empty();
	if(data.content.length == 0){
		return;
	}
	var childNode = $("#tr");
	$.each(data.content,function(index,shoe){
		var oneNode = childNode.clone();
		oneNode.removeAttr("style");
		oneNode.find("#name").text(shoe.name);
		oneNode.find("#check_shoe").attr("shoeId",shoe.id);
		oneNode.find("#check_shoe").attr("commodityId",shoe.commodityId);
		oneNode.find("#color").text(shoe.color);
		oneNode.find("#price").text(shoe.price);
		oneNode.find("#count").text(shoe.count);
		oneNode.find("#sale_count").text(shoe.sale_count);
		oneNode.find("#create_date").text(shoe.createDate);
		oneNode.find("#modify_date").text(shoe.modifyDate);
		oneNode.find("#check_shoe").on("click",function(){
			var isChecked = removeChecked($("input[id='check_shoe']"),$(this));
			prohibit(isChecked);
		});
		parantNode.append(oneNode);
	});
}

function removeChecked(inputs,self){
	if(self.is(":checked")){
		$.each(inputs,function(index,input){
			$(input).prop("checked",false);
		});
		self.prop("checked",true);
		return true
	}
	return false;
}

function prohibit(isChecked){
	if(isChecked){
		$("#update").removeAttr("disabled");
		$("#delete_").removeAttr("disabled");
	}else{
		$("#update").attr("disabled","disabled");
		$("#delete_").attr("disabled","disabled");
	}
}

var update = document.getElementById("update");
update.onclick = function(){
	var shoeId = getValue();
	window.location.href="/shoe/manager/update.htm?shoeId="+shoeId;
}
var delete_ = document.getElementById("delete_");
delete_.onclick = deleteEvent;
function deleteEvent(){
	var shoeId = getValue();
	$.post("/shoe/outside/web/shoe/delete.htm",{
		"shoeId":shoeId
	},function(data){
		if(data.header.flag != 1){
			return;
		}
		alert("刪除成功");
		requestMethod();
	},"json");
}


function getValue(){
	var value = null;
	var inputs = $("input[id='check_shoe']");
	for(var i = 0;i<inputs.length;i++){
		if($(inputs[i]).is(":checked")){
			value = $(inputs[i]).attr("shoeId");
			break;
		}
	}
	return value;
}





