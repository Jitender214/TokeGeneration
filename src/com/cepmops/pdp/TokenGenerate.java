package com.cepmops.pdp;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.MediaType;

public class TokenGenerate {
	private static final Logger LOG = Logger.getLogger(TokenGenerate.class);
	public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
	static OkHttpClient client = new OkHttpClient();
	
	public JSONObject getToken() {        
		JSONObject jsonReq = null;
		Response response = null;
		/*
		 * String URL =
		 * PropertiesUtil.getProperty("TOKEN_URL")+"grant_type="+PropertiesUtil.
		 * getProperty("GRANT_TYPE")
		 * +"&client_id="+PropertiesUtil.getProperty("TOKEN_USER_ID")+"&client_secret="+
		 * PropertiesUtil.getProperty("TOKEN_PASSWORD");
		 */
		
		String URL = "https://cloudsso-test.cisco.com/as/token.oauth2?"+"grant_type="+"client_credentials"
		+"&client_id="+"cepm-ops_gen_stg"+"&client_secret="+"Asjdsbkj!2309df%2307sj";
		
		LOG.info("URL is  "+URL);
		try {
			client = new OkHttpClient();
			RequestBody body = RequestBody.create("{}", JSON); 
			Request request = new Request.Builder()
					.url(URL)
			        .method("POST", body)
			        .build();
			response = client.newCall(request).execute();
			
			jsonReq = new JSONObject(response.body().string());
			
			}
			catch (final Exception e) {
				LOG.error(e.getMessage(), e);
				
			} finally {
				if (response != null) {
					(response).close();
				}
			}
		LOG.info("json req is "+jsonReq);	
		return jsonReq;
		}
	}