// /**
//  * Created by fangjiejie on 2017/8/8.
//  */
//点击bellx显示消息列表
// $(".example").turnBox({
//     width: 340,
//     height: 87,
//     axis: "X",
//     type:"skip",
//     duration: 500,
//     easing: "ease-in-out"
// });
$('#header .user').on('click',function () {
    $('#sider .infor').slideToggle('slow',function () {
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
// $("#msg").on('click',function (event) {
//     var m="sdgasdg";
//         event.preventDefault();
//         iziToast.show({
//             class: 'test',
//             color: 'dark',
//             icon: 'icon-contacts',
//             title: 'Hello!',
//             message:m,
//             transitionIn: 'flipInX',
//             transitionOut: 'flipOutX',
//             progressBarColor: 'rgb(0, 255, 184)',
//             layout:2,
//             onOpen:function () {
//
//             },
//             onClose: function(){
//                 console.info('onClose');
//             },
//             iconColor: 'rgb(0, 255, 184)'
//         });
// })
