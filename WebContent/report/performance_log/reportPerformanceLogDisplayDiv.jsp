<%-- <%@page import="file.FileTypeConstants"%> --%>
<div class="portlet light">
	<div class="portlet-title">
		<div class="caption">
			<i class="fa fa-tv"></i>Display
		</div>
	</div>
	<!-- /.box-header -->
	<div class="portlet-body form" style="height: 30vh; overflow-x: hidden; overflow-y:  scroll;">
		<div class="form-body">
		
			<div class="form-group">
				<div class="col-md-5">
					<label class="checkbox"><span><input type="checkbox" class="input-checkbox-display" value="2"></span>Username</label>
				</div>
				<div class="col-md-5">
					<input type="text" class="display-input" name="display.user.userName" value="Username" disabled
					data-operator="like" data-comment="">
				</div>
				<div class="col-md-2" style="position: relative;"><%@include file="../upDownArrow.jsp"%></div>
			</div>
			
			<div class="form-group">
				<div class="col-md-5">
					<label class="checkbox"><span><input type="checkbox" class="input-checkbox-display" value="3"></span>URI</label>
				</div>
				<div class="col-md-5">
					<input type="text" class="display-input" name="display.PerformanceLog.URI" value="URI" disabled
					data-operator="like" data-comment="">
				</div>
				<div class="col-md-2" style="position: relative;"><%@include file="../upDownArrow.jsp"%></div>
			</div>
			
			
			<div class="form-group">
				<div class="col-md-5">
					<label class="checkbox"><span><input type="checkbox" class="input-checkbox-display" value="13"></span>IP Address</label>
				</div>
				<div class="col-md-5">
					<input type="text" class="display-input" name="display.PerformanceLog.ipAddress" value="IP Address" disabled
					data-operator="like" data-comment="">
				</div>
				<div class="col-md-2" style="position: relative;"><%@include file="../upDownArrow.jsp"%></div>
			</div>
			
			<div class="form-group">
				<div class="col-md-5">
					<label class="checkbox"><span><input type="checkbox" class="input-checkbox-display" value="14"></span>Total Service Time</label>
				</div>
				<div class="col-md-5">
					<input type="text" class="display-input" name="display.PerformanceLog.totalServiceTime" value="Total Service Time" disabled
					data-operator="geq" data-comment="">
				</div>
				<div class="col-md-2" style="position: relative;"><%@include file="../upDownArrow.jsp"%></div>
			</div>
			<div class="form-group">
				<div class="col-md-5">
					<label class="checkbox"><span><input type="checkbox" class="input-checkbox-display" value="15"></span>Request Time</label>
				</div>
				<div class="col-md-5">
					<input type="text" class="display-input" name="display.PerformanceLog.requestTime" value="Request Time" disabled
					data-operator="geq" data-comment="datepicker">
				</div>
				<div class="col-md-2" style="position: relative;"><%@include file="../upDownArrow.jsp"%></div>
			</div>
		</div>
	</div>
	<!-- /.box-body -->
</div>
