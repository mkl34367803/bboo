ID =$('#vark_user_id').val();

$(function() {
    timeID = setInterval("timeinfo('"+ID+"')", 15000);
});

function timeinfo(id){
       var paras = {
                id :id
       };
       $.get('/timeinfo', paras, function(data){
            for(var i in data){
                console.log(i);
                if (data[i]['status'] == "error") {
                    $('#yadata_'+data[i]['id']).html("<strong class='status_error'>"+data[i]['info']+"</strong>");
                }else{
                    $('#yadata_'+data[i]['id']).html("<strong class='status_ok'>"+data[i]['info']+"</strong>");
                }
            }
            },'json');
}


$(document).ready(function(){

   if ($.browser.msie && $.browser.version == "6.0") {
                var html = '<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" id="sound" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,14,0" width="1" height="1"><param name="movie" value="../static/sound.swf"/><param name="quality" value="high"/><param name="bgcolor" value="#ffffff"/></object>';
                $("#sound-container").html(html);
        } else {
                var html = '<embed src="../static/sound.swf" quality="high" bgcolor="#ffffff" width="1" height="1" type="application/x-shockwave-flash" name="sound" id="sound" pluginspage="http://www.macromedia.com/go/getflashplayer"></embed>';
                $("#sound-container").html(html);
        }

    $('.J_add_ya_data').click(function(){
        p = $(this).attr('name');
        showAlert("确认要上传当前策略吗？"+p, 'confirm', function(){
               FnoDataSubmit(p);
                        });
    });
    $('.J_del_chupiao').click(function(){
        p = $(this).attr('name');
        showAlert("确认要删除当前航班信息吗？"+p, 'confirm', function(){
		window.location.href = '/delchupiao?id='+p;
        });
    });

    $('.J_show_log_detail').click(function(){
        p = $(this).attr('name');
        console.log(p);
        $.get('/jsonyalog?id='+p, function(data){
           showAlert(data);
        });

    });
    function FnoDataSubmit(uri){
       showLoading('正在提交航班信息');
       var paras = {
                id : uri
       };
       $.get('/toqueue', paras, function(data){
            hideLoading();
            if(data == 'ok'){
                $('#yadata_'+uri).html("开始搜索,请等待");
                window.document.getElementById('sound').TCallLabel('/', "imReceive");
            }else{
                    data = eval('(' + data + ')');
            }
            });
    }

});
