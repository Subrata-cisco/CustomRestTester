package com.uc.rest.core;

import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class ItemForTask {

	private IRequester requester;
	private Properties p;
	private String filePath;
	private CountDownLatch latch;
	private int taskNo = 0;

	public IRequester getRequester() {
		return requester;
	}

	public void setRequester(IRequester requester) {
		this.requester = requester;
	}

	public Properties getP() {
		return p;
	}

	public void setP(Properties p) {
		this.p = p;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public CountDownLatch getLatch() {
		return latch;
	}

	public void setLatch(CountDownLatch latch) {
		this.latch = latch;
	}

	public int getTaskNo() {
		return taskNo;
	}

	public void setTaskNo(int taskNo) {
		this.taskNo = taskNo;
	}
}
