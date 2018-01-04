<@htmlHeader>
</@htmlHeader>
<@htmlBody>
	<@form action="/apply" onsubmit="return $form.submit(this,_loginCallback);" class="fh5co-form animate-box" h2="同学会聚餐报名">
	<@form_group id="name" value="" desc="真实姓名" type="text" name="name" dataType="Chinese" msg="姓名必须全部为中文" />
	<@form_group id="phone" value="" desc="手机号码" type="text" name="phone" dataType="Phone" msg="手机号码不正确" />
	<@form_group id="vcode" value="" desc="验证码" type="text" name="vcode" dataType="Number" msg="验证码格式不正确" />
	<div class="form-group">
		<input type="submit" value="报名" class="btn btn-primary">
		<input type="button" value="返回" class="btn btn-primary back"  onclick="window.history.back()">
	</div>
	</@form>
	<@showMsg type="danger"/>
	<script type="text/javascript">
		<#if _token?exists>_token='${_token}';</#if>
	
	</script>
</@htmlBody>
<@htmlFooter/>
	

