package com.web.action;


import com.menu.Service.imple.HelpDocumentImpl;
import com.menu.Service.in.HelpDocumentIn;
import com.opensymphony.xwork2.ActionSupport;

public class HelpAction extends ActionSupport {
	
	public String openHelpDocument()
	{
		HelpDocumentIn helpDocument = new HelpDocumentImpl();
		helpDocument.OpenHelpDocument();
		
		return null;
		
	}

}
