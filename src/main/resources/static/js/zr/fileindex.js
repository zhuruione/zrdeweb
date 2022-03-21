//回到顶部按钮
$(function () {
    $(window).scroll(function () {
        if ($(this).scrollTop() != 0) {
            $("#toTop").fadeIn();
        } else {
            $("#toTop").fadeOut();
        }
    });
    $("body").append("<img src='/image/fileimg/totop.png' height='50px' id=\"toTop\" style=\"position:fixed;bottom:30px;right:10px;display:none;\"></img>");
    $("#toTop").click(function () {
        $("body,html").animate({scrollTop: 0}, 800);
    });
})
//删除操作
function delectfile(id) {
    $("#delectfiletrue").attr('onclick','delectbyid(\''+id+'\')');
    $('#willdelectfile').modal('show')
}
function delectbyid(id){
    $.post(
        '/file/delect',
        {
            path: $("#pathid_" + id).text(),
        },
        function (data){
            if(data=='true'){
                $('.fileclassid_'+id).remove();
                $('#willdelectfile').modal('hide')
            }
        }
    )
}
//重命名操作
function rename(id){
    $("#renametrue").attr('onclick','renamebyid(\''+id+'\')')
    $("#oldname").val($('#filename_'+id).text())
    $('#willrename').modal('show')
    $('#willrename').on('shown.zui.modal', function() {
        $("#oldname").focus();
    })
}
//文件重命名
function renamebyid(id){
    $.post(
        '/file/rename',
        {
            name: $("#oldname").val(),
            path: $("#pathid_" + id).text(),
        },
        function (data){
            if(data!=null){
                var name=data.name
                var path=data.path
                $("#pathid_"+id).text(path)
                $('#nameid_'+id).text(name)
                $("#filename_"+id).text(name.substring(0,name.lastIndexOf('.')))
                if(name.length>=15){
                    var smallname=name.substring(0,7)+"..."+name.substring(name.length-7,name.length)
                    $("#smallname_"+id).text(smallname)
                }else {
                    $("#smallname_"+id).text(name)
                }
                $('#willrename').modal('hide')
            }
        }
    )
}
//文件夹重命名
function renamefordir(id){
    $.post(
        '/file/rename',
        {
            name: $("#oldname").val(),
            path: $("#dirpathid_" + id).text(),
        },
        function (data){
            if(data!=null){
                var name=data.name
                var path=data.path
                $("#dirpathid_"+id).text(path)
                $('#dirnameid_'+id).text(name)
                if(name.length>=15){
                    var smallname=name.substring(0,7)+"..."+name.substring(name.length-7,name.length)
                    $("#dirsmallname_"+id).text(smallname)
                }else {
                    $("#dirsmallname_"+id).text(name)
                }
                $('#willrename').modal('hide')
            }
        }
    )
}
$(function (){
    //给文件元素添加上下文菜单
    var obj=$(".mainspan")
    $.each(obj,function (index,value){
        var id=value.id
        id=id.substring(2,id.length)
        $("#f_"+id).contextmenu({
            items: [{
                label: '下载',
                onClick: function() {
                    $("#filedownid_"+id).click();
                }
            }, {
                type: 'divider'
            }, {
                label: '删除',
                onClick: function() {
                    $("#filedelectid_"+id).click();
                }
            }, {
                label: '重命名',
                onClick: function() {
                    $("#filerenameid_"+id).click();
                }
            }]
        });
    })
    //给文件夹元素添加上下文菜单
    var dir=$(".maindir")
    $.each(dir,function (index,value){
        var id=value.id
        id=id.substring(2,id.length)
        $("#d_"+id).contextmenu({
            items: [
                {
                label: '打开',
                onClick: function() {
                    dirin(id)
                }
            },{
                label: '重命名',
                onClick: function() {
                    $("#renametrue").attr('onclick','renamefordir(\''+id+'\')')
                    $("#oldname").val($('#dirnameid_'+id).text())
                    $('#willrename').modal('show')
                    $('#willrename').on('shown.zui.modal', function() {
                        $("#oldname").focus();
                    })
                }
            }, {
                label: '移动位置',
                onClick: function() {
                    
                }
            }]
        });
    })
})
//文件夹操作
function dirin(id) {
    var a=$('#dirhref_'+id).attr('href');
    $(location).attr("href", a);
}
