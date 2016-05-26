package utils;

import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

public class PrintTool {
	private static PrintService prtService ;
	
	public static void DoPrint(XPrinter58 xp58,PrinterParas paras) throws PrinterException
	{
		//ReadData();
		// ͨ���������顢�ĵ�
		Book book = new Book();
		// ���ó�����
		PageFormat pf = new PageFormat();
		pf.setOrientation(PageFormat.PORTRAIT); // LANDSCAPE��ʾ����;PORTRAIT��ʾ���;REVERSE_LANDSCAPE��ʾ��ӡ�հ�
		// ͨ��Paper����ҳ��Ŀհױ߾�Ϳɴ�ӡ���򡣱�����ʵ�ʴ�ӡֽ�Ŵ�С�����
		Paper p = new Paper();
		p.setSize(140,300); // ֽ�Ŵ�С(590, 840)��ʾA4ֽ
		p.setImageableArea(1, 3, 140, 300); // A4(595 X
		// 842)���ô�ӡ������ʵ0��0Ӧ����72��72
		// ����ΪA4ֽ��Ĭ��X,Y�߾���72

		pf.setPaper(p);
		// �� PageFormat �� Printable ��ӵ����У����һ��ҳ��
		book.append(new XPrinter58(paras), pf);
		//book.append(xp58, pf);
		// ��ȡ��ӡ�������
		PrinterJob job = PrinterJob.getPrinterJob();
		// �������Ƿ����58��ӡ��ʧ�ܣ�����ʾ���˳���
		if(!checkPrinter(1))
		{
			System.out.println("Not found Suitble XP-58 Printer");
			return;
		}
		job.setPrintService(prtService);
		// ���ô�ӡ��
		job.setPageable(book);
		try
		{
			// ������printDialog��ʾ��ӡ�Ի������û�ȷ�Ϻ��ӡ��Ҳ����ֱ�Ӵ�ӡ
			//boolean a = job.printDialog();
			//if (a)
			//{
		job.print();
			//}
		} catch (PrinterException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void DoPrint(XPrinter80 xp80,PrinterParas paras) throws PrinterException
	{
		//ReadData();
		// ͨ���������顢�ĵ�
		Book book = new Book();
		// ���ó�����
		PageFormat pf = new PageFormat();
		pf.setOrientation(PageFormat.PORTRAIT); // LANDSCAPE��ʾ����;PORTRAIT��ʾ���;REVERSE_LANDSCAPE��ʾ��ӡ�հ�
		// ͨ��Paper����ҳ��Ŀհױ߾�Ϳɴ�ӡ���򡣱�����ʵ�ʴ�ӡֽ�Ŵ�С�����
		Paper p = new Paper();
		p.setSize(240,400); // ֽ�Ŵ�С(590, 840)��ʾA4ֽ
		p.setImageableArea(1, 3, 240, 500); // A4(595 X
		// 842)���ô�ӡ������ʵ0��0Ӧ����72��72
		// ����ΪA4ֽ��Ĭ��X,Y�߾���72

		pf.setPaper(p);
		// �� PageFormat �� Printable ��ӵ����У����һ��ҳ��
		book.append(new XPrinter80(paras), pf);
		// ��ȡ��ӡ�������
		PrinterJob job = PrinterJob.getPrinterJob();
		// �������Ƿ����80��ӡ��ʧ�ܣ�����ʾ���˳���
		if(!checkPrinter(2))
		{
			System.out.println("Not found Suitble XP-80 Printer");
			return;
		}
		job.setPrintService(prtService);
		// ���ô�ӡ��
		job.setPageable(book);
		try
		{
			// ������printDialog��ʾ��ӡ�Ի������û�ȷ�Ϻ��ӡ��Ҳ����ֱ�Ӵ�ӡ
			//boolean a = job.printDialog();
			//if (a)
			//{
				job.print();
			//}
		} catch (PrinterException e)
		{
			e.printStackTrace();
		}
	}
	
	public static boolean checkPrinter(int type)
	{
		PrintService[] service = PrintServiceLookup.lookupPrintServices(null,null);                    
		for(int i=0;i<service.length;i++){                 
			//�ҵ����úõ������ӡ���������˲���XP-58������ӡ������ƥ��58�ַ���
			if(type==1)
			{
				if(service[i].getName().contains("58"))
				{
					System.out.println(service[i].getName()); 
					prtService = service[i];
					return true;
				}
			}
			if(type==2)//�ҵ����úõ������ӡ���������˲���XP-80������ӡ������ƥ��80�ַ���
			{
				if(service[i].getName().contains("80"))
				{
					System.out.println(service[i].getName()); 
					prtService = service[i];
					return true;
				}
			}
		}	
		return false;
	}
	
	public static void main(String[] args) throws PrinterException {	
		/*DoPrint(new XPrinter58());		*/	
	}
	
}
