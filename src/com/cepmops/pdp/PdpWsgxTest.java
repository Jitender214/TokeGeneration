package com.cepmops.pdp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Base64;
import java.net.URL;
import java.net.URLConnection;

public class PdpWsgxTest {

	    public static void main(String[] args) {

	        try {
	            String serviceurl ="http://wsgx.cisco.com/cepm/pdpservices/authorizationmanagerservice";
	            //String serviceurl = "http://cepm-cpdpws.cloudapps.cisco.com/rest/services/AuthorizationManagerServices/invoke";
	            String name = "cepm-ops.gen";
				String password = "A!j%js#6\r\n";
				String authString = name + ":" + password;
				String authEncBytes = Base64.getEncoder().encodeToString(authString.getBytes());
				String authStringEnc = new String(authEncBytes);
				System.out.println("Base64 encoded auth string: " + authStringEnc);
	            System.out.println("in try block");
	            URL url = new URL(serviceurl);
				URLConnection urlConnection = url.openConnection();
				urlConnection.setRequestProperty("Authorization", "Basic " + authStringEnc);
				InputStream is = urlConnection.getInputStream();
				InputStreamReader isr = new InputStreamReader(is);
				int numCharsRead;
				char[] charArray = new char[1024];
				StringBuffer sb = new StringBuffer();
				while ((numCharsRead = isr.read(charArray)) > 0) {
					sb.append(charArray, 0, numCharsRead);
				}
				String result = sb.toString();
				System.out.println("*** BEGIN ***");
				System.out.println(result);
				System.out.println("*** END ***");
	        } catch(Exception e) {
	            e.printStackTrace();
	        }

	    }

	}
