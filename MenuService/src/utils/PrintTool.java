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
		// 通俗理解就是书、文档
		Book book = new Book();
		// 设置成竖打
		PageFormat pf = new PageFormat();
		pf.setOrientation(PageFormat.PORTRAIT); // LANDSCAPE表示竖打;PORTRAIT表示横打;REVERSE_LANDSCAPE表示打印空白
		// 通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
		Paper p = new Paper();
		p.setSize(140,300); // 纸张大小(590, 840)表示A4纸
		p.setImageableArea(1, 3, 140, 300); // A4(595 X
		// 842)设置打印区域，其实0，0应该是72，72
		// ，因为A4纸的默认X,Y边距是72

		pf.setPaper(p);
		// 把 PageFormat 和 Printable 添加到书中，组成一个页面
		book.append(new XPrinter58(paras), pf);
		//book.append(xp58, pf);
		// 获取打印服务对象
		PrinterJob job = PrinterJob.getPrinterJob();
		// 如果检查是否存在58打印机失败，则提示并退出。
		if(!checkPrinter(1))
		{
			System.out.println("Not found Suitble XP-58 Printer");
			return;
		}
		job.setPrintService(prtService);
		// 设置打印类
		job.setPageable(book);
		try
		{
			// 可以用printDialog显示打印对话框，在用户确认后打印；也可以直接打印
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
		// 通俗理解就是书、文档
		Book book = new Book();
		// 设置成竖打
		PageFormat pf = new PageFormat();
		pf.setOrientation(PageFormat.PORTRAIT); // LANDSCAPE表示竖打;PORTRAIT表示横打;REVERSE_LANDSCAPE表示打印空白
		// 通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
		Paper p = new Paper();
		p.setSize(240,400); // 纸张大小(590, 840)表示A4纸
		p.setImageableArea(1, 3, 240, 500); // A4(595 X
		// 842)设置打印区域，其实0，0应该是72，72
		// ，因为A4纸的默认X,Y边距是72

		pf.setPaper(p);
		// 把 PageFormat 和 Printable 添加到书中，组成一个页面
		book.append(new XPrinter80(paras), pf);
		// 获取打印服务对象
		PrinterJob job = PrinterJob.getPrinterJob();
		// 如果检查是否存在80打印机失败，则提示并退出。
		if(!checkPrinter(2))
		{
			System.out.println("Not found Suitble XP-80 Printer");
			return;
		}
		job.setPrintService(prtService);
		// 设置打印类
		job.setPageable(book);
		try
		{
			// 可以用printDialog显示打印对话框，在用户确认后打印；也可以直接打印
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
			//找到配置好的网络打印机，厨房端采用XP-58热敏打印机，故匹配58字符串
			if(type==1)
			{
				if(service[i].getName().contains("58"))
				{
					System.out.println(service[i].getName()); 
					prtService = service[i];
					return true;
				}
			}
			if(type==2)//找到配置好的网络打印机，厨房端采用XP-80热敏打印机，故匹配80字符串
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
