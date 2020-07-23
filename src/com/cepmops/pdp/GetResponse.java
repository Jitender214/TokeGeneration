package com.cepmops.pdp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;



import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GetResponse {
	
	private static final Logger LOG = Logger.getLogger(GetResponse.class);
	public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
	JSONObject JSONres = null;
	static OkHttpClient client = new OkHttpClient();
	

	public JSONObject responseJSONFromPojoERPCR(String JSONreqString) {
		Response response = null;
		JSONObject jsonReq = null;
		String URL = "https://cepm-cpdpws-stage.cloudapps.cisco.com/rest/services/AuthorizationManagerServices/invoke";
		TokenGenerate tokenGenerate = new TokenGenerate();
		JSONObject token = tokenGenerate.getToken();

		try {
			LOG.info("JSONreqString -----------  "+JSONreqString);
			LOG.info("URL is -----------  "+URL);
			LOG.info("token is -----------  "+token);
			client = new OkHttpClient();
			RequestBody body = RequestBody.create(JSONreqString, JSON);
			Request request = new Request.Builder()
			        .url(URL)
			        .method("POST", body)
			        .addHeader("Authorization", token.getString("token_type")+" "+token.getString("access_token"))
			        .addHeader("Content-Type", "application/json")
			        .build();
			response = client.newCall(request).execute();
			/*
			 * Don't use response.body().string() more than once because as per the okhttp3, response body can be consumed only once. if it is required to
			 * use one more, better to create a local variable body and use that. access specifier public can make it to use outside the class as well
			*/
			jsonReq = new JSONObject(response.body().string());
			//System.out.println(response.body().toString());
			System.out.println(jsonReq);
			
			return jsonReq;
			
			}
			catch (final Exception e) {
				LOG.error(e.getMessage(), e);
				
			} finally {
				if (response != null) {
					(response).close();
				}
			}
		return jsonReq;
			
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String JSONreqString = "{\r\n" + 
				"    \"methodType\": \"RESOURCE_ROLEBUNDLES_CONTEXT_MAP\",\r\n" + 
				"    \"methodName\": \"getUsersAllowedForResource\",\r\n" + 
				"    \"resourceFQN\": \"Commerce Tools:Capture Order:TS1DMP:Return Order (ITDS Only)\",\r\n" + 
				"    \"actions\": { \"action\": [\"any\"] },\r\n" + 
				"    \"context\": \"Global Context:Global Context\",\r\n" + 
				"    \"level\": \"-1\",\r\n" + 
				"    \"roleBundles\": { \"roleBundle\": [\"Default\"] }\r\n" + 
				"}";
		GetResponse response = new GetResponse();
		response.responseJSONFromPojoERPCR(JSONreqString);
		
	}

}
