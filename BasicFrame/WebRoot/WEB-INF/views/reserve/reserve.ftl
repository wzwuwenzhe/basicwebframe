<@htmlHeader>
	<@css path="/js/layui/css/layui.css"/>
	<@css path="/css/loginAndRegister/bill.css"/>
	<@js path="/js/layui/layui.js"/>
	<@js path="/js/map.js" />
<style type="text/css">
	.reserveTable, .reserveTable tr td {
		border:1px  solid black;
		width:300px;
		text-align:center;
	}
</style>
</@htmlHeader>
<@htmlBody>
	<form class="fh5co-form animate-box" id="reserveForm" action="./reserveSearch" method="post">
	<select id="schoolSelector" name="schoolId" onchange="changeSchoolId(this);"></select>
	<select id="classSelector" name="classId" onchange="$('#reserveForm').submit()"></select>
	<input type="button" value="查询" onclick="searchReserve();" class="btn btn-primary" />
	<input type="hidden" name="_token" id="_token" value="${_token}"/>
	<div class="form-group">
		<table class="reserveTable">
			<thead>
				<tr>
					<td style="width:25%">姓名</td>
					<td style="width:35%">电话</td>
					<td style="width:20%">状态</td>
					<td style="width:20%">操作</td>
				</tr>
			</thead>
			<tbody>
				<#list studentList as student>
					<tr>
						<td>${student.name}</td>
						<td>${student.phone}</td>
						<td>${student.stateDesc}</td>
						<td>
							<#assign applyType='${student.isApply}'/>
							<#assign payType='${student.isPay}'/>
							<#if applyType=="1" && payType=="0">
							<a href="javascript:void(0)" onclick="pay('${student.id}')" >支付</a>
							</#if>
						</td>
					</tr>
				</#list>
			</tbody>
		</table>
	</div>
	</form>
	<script type="text/javascript">
	
	var proposals = new Array();
	
	var map = new Map();
	var schoolIds = new Map();
	<#list classList as clazz>
		//去重
		if(false == schoolIds.get('${clazz.schoolId}')){
			schoolIds.put('${clazz.schoolId}',"1");
		}
		var Clazz = new Object();
		Clazz.id = '${clazz.id}';
		Clazz.name = '${clazz.name}';
		if(false == map.get('${clazz.schoolId}')){
			var clazzs = new Array();
			clazzs.push(Clazz);
			map.put('${clazz.schoolId}',clazzs);
		}else{
			var clazzs = map.get('${clazz.schoolId}');
			clazzs.push(Clazz);
			map.put('${clazz.schoolId}',clazzs);
		}
	</#list>
	
	$(document).ready(function(){
		for(var i=0;i<schoolIds.keys().length;i++){
			var key = schoolIds.keys()[i];
			$("#schoolSelector").append("<option value='"+key+"'>"+key+"</option>");
		}
		var schoolId = $("#schoolSelector").val();
		var clazzs = map.get(schoolId);
		for(var n=0 ;n<clazzs.length;n++){
			var clazz = clazzs[n];
			$("#classSelector").append("<option value='"+clazz.id+"'>"+clazz.name+"</option>");
		}
		var reqSchoolId = '${schoolId}';
		var classId = '${classId}';
		if(reqSchoolId!="" && classId!=""){
			$("#schoolSelector option[value='"+reqSchoolId+"']").attr("selected", true);
			$("#classSelector option[value='"+classId+"']").attr("selected", true);
		}
	});
	
	
	function searchReserve(){
		$("#reserveForm").submit();
	}
	
	function changeSchoolId(selector){
		var schoolId = $(selector).val();
		var clazzs = map.get(schoolId);
		for(var n=0 ;n<clazzs.length;n++){
			var clazz = clazzs[n];
			$("#classSelector").append("<option value='"+clazz.id+"'>"+clazz.name+"</option>");
		}
	}
	
	function pay(studentId){
	infoUtil.confirm("修改为已支付?",{
	        title:'选项确认',
	        closeBtn:0,
	        shift:-1,
	        btn: ['确定','取消'] //按钮
	    },function(_index){
	        infoUtil.close();
        	var _token=$("#_token").val();
			$.ajax({
					url:"./payed.htm",
					type:"POST",
					dataType:"json",
					data:{studentId:studentId,_token:_token},
					success:function(response){
						$("input[name='_token']").val(response.token);
						alert(response.message);
						$("#reserveForm").submit();
			        },
			        error:function(){
			            alert("系统错误,请联系管理员");
			        }
				});
	    },function(){});
	
	}
	</script>
</@htmlBody>
<@htmlFooter/>
	

