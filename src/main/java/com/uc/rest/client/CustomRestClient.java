package com.uc.rest.client;

import java.io.File;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import com.uc.rest.core.IRequester;
import com.uc.rest.core.ItemForTask;
import com.uc.rest.core.RequestFactory;
import com.uc.rest.core.RequestType;
import com.uc.rest.core.RequesterThread;
import com.uc.rest.core.RequestorThreadManager;

public class CustomRestClient {
	
	public static void main(String[] args) throws Exception {
		// Read property file.
		Properties p = readPropertyFile();
		
		//clean output dir
		cleanOutputDirectory(p.getProperty("output.dir"));
		
		// Send rest query.
		RequestType rType = RequestType.valueOf(p.getProperty("http.method"));
		IRequester requester = new RequestFactory().getRequestor(rType);
		
		RequestorThreadManager manager = new RequestorThreadManager(Integer.valueOf(p.getProperty("parallel.thread.count")));
		manager.start();
		
		int size = Integer.valueOf(p.getProperty("task.count").trim());
		CountDownLatch latch = new CountDownLatch(size);
		
		// introduce a delay
		Thread.sleep(1000L); 
		
		for(int i=0 ;i<size ;i++) {
			String filePath  = p.getProperty("output.dir")+ File.separator + "output"+(i+1)+".json" ;
			
			ItemForTask payload = new ItemForTask(); 
			payload.setRequester(requester);
			payload.setP(p);
			payload.setLatch(latch);
			payload.setFilePath(filePath);
			payload.setTaskNo(i+1);
			
			RequesterThread thread = new RequesterThread(payload);
			manager.addTask(thread);
		}
		
		latch.await();
		manager.stop();
		
		System.out.println("Done !!");
	}
	
	private static Properties readPropertyFile(){
		String fileName = "rest.properties";
		Properties prop = new Properties();
		InputStream input = null;
		try{
			input = CustomRestClient.class.getClassLoader().getResourceAsStream(fileName);
			prop.load(input);
		}catch(Exception e){
			throw new RuntimeException("Not able to read Property file :"+fileName);
		}
		//System.out.println(prop.getProperty("http.method"));
		return prop;
	}
	
	private static void cleanOutputDirectory(String path) {
		File file = new File(path);
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(File eachFile : files) {
				eachFile.delete();
			}
		}
	}

}
