package com.uc.rest.core;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;

public class ResponseHandler {

	public void handleResponse(CloseableHttpResponse httpResponse,
			String filePath) throws Exception {
		if (httpResponse != null) {
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				writeToAFile(entity.getContent(), filePath);
			}
		}
	}

	private void writeToAFile(InputStream inputStream, String filePath) {
		BufferedWriter bw = null;
		FileWriter fw = null;
		BufferedInputStream bis = null;
		byte[] buffer = new byte[1024];
		int bytesRead = 0;
		
		try {
			fw = new FileWriter(filePath);
			bw = new BufferedWriter(fw);

			bis = new BufferedInputStream(inputStream);
			while ((bytesRead = bis.read(buffer)) != -1) {
				String chunk = new String(buffer, 0, bytesRead);
				bw.write(chunk);
			}
		} catch (IOException ioException) {
			System.out.println("Error in saving json , reason :"+ioException.getMessage());
		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
				if (bis != null)
					bis.close();
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
			}
		}
	}

}
