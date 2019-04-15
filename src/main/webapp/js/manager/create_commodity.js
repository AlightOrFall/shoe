var btn_save = document.getElementById("btn_save");
btn_save.onclick = btnSaveEvent;
function btnSaveEvent(){
	var this_ = this;
	var name = getInputValue("new_name");
	var count = parseInt(getInputValue("new_count"));
	
	if(isNull(name)){
		alert("请填写信息");
		return;
	}
	
	btn_save.onclick = null;
	btnSaveData(name,count,function(data){
		alert("创建成功");
		btn_save.onclick = btnSaveEvent;
		initValue();
	});
	
}

function initValue(){
	document.getElementById("new_name").value="";
	document.getElementById("new_count").value="";
}
function btnSaveData(name,count,callback){
	$.post("/shoe/outside/web/commodity/create.htm",{
		"name":name,
		"count":count
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




