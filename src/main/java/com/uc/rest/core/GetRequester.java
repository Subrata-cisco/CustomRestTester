package com.uc.rest.core;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

public class GetRequester extends AbstractRequester {
	
	private HttpGet httpGet = null;
	
	public IRequester addHeader(String k, String val) {
		super.addHeader(k, val);
		return this;
	}

	public IRequester forURL(String url) {
		super.forURL(url);
		return this;
	}

	public CloseableHttpResponse request() throws Exception {
		httpGet = new HttpGet(url);
		for (String key : headers.keySet()) {
			httpGet.addHeader(key, headers.get(key));
		}
		return client.execute(httpGet);
	}
	
	public void close() {
		super.close();
		httpGet.completed();
		httpGet = null;
	}

}
