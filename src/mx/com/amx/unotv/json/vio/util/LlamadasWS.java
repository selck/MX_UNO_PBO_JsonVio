package mx.com.amx.unotv.json.vio.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class LlamadasWS {
	private final Logger logger = Logger.getLogger(this.getClass().getName());
	private final Properties props = new Properties();
	private RestTemplate restTemplate;
	private String URL_WS_BASE="";
	private HttpHeaders headers = new HttpHeaders();
	
	public LlamadasWS() {
		super();
		restTemplate = new RestTemplate();
		ClientHttpRequestFactory factory = restTemplate.getRequestFactory();

	        if ( factory instanceof SimpleClientHttpRequestFactory)
	        {
	            ((SimpleClientHttpRequestFactory) factory).setConnectTimeout( 15 * 1000 );
	            ((SimpleClientHttpRequestFactory) factory).setReadTimeout( 15 * 1000 );
	            //logger.info("Inicializando rest template");
	        }
	        else if ( factory instanceof HttpComponentsClientHttpRequestFactory)
	        {
	            ((HttpComponentsClientHttpRequestFactory) factory).setReadTimeout( 15 * 1000);
	            ((HttpComponentsClientHttpRequestFactory) factory).setConnectTimeout( 15 * 1000);
	            //logger.info("Inicializando rest template");
	        }
	        restTemplate.setRequestFactory( factory );
	        headers.setContentType(MediaType.APPLICATION_JSON);
	        
	        try {
				props.load( this.getClass().getResourceAsStream( "/general.properties" ) );						
			} catch(Exception e) {
				logger.error("[ConsumeWS:init]Error al iniciar y cargar arhivo de propiedades." + e.getMessage());
			}		
			URL_WS_BASE = props.getProperty(props.getProperty( "ambiente" )+".urlws");
	}
	
	
	public String saveJSON(String jsonRecibido) {
		
		String metodo="saveJSONVio";
		String URL_WS=URL_WS_BASE+metodo;
		String respuesta="";
		try {
			logger.info("URL_WS: "+URL_WS);
			HttpEntity<String> entity = new HttpEntity<String>( jsonRecibido );
			respuesta = restTemplate.postForObject(URL_WS,entity, String.class);
			
		} catch(Exception e) {
			logger.error("Error saveJSON [LlamadasWS]: ",e);
		}		
		return respuesta;	
	}
	


}
