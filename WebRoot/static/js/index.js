// /**
//  * Created by fangjiejie on 2017/8/8.
//  */
//点击个人信息
$('#header .user').on('click',function () {
    $('#sider .infor').slideToggle('slow',function () {
    })
})
//点击收缩菜单
$('#header .hidemenu').on('click',function () {
    $('#sider').toggle('slow',function () {
    })
})
//点击消息提示
$('#msg').on('click',function () {
    var mpos=$(this).position();
    $('.msglist').css({
        left:mpos.left-$(this).width()*3.5,
        top:mpos.top+50
    }).addClass('show').toggleClass('turn');
})
//点击出现搜索栏
$('#search').on('click',function () {
    
    $('#topsearch').toggle('slow',function () {
        $(this).css({
            height:50
        })
    })
})
$('#header .lockscreen').on('click',function () {
    lockscreen();
})
function lockscreen() {
    $('#bg').css("z-index","2");
    $('#lock').addClass('show');
    $.cookie('lock','lock');
}
function unlockscreen() {
    $('#bg').css("z-index","-1");
    $('#lock').removeClass('show');
    $.cookie('lock','unlock');
}
$('#lock .unlock .switch-box').on('click',function () {
    if($('.switch-box-input').val()=="on"){
        var ps=$("#lock .unlock input[type=password]").val();
        var  json = {password:ps}
        //解锁
        $.ajax({
            type:"POST",
            url:"UnlockAction_unlockScreen.do",
            contentType:"application/json",
            dataType:"html json",
            data:JSON.stringify(json),
            success:function (data) {
                var jsonStr=data;
                var jsonObj =  JSON.parse(jsonStr);
                console.log(jsonObj.state);
                if(jsonObj.state==1){//解锁成功
                    unlockscreen();
                    window.location="IndexAction_index.do";
                }else{//解锁失败
                    $('#lock .unlock input[type=password]').next().html("密码错误");

                }


            }
        })
    }
})
