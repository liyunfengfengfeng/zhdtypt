/**
 * Created by fangjiejie on 2017/8/10.
 */
$(function() {
    $('#emailbutton').on('click',function () {
        alert('已发送验证码至你的邮箱，请查收');
        //ajax发送
        var json=JSON.stringify($("#email").serializeObject());
        //console.log("=========="+json);
        $.ajax({
            type:"POST",
            url:"RegisterAction_sendEmail.do",
            contentType:"application/json",
            dataType:"html json",
            data:json,
            success:function (data) {
                var jsondata = eval("("+data+")");
                var result = jsondata.state;
                console.log(result);
                // if(parseInt(result)==0){
                //     //     console.log("与注册成功的路上渐行渐远");
                //     //     window.location.href=="AccountAction_toRegister.do";
                //     //     //window.navigate("AccountAction_toRegister.do");
                //     window.location="AccountAction_toRegister.do";
                // }else{//注册成功
                //     //     console.log("正在眺望注册成功的道路上");
                //     //     window.location.href=="AccountAction_toLogin.do";
                //     //     //window.navigate("AccountAction_toLogin.do");
                //     window.location="AccountAction_toLogin.do";
                // }

            }

        })





    })

    var $formElem=$('#formElem');
    validate($formElem);
    /*
     number of fieldsets
     */
    var fieldsetCount = $('#formElem').children().length;

    /*
     current position of fieldset / navigation link
     */
    var current 	= 1;
    /*
     sum and save the widths of each one of the fieldsets
     systemparametermanagement the final sum as the total width of the steps element
     */
    var stepsWidth	= 0;
    var widths 		= new Array();
    $('#steps .step').each(function(i){
        var $step 		= $(this);
        widths[i]  		= stepsWidth;
        stepsWidth	 	+= $step.width();
    });
    $('#steps').width(stepsWidth);

    /*
     to avoid problems in IE, focus the first input of the form
     */
    $('#formElem').children(':first').find(':input:first').focus();

    /*
     show the navigation bar
     */
    $('#navigation').show();

    /*
     when clicking on a navigation link 
     the form slides to the corresponding fieldset
     */
    $('#navigation a').bind('click',function(e){
        var $this	= $(this);
        var prev	= current;
        $this.closest('ul').find('li').removeClass('selected');
        $this.parent().addClass('selected');
        /*
         we store the position of the link
         in the current variable	
         */
        current = $this.parent().index() + 1;
        /*
         animate / slide to the next or to the corresponding
         fieldset. The order of the links in the navigation
         is the order of the fieldsets.
         Also, after sliding, we trigger the focus on the first 
         input element of the new fieldset
         If we clicked on the last link (confirmation), then we validate
         all the fieldsets, otherwise we validate the previous one
         before the form slided
         */
        $('#steps').stop().animate({
            marginLeft: '-' + widths[current-1] + 'px'
        },500,function(){
            if(current == fieldsetCount)
                validateSteps();
            else
                validateStep(prev);
            $('#formElem').children(':nth-child('+ parseInt(current) +')').find(':input:first').focus();
        });
        e.preventDefault();
    });

    /*
     clicking on the tab (on the last input of each fieldset), makes the form
     slide to the next step
     */
    
    $('#formElem > fieldset').each(function(){
        var $fieldset = $(this);
        $fieldset.children(':last').find(':input').keydown(function(e){
            if (e.which == 9){
                $('#navigation li:nth-child(' + (parseInt(current)+1) + ') a').click();
                /* force the blur for validation */
                $(this).blur();
                e.preventDefault();
            }
        });
    });

    /*
     validates errors on all the fieldsets
     records if the Form has errors in $('#formElem').data()
     */
    function validateSteps(){
        var FormErrors = false;
        for(var i = 1; i < fieldsetCount; ++i){
            var error = validateStep(i);
            if(error == -1)
                FormErrors = true;
        }
        $('#formElem').data('errors',FormErrors);
    }

    /*
     validates one fieldset
     and returns -1 if errors found, or 1 if not
     */
    function validateStep(step){
        if(step == fieldsetCount) return;

        var error = 1;
        var hasError = false;
        $('#formElem').children(':nth-child('+ parseInt(step) +')').find(':input:not(button)').each(function(){
            var $this 		= $(this);
            var valueLength = jQuery.trim($this.val()).length;
            if(valueLength == ''){
                hasError = true;
                $this.css('background-color','#FFEDEF');
            }
            else
                $this.css('background-color','#FFFFFF');
        });
        var $link = $('#navigation li:nth-child(' + parseInt(step) + ') a');
        $link.parent().find('.error,.checked').remove();

        var valclass = 'checked';
        if(hasError){
            error = -1;
            valclass = 'error';
        }
        $('<span class="'+valclass+'"></span>').insertAfter($link);

        return error;
    }

    /*
     if there are errors don't allow the user to submit
     */
    $('#registerButton').bind('click',function(){
        if($('#formElem').data('errors')){
            alert('请认真检查表单中的错误，并改正后提交');
            return false;
        }else{
            //提交注册表单
            // console.log("提交用户的注册信息");
            // var form1=document.getElementById("formElem");
            // form1.action="RegisterAction_registerUser.do";//设置提交路径
            // //alert('注册成功');
            // form1.submit();


            var json=JSON.stringify($("#formElem").serializeObject());
            //console.log("=========="+json);
            $.ajax({
                type:"POST",
                url:"RegisterAction_registerUser.do",
                contentType:"application/json",
                dataType:"html json",
                data:json,
                success:function (data) {
                    var jsondata = eval("("+data+")");
                    var result = jsondata.flag;
                    console.log(result+"=====");
                    if(parseInt(result)==0){
                    //     console.log("与注册成功的路上渐行渐远");
                    //     window.location.href=="AccountAction_toRegister.do";
                    //     //window.navigate("AccountAction_toRegister.do");
                        window.location="AccountAction_toRegister.do";
                    }else{//注册成功
                    //     console.log("正在眺望注册成功的道路上");
                    //     window.location.href=="AccountAction_toLogin.do";
                    //     //window.navigate("AccountAction_toLogin.do");
                        window.location="AccountAction_toLogin.do";
                    }

                }

            })
        }
    });
    //校验验证码
    $('#vericode').bind('blur',function(){

            var json=JSON.stringify($("#vericode").serializeObject());
            $.ajax({
                type:"POST",
                url:"RegisterAction_checkVericode.do",
                contentType:"application/json",
                dataType:"html json",
                data:json,
                success:function (data) {
                    //var jsondata = eval("("+data+")");
                    //var result = jsondata.flag;
                    //console.log(jsondata);
                    var jsonStr=data;
                    var jsonObj =  JSON.parse(jsonStr);
                    //Juery 写法 var jsonObj = $.parseJSON(jsonStr)
                    // console.log(jsonStr);
                    console.log(jsonObj.state);
                    if(jsonObj.state==1){//验证码正确
                        $('#vericode').next().html("验证码正确");
                    }else{//验证码错误
                        $('#vericode').next().html("验证码错误");
                    }
                }
            })
    });
});