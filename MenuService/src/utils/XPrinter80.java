package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Date;

import javax.print.PrintService;
import javax.print.PrintServiceLookup; 

public class XPrinter80 implements Printable
{
	private PrinterParas paras = new PrinterParas();
	
	private static PrintService prtService ;
	/**
	 *   * @param Graphicָ����ӡ��ͼ�λ���   * @param
	 * PageFormatָ����ӡҳ��ʽ��ҳ���С�Ե�Ϊ������λ��1��Ϊ1Ӣ���1/72��1Ӣ��Ϊ25.4���ס�A4ֽ����Ϊ595��842�㣩   * @param
	 * pageIndexָ��ҳ��
	 **/
	public XPrinter80()
	{
		paras = new PrinterParas();
		paras.setName("�ڸ�����");
		paras.setCashier("001");
		paras.setPrinter("001");
		paras.setGuestNumbers("10");
		paras.setOrderNumber("20140201");
		paras.setTableName("����01");
		paras.setPaperName("���˵�-������");
		paras.setWaiter("����");
		paras.setSerialnumber("20140201");
		
		String[][] menus={
				{"��������","1","28.00"},
				{"��������","1","28.00"},
				{"��������","1","28.00"}
		};
		paras.setMenuList(menus);
	}
	
	public XPrinter80(PrinterParas _paras)
	{
		this.paras = _paras;
	}
	public int print(Graphics gra, PageFormat pf, int pageIndex)
			throws PrinterException
	{		
		Component c = null;	
		// ת����Graphics2D
		Graphics2D g2 = (Graphics2D) gra;
		// ���ô�ӡ��ɫΪ��ɫ
		g2.setColor(Color.red);
		// ��ӡ�������
		double x = pf.getImageableX();
		double y = pf.getImageableY();
		switch (pageIndex)
		{
		case 0:
			// ���ô�ӡ���壨�������ơ���ʽ�͵��С�����������ƿ�������������߼����ƣ�
			// Javaƽ̨���������������ϵ�У�Serif��SansSerif��Monospaced��Dialog �� DialogInput
			Font font = new Font("������", Font.PLAIN, 10);
			g2.setFont(font); // ��������
			// BasicStroke bs_3=new BasicStroke(0.5f);
			float[] dash1 =
			{ 2.0f };
			// ���ô�ӡ�ߵ����ԡ�
			// 1.�߿� 2��3����֪����4���հ׵Ŀ�ȣ�5�����ߵĿ�ȣ�6��ƫ����
			g2.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER, 2.0f, dash1, 0.0f));
			// g2.setStroke(bs_3);//�����߿�
			float heigth = font.getSize2D(); // ����߶�
			System.out.println("x=" + x);
			// -1- ��Graphics2Dֱ�����	
			/////////////////////////////////////////////////////////////////////
			int lineHeight = 12;
			int sX = 1; 
			int sY = 15;
			int num = 1;
			//L1:��ӡ��������
			int strWidth = g2.getFontMetrics().stringWidth(paras.getName());
			g2.drawString(paras.getName(),sX+(180-strWidth)/2,sY);
			//L2:��ӡ��������
			strWidth = g2.getFontMetrics().stringWidth(paras.getPaperName());
			g2.drawString(paras.getPaperName(),sX+(180-strWidth)/2,sY+lineHeight*(++num));
			//L3:��ӡ����
			g2.drawLine(sX,sY+lineHeight*(++num),400,sY+lineHeight*(num));
			//L4:��ӡ����������Ϣ
			g2.drawString("̨��:"+paras.getTableName(),sX,sY+lineHeight*(++num));
			g2.drawString("������"+paras.getGuestNumbers(),sX+120,sY+lineHeight*(num));
			/*��ͬһ�У��ָ���*/
			g2.drawString("����:"+paras.getOrderNumber(),sX,sY+lineHeight*(++num));
			g2.drawString("����Ա:"+paras.getWaiter(),sX+120,sY+lineHeight*(num));
			/*��ͬһ�У��ָ���*/
			g2.drawString("��ˮ�ţ�"+paras.getSerialnumber(),sX,sY+lineHeight*(++num));
			g2.drawLine(sX,sY+lineHeight*(++num),400,sY+lineHeight*(num));
			g2.drawString("ʱ�䣺"+new Date().toLocaleString(),sX,sY+lineHeight*(++num));
			g2.drawLine(sX,sY+lineHeight*(++num),400,sY+lineHeight*(num));
			g2.drawString("����             ����             ����",sX,sY+lineHeight*(++num));
			/////////////////////////////////////////////////////////////////////
			String[][] list = paras.getMenuList();
			for(int i=0;i<paras.getMenuList().length;i++)
			{
				/*g2.drawString(list[i][0]+"         "+list[i][1]+"          "+list[i][2],sX,sY+lineHeight*(++num));*/
				g2.drawString(list[i][0],sX,sY+lineHeight*(++num));
				g2.drawString(list[i][1],sX+100,sY+lineHeight*(num));
				g2.drawString(list[i][2],sX+170,sY+lineHeight*(num));
			}
			/*����*/
			g2.drawString("",sX,sY+lineHeight*(++num));
			g2.drawString("�ۿ���:"+paras.getDiscountRate(),sX,sY+lineHeight*(++num));
			g2.drawString("���ѽ��:"+paras.getTotalmoney(),sX,sY+lineHeight*(++num));
			g2.drawLine(sX,sY+lineHeight*(++num),400,sY+lineHeight*(num));
			g2.drawString("����Ա:"+paras.getCashier()+"      ��ӡԱ:"+paras.getPrinter(),sX,sY+lineHeight*(++num));
			return PAGE_EXISTS;
		default:
			return NO_SUCH_PAGE;
		}
	}

	/*public static void DoPrint() throws PrinterException
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
		book.append(new XPrinter80(), pf);
		// ��ȡ��ӡ�������
		PrinterJob job = PrinterJob.getPrinterJob();
		// �������Ƿ����58��ӡ��ʧ�ܣ�����ʾ���˳���
		if(!checkPrinter())
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
	*/
	public static boolean checkPrinter()
	{
		PrintService[] service = PrintServiceLookup.lookupPrintServices(null,null);                    
		for(int i=0;i<service.length;i++){                 
			//�ҵ����úõ������ӡ���������˲���XP-58������ӡ������ƥ��58�ַ���
			if(service[i].getName().contains("80"))
			{
				System.out.println(service[i].getName()); 
				prtService = service[i];
				return true;
			}
		}	
		return false;
	}
	public static void main(String[] args) throws PrinterException {	
		/*DoPrint();*/			
	}
}