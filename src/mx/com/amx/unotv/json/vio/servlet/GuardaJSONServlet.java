package mx.com.amx.unotv.json.vio.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.com.amx.unotv.json.vio.util.LlamadasWS;

import org.apache.log4j.Logger;


/**
 * Servlet implementation class GuardaJSONServlet
 */
public class GuardaJSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger=Logger.getLogger(GuardaJSONServlet.class);
	
    public GuardaJSONServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("Intento de petición Get");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.info("doPost...");
		String respuesta="";
		LlamadasWS llamadasWS=new LlamadasWS();
		String jsonRecibido=request.getParameter("json")==null?"":request.getParameter("json");
		try {
			logger.info("json recibido: "+jsonRecibido);
			respuesta=llamadasWS.saveJSON(jsonRecibido);
			logger.debug("Respuesta: "+respuesta);
			response.setContentType("text/html");  
			PrintWriter out = response.getWriter();  
			out.println(respuesta);  
		} catch (Exception e) {
			logger.error("Error GuardaJSONServlet: ",e);
		}
	}
}
