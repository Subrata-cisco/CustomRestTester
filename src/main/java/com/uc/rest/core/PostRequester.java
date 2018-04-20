package com.uc.rest.core;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;

public class PostRequester extends AbstractRequester {
	
	private HttpPost httpPost = null;

	public IRequester addHeader(String k, String val) {
		super.addHeader(k, val);
		return this;
	}

	public IRequester forURL(String url) {
		super.forURL(url);
		return this;
	}

	public CloseableHttpResponse request() throws Exception {
		httpPost = new HttpPost(url);
		for (String key : headers.keySet()) {
			httpPost.addHeader(key, headers.get(key));
		}
		return client.execute(httpPost);
	}
	
	public void close() {
		super.close();
		httpPost.completed();
		httpPost = null;
	}
}
