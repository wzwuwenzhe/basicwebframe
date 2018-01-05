         
    // 定义发送时间间隔(s)
    var SEND_INTERVAL = 60;
    var timeLeft = SEND_INTERVAL;
    
    /**
    * 绑定btn按钮的监听事件
    */
    var bindBtn = function(phone){
        btn.click(function(){
            // 需要先禁用按钮（为防止用户重复点击）
            btn.attr('disabled', 'disabled');
            if(!Validator.Phone.test(phone)){
            	alert("请输入正确的手机号码!");
            	reset();
            	return;
            }
            $.ajax({
                
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
                btn.html(timeLeft + "后重新发送");
                timeCount();
            } else {
                btn.html("重新发送");
                bindBtn();
            }
        }, 1000);
    }
    