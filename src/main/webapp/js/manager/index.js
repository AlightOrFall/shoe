var file = document.getElementById("file");
var filePath = null;
file.addEventListener('change', function(e) {
	//获取文件
	var fileName = $('#file')[0].files[0];
	var fd = new FormData();
	fd.set('upload_image', fileName);
	uploadImage(formData);
	document.getElementById('img').setAttribute("src", filePath);
	
}, false);

function uploadImage(formData){
	 $.ajax({
         //接口地址
         url: "/outside/web/organization_lectuer/batch_import_lectuer.htm",
         type: 'post',
         data: formData,
         async: false,
         cache: false,
         contentType: false,
         processData: false,
         success: function (data) {
         	$("#file").val("");
         	filePath = data;
         }
     });
}