<@htmlHeader>
	<@js path="/js/map.js" />
	<@js path="/js/msgCode/msgCode.js"/>
</@htmlHeader>
<@htmlBody>
	<@form action="/apply" onsubmit="return $form.submit(this,_loginCallback);" class="fh5co-form animate-box" h2="同学会聚餐报名">
	<@form_group id="name" value="" desc="真实姓名" type="text" name="name" dataType="Chinese" msg="姓名必须全部为中文" />
	<@form_group id="phone" value="" desc="手机号码" type="text" name="phone" dataType="Phone" msg="手机号码不正确" />
	<input type="hidden" id="classId" />
	<div>
		<@form_group id="vcode" value="" desc="验证码" type="text" name="vcode" dataType="Number" msg="验证码格式不正确" 
			style="width:200px;float:left" />
		<div style="margin-left: 220px;whith=200px;height:50px;padding-top: 14px;">
			<a id="send-msg-vcode" href="javascript:void(0)" >发送验证码</a>
		</div>
	</div>
	<div class="form-group" style="margin-right:40px;">
		<input type="submit" value="报名" class="btn btn-primary">
		<input type="button" value="返回" class="btn btn-primary back"  onclick="window.history.back()">
	</div>
	</@form>
	<@showMsg type="danger"/>
	<div id="payInfo" style="display:none">
		<img src="./image/payInfo/payInfo.jpg"  alt="2月13日,温州得尔乐(南塘店)同学会聚餐费,多退" />
	</div>
	<script type="text/javascript">
		<#if _token?exists>_token='${_token}';</#if>
		$("#name").focus(function(){
			$(this).val('');
		});
		$("#name").blur(function(){
			var name = $(this).val();
			name = name.replace(" ","");
			var classId = '${classId}';
			$("#classId").val(classId);
			$.ajax({
				url:"./getStudentByNameAndClassId.htm",
				type:"POST",
				dataType:"json",
				data:{classId:classId,name:name,_token:_token},
				success:function(response){
					$("input[name='_token']").val(response.token);
					if(response.success != true){
						alert(response.message);
					}						
		        },
		        error:function(){
		            alert("系统错误,请联系管理员");
		        }
			});
		});
		var btn = $("#send-msg-vcode");
    	var bindBtn = new bindBtn();
    	
    	var _loginCallback=function(response){
    		$("input[name='_token']").val(response.token);
			alert(response.message);
			if(response.success==true){
				window.setTimeout(function(){
					//打开弹框
		            layer.open({
		                type:1,
		                shift:-1,
		                title: '付款',
		                closeBtn:0,
		                area: ['310px','460px'],
		                content: $("#payInfo"),
		                btn:["关闭"],
		                cancel:function(){
		                }
		            });
				}, 2000);
			}
		}
	</script>
</@htmlBody>
<@htmlFooter/>
	

