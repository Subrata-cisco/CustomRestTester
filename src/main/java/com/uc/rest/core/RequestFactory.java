package com.uc.rest.core;

public class RequestFactory {

	public IRequester getRequestor(RequestType type) {
		IRequester requester = null;
		switch (type) {
		case GET:
			requester = new GetRequester();
			break;
		case POST:
			requester = new PostRequester();
			break;
		}
		return requester;
	}

}
