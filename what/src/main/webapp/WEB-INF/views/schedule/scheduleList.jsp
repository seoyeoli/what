<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<!-- js, 부트스트랩 등등 include -->
	<%@ include file="/WEB-INF/views/common/comm.jsp" %>
	<script type="text/javascript">
		//캘린더 설정& 선택 시 다른곳에 값 세팅..
		$(document).ready(function() {
			flatpickr("#myDatePicker", {
				"allowInput" : true,
				"inline" : true, 
				"onOpen" : function(selectedDates, dateStr, instance) {
					instance.setDate(instance.input.value, false);
				},
				"defaultDate" : "today",
				"onChange" : function(){
					//>>>>캘린더 변경 이벤트 START
					document.getElementById("selDay").innerHTML = document.getElementById("myDatePicker").value;
					var data = {"user_id":document.getElementById("topuserid").value, 
								"sch_day":document.getElementById("selDay").innerText};
					//Ajax로 날짜 선택 시 해당 일자 스케쥴 불러오기
					$.ajax({
						url:"/schedule/daySchedule.do",
						type:"POST",
						dataType:"json",
						contentType:"application/json",
						data:JSON.stringify(data),
						success:function(data){
							
							var tab = document.getElementById("scheTable");
							var inhtml = '';
							
							$.each(data,function(key,value){
								//alert(value['SCH_ID']);
								if(value['SCH_ID'] != null){
									inhtml = inhtml + "<tr> <td>"+value['SCH_TIME']+"</td><td>"+value['PROG_REMARK']+"</td></tr>" ;
								}
							});
							
							tab.innerHTML = inhtml;
						},
						error:function(){
							alert("잠시 후 다시 시도해주세요."); 
						}
					});
					//<<<<캘린더 변경 이벤트 END
				}
			});
			
			//>>>>문서 로딩 시 오늘 날짜에 대한 스케줄 세팅 START
			document.getElementById("selDay").innerHTML = document.getElementById("myDatePicker").value;
			var list = ${jsonData};
			if(list != null){  
				var tab = document.getElementById("scheTable");
				var inhtml = '';
				
				$.each(list,function(key, value){
					if(value['SCH_ID'] != null){
						inhtml = inhtml + "<tr> <td>"+value['SCH_TIME']+"</td><td>"+value['PROG_REMARK']+"</td></tr>" ;
					}
				});
				tab.innerHTML = inhtml;
			}
			//<<<<문서 로딩 시 오늘 날짜에 대한 스케줄 세팅END
			
		});
		
		
		
	</script>
</head>
<body>
	<%@ include file="/WEB-INF/views/common/top.jsp" %>
	
	<div class="wrap">
	<div class="container">
	<div class="row">
		<div class="col-md-5" align="center">
			<input type="text" class="selector" id="myDatePicker" placeholder="날짜를 선택하세요." hidden="true"/>
		</div>
		<div class="col-md-7" style="padding-left:50px;padding-top:20px ">
			<h2 id="selDay"></h2>
			<br>
			<table class="table" id="scheTable"> 
			</table>
		</div>
	</div>
	</div> 
	</div>
	
	
	
</body>
</html>
