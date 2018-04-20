package com.uc.rest.core;

import org.apache.http.client.methods.CloseableHttpResponse;

public class RequesterThread implements Runnable {
	
	private ItemForTask payload  = null;
	
	public RequesterThread(ItemForTask payload) { 
		this.payload = payload;
	}

	public void run() {
		CloseableHttpResponse response = null;
		try {
			response = payload.getRequester()
					.addHeader("Authorization", payload.getP().getProperty("auth.header"))
					.addHeader("Api-Version", payload.getP().getProperty("api.version"))
					.addHeader("Cobrand-Name", payload.getP().getProperty("cobrand.name"))
					.forURL(payload.getP().getProperty("http.url")).request();
			
			//handle response
			StringBuilder sb = new StringBuilder();
			sb.append("Status :").append(response.getStatusLine().getStatusCode());
			new ResponseHandler().handleResponse(response,payload.getFilePath());
			response.close();
			sb.append("  ").append("Task "+payload.getTaskNo()+" is finished by "+Thread.currentThread().getName());
			System.out.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			payload.getLatch().countDown();
		}
		
	}

}
