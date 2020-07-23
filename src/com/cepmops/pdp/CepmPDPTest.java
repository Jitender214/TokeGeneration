package com.cepmops.pdp;

import org.apache.http.HttpEntity;
		import org.apache.http.auth.AuthScope;
		import org.apache.http.auth.UsernamePasswordCredentials;
		import org.apache.http.client.CredentialsProvider;
		import org.apache.http.client.methods.CloseableHttpResponse;
		import org.apache.http.client.methods.HttpGet;
		import org.apache.http.impl.client.BasicCredentialsProvider;
		import org.apache.http.impl.client.CloseableHttpClient;
		import org.apache.http.impl.client.HttpClientBuilder;
		import org.apache.http.util.EntityUtils;

		import java.io.IOException;

		public class CepmPDPTest {

		    public static void main(String[] args) throws IOException {

		        HttpGet request = new HttpGet("https://cepm-cpdpws.cloudapps.cisco.com/rest/services/AuthorizationManagerServices/invoke");
		        //HttpGet request = new HttpGet("http://cepm-cpdpws-stage.cloudapps.cisco.com/rest/services/AuthorizationManagerServices/invoke");

		        CredentialsProvider provider = new BasicCredentialsProvider();
		        provider.setCredentials(
		                AuthScope.ANY,
		                new UsernamePasswordCredentials("cepm-ops.gen", "A!j%js#6\\r\\n")
		        );

		        try (CloseableHttpClient httpClient = HttpClientBuilder.create()
		                .setDefaultCredentialsProvider(provider)
		                .build();
		             CloseableHttpResponse response = httpClient.execute(request)) {

		            // 401 if wrong user/password
		            System.out.println(response.getStatusLine().getStatusCode());

		            HttpEntity entity = response.getEntity();
		            if (entity != null) {
		                // return it as a String
		                String result = EntityUtils.toString(entity);
		                System.out.println(result);
		            }

		        }

		    }

}

