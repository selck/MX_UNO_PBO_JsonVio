<script type="text/javascript" src='<%= request.getContextPath() %>/resources/js/jquery-latest.js'></script>

<!--  
<script type="text/javascript" src='<%= request.getContextPath() %>/resources/js/bootstrap.min.js'></script>
-->

<link href="<%= request.getContextPath() %>/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
var guardaJSONServlet = "<%=response.encodeURL(request.getContextPath())%>" + "/guardaJSONServlet";
</script>

<script>
$(document).ready(function(){
    $("#enviar_json").click(function(){
         var json = jQuery("#text_area_json").val();
		 var error="";
			$("#text_area_json").removeClass("has-error");
			$("#div_error").html("");
			$("#div_error").removeClass("alert-danger");
		 if(json!=""){
				$.ajax({
						type: "post",
						dataType: "text",			
						url: guardaJSONServlet,
						data: {json : json},
				        async:false,
						success: function(data) {
							console.debug(data);
							if(data != "ERROR"){
								$("#div_error").addClass("alert-success");
								$("#text_area_json").val("");
								$("#div_error").html("JSON Actualizado!");
							}else{
								$("#div_error").html("Error al guardar JSON, favor de llamar a Fer");
								$("#div_error").addClass("alert-danger");
							}
						}
					});	
		 }else{
			
			$("#text_area_json").addClass("has-error");
			
			$("#div_error").html("Favor de poner un json");
			$("#div_error").addClass("alert-danger");
			
		 }
    });
});
</script>

  <label for="comment">Favor de ingresar json:</label>
  <div class="form-group">
    <span class="input-group-addon">JSON</span>
    <textarea id="text_area_json" name="text_area_json" class="form-control" rows="5" id="comment"></textarea>
  </div>
  
  <div id="div_error" class="alert" name="div_error"> </div>
  
  <div class="form-group-justified">
  <button type="button" class="btn btn-primary btn-md" id="enviar_json" name="enviar_json">Enviar JSON</button>
  </div>
  