package com.uc.rest.core;

import org.apache.http.client.methods.CloseableHttpResponse;

public interface IRequester {
	IRequester addHeader(String k,String val);
	IRequester forURL(String url);
	CloseableHttpResponse  request() throws Exception;
	void close();
}
