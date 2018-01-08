         
    // 定义发送时间间隔(s)
    var SEND_INTERVAL = 60;
    var timeLeft = SEND_INTERVAL;
    
    /**
    * 绑定btn按钮的监听事件
    */
    var bindBtn = function(){
        btn.click(function(){
            // 需要先禁用按钮（为防止用户重复点击）
            btn.attr('disabled', 'disabled');
            var phone = $("#phone").val();
            var name = $("#name").val();
            if(!Validator.Phone.test(phone)){
            	alert("请输入正确的手机号码!");
            	reset();
            	return;
            }
            $.ajax({
            	url:"./sendCodeMsg.htm",
				type:"POST",
				dataType:"json",
				data:{classId:$("#classId").val(),name:name,phone:phone,_token:_token},
				success:function(response){
					$("input[name='_token']").val(response.token);
					alert(response.message);
					if(response.data=="not_pay"){
						//打开弹框
			            layer.open({
			                type:1,
			                shift:-1,
			                title: '长按,识别图中二维码付款',
			                closeBtn:0,
			                area: ['280px','480px'],
			                content: $("#payInfo"),
			                btn:["关闭"],
			                cancel:function(){
			                }
			            });
					}
		        },
		        error:function(){
		            alert("系统错误,请联系管理员");
		        }
                
            })
            .done(function () {
                timeLeft = SEND_INTERVAL;
                timeCount();                
            })
            .fail(function () {
                //失败，弹窗
                alert('发送失败');
                reset();
            });
        });
        
    }           
    
    var reset=function(){
        btn.removeAttr('disabled');
    }
    /**
    * 重新发送计时
    **/ 
    
    var timeCount = function() {
        window.setTimeout(function() {
            if(timeLeft > 0) {
                timeLeft -= 1;
                btn.val(timeLeft + "秒后重新发送");
                timeCount();
            } else {
            	reset();
                btn.val("重新发送");
            }
        }, 1000);
    }
    