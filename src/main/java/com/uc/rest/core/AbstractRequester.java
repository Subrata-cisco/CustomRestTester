package com.uc.rest.core;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public abstract class AbstractRequester implements IRequester {
	
	protected CloseableHttpClient client = null;
	protected Map<String,String> headers = new HashMap<String,String>();
	protected String url = null;
	
	public AbstractRequester(){
		client = HttpClients.createDefault();
	}
	
	public IRequester addHeader(String k, String val) {
		headers.put(k, val);
		return this;
	}
	
	public IRequester forURL(String url) {
		this.url = url;
		return this;
	}
	
	public void close() {
		if(client != null){
			try {
				client.close();
			} catch (IOException e) {
				// nothing
			}
		}
	}

}
