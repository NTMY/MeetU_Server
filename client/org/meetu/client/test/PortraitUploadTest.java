package org.meetu.client.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.meetu.client.handler.PortraitHandler;
import org.meetu.client.listener.impl.PortraitUploadListenerImpl;
import org.meetu.client.util.HttpUtil;
import org.meetu.model.PortraitUploadModel;

public class PortraitUploadTest {
	public static void main(String[] args) {
		
		PortraitHandler handler = new PortraitHandler();
		
		PortraitUploadModel model = new PortraitUploadModel();
		model.setUserId("4");
		model.setFileName("1.jpg");
		model.setResolution("");
		
        byte[] bytes = null;
		try {
			bytes = HttpUtil.input2byte(new FileInputStream("D://meet_me.jpg"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.setFileBytes(bytes);
		handler.onUpload(new PortraitUploadListenerImpl(), model);
	}
}
