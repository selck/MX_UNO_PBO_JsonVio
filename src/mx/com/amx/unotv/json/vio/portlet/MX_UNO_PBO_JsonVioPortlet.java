package mx.com.amx.unotv.json.vio.portlet;

import java.io.*;
import javax.portlet.*;

import org.apache.log4j.Logger;

public class MX_UNO_PBO_JsonVioPortlet extends javax.portlet.GenericPortlet {
	
	private Logger logger=Logger.getLogger(MX_UNO_PBO_JsonVioPortlet.class);
	
	public void init() throws PortletException{
		super.init();
	}

	public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
		logger.debug("===== doView =====");
		String dispatch=(String) (request.getPortletSession().getAttribute("dispatch")==null || request.getPortletSession().getAttribute("dispatch").equals("")?"inicio":request.getPortletSession().getAttribute("dispatch"));
		try {
			logger.debug("dispatch: "+dispatch);
			
			
			dispatch="/resources/jsp/violeta.jsp";
			
			logger.debug("Redirigiendo a: "+dispatch);
			response.setContentType(request.getResponseContentType());
			PortletRequestDispatcher rd = getPortletContext().getRequestDispatcher(dispatch);
			rd.include(request,response);
		} catch (Exception e) {
			logger.error("Error DoView: ",e);
		}

	}

	public void processAction(ActionRequest request, ActionResponse response) throws PortletException, java.io.IOException {
		
	}

}
