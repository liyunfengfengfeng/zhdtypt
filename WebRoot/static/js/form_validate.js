/**
 * Created by fangjiejie on 2017/8/13.
 */
var validate=function (form) {
    jQuery.validator.addMethod("idCardvali",function (value) {
        var id=/^\d{14,17}(\d|X)$/gi
        return id.test(value);
    },$.validator.format("请确保输入正确的身份证号码"));
    jQuery.validator.addMethod('birthdate',function (value) {
        var date=new Date();
        // var date1=date.getYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
        var date2=new Date(value);
        console.log(date);
        console.log(date2)
        return date>date2;
    },$.validator.format("请正确选择出生日期"));
    jQuery.validator.addMethod('checkorganizId',function(value){
        $.ajax({
            type:"POST",
            url:"",
            data:"email="+value,
            statusCode:{
                200:function (data) {

                }
            }

        })
    },$.validator.format("该组织代码不存在"));
    jQuery.validator.addMethod("checkEmail",function (value) {
        $.ajax({
            type:"POST",
            url:"",
            data:"email="+value,
            statusCode:{
                200:function (data) {

                }
            }

        })
    },$.validator.format("该邮箱已被注册"));
    $(form).validate({
        rules: {
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
                minlength: 6,
                maxlength:10
            },
            repassword: {
                required: true,
                equalTo: "#password"
            },
            name:{
                required:true
            },
            idCard:{
                required:true,
                idCardvali:true
            },
            sex: {
                required: true,
            },
            birth:{
                required:true,
                birthdate:true
            },
            organize:{
                required:true
            }
        },
        messages: {
            email: {
                required: "请输入邮箱",
                email: "请输入正确的邮箱地址"
            },
            password: {
                required: "请输入密码",
                minlength: jQuery.format("密码为6~10位")
            },
            name:{
                required:"请输入姓名"
            },
            repassword: {
                required: "请输入确认密码",
                equalTo: "两次输入密码不一致"
            },
            sex:{
                required:"请选择性别",
                sex:"请选择性别"
            },
            idCard:{
                required:"请填写身份证号码"
            },
            birth:{
                required:"请填写出生日期"
            },
            organize:{
                required:"请填写组织代号"
            }
        },


        errorPlacement: function (error, element) { //指定错误信息位置
            if (element.is(':radio') || element.is(':checkbox')) { //如果是radio或checkbox
                var eid = element.attr('name'); //获取元素的name属性
                error.insertAfter('[value="female"]'); //将错误信息添加当前元素的父结点后面
            } else {
                error.insertAfter( element).wrapInner('<span class="errormsg"/>');
            }
        }
        
    })
}