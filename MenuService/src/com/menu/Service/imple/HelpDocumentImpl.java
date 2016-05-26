package com.menu.Service.imple;

import java.io.IOException;

import com.menu.Service.in.HelpDocumentIn;


public class HelpDocumentImpl implements HelpDocumentIn{

	/**
	 * ´ò¿ª°ïÖúÎÄµµ
	 */
	public void OpenHelpDocument() {
		// TODO Auto-generated method stub
		String filePath = "D:\\Program Files\\MyEclipse\\MyEclipse 10\\e\\Workspaces\\MyEclipse 10\\VehicleTrackingAndNavigationServer\\WebRoot\\admin\\help";
		Runtime runtime=Runtime.getRuntime();
		String[] commandArgs={"C:\\Program Files\\Microsoft Office\\OFFICE11\\WINWORD.EXE",filePath + "\\helpDocument.doc"}; 
		
		try {
			runtime.exec(commandArgs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
