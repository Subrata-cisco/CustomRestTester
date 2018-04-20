package com.uc.rest.core;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RequestorThreadManager {

	private ThreadPoolExecutor threadPoolExecuter = null;
	private BlockingQueue<Runnable> workQueue = null;
	private final static long THREAD_KEEP_ALIVE_TIME = 60L;
	private int tc = 10;
	
	public RequestorThreadManager(int threadCount) {
		this.tc = threadCount;
	}
	
	public void start() {
		workQueue = new LinkedBlockingQueue<>();
		threadPoolExecuter = new ThreadPoolExecutor(tc, tc, THREAD_KEEP_ALIVE_TIME, TimeUnit.SECONDS, workQueue);
	}
	
	public void addTask(RequesterThread task) {
		threadPoolExecuter.execute(task);
	}
	
	public void stop() {
		threadPoolExecuter.shutdownNow();
        threadPoolExecuter = null;
        workQueue.clear();
        workQueue = null;
	}
}
