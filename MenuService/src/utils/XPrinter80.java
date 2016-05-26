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
	 *   * @param Graphic指明打印的图形环境   * @param
	 * PageFormat指明打印页格式（页面大小以点为计量单位，1点为1英寸的1/72，1英寸为25.4毫米。A4纸大致为595×842点）   * @param
	 * pageIndex指明页号
	 **/
	public XPrinter80()
	{
		paras = new PrinterParas();
		paras.setName("在富餐厅");
		paras.setCashier("001");
		paras.setPrinter("001");
		paras.setGuestNumbers("10");
		paras.setOrderNumber("20140201");
		paras.setTableName("大厅01");
		paras.setPaperName("结账单-厨房联");
		paras.setWaiter("张三");
		paras.setSerialnumber("20140201");
		
		String[][] menus={
				{"宫保鸡丁","1","28.00"},
				{"宫保鸡丁","1","28.00"},
				{"宫保鸡丁","1","28.00"}
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
		// 转换成Graphics2D
		Graphics2D g2 = (Graphics2D) gra;
		// 设置打印颜色为黑色
		g2.setColor(Color.red);
		// 打印起点坐标
		double x = pf.getImageableX();
		double y = pf.getImageableY();
		switch (pageIndex)
		{
		case 0:
			// 设置打印字体（字体名称、样式和点大小）（字体名称可以是物理或者逻辑名称）
			// Java平台所定义的五种字体系列：Serif、SansSerif、Monospaced、Dialog 和 DialogInput
			Font font = new Font("新宋体", Font.PLAIN, 10);
			g2.setFont(font); // 设置字体
			// BasicStroke bs_3=new BasicStroke(0.5f);
			float[] dash1 =
			{ 2.0f };
			// 设置打印线的属性。
			// 1.线宽 2、3、不知道，4、空白的宽度，5、虚线的宽度，6、偏移量
			g2.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_BUTT,
					BasicStroke.JOIN_MITER, 2.0f, dash1, 0.0f));
			// g2.setStroke(bs_3);//设置线宽
			float heigth = font.getSize2D(); // 字体高度
			System.out.println("x=" + x);
			// -1- 用Graphics2D直接输出	
			/////////////////////////////////////////////////////////////////////
			int lineHeight = 12;
			int sX = 1; 
			int sY = 15;
			int num = 1;
			//L1:打印餐厅名称
			int strWidth = g2.getFontMetrics().stringWidth(paras.getName());
			g2.drawString(paras.getName(),sX+(180-strWidth)/2,sY);
			//L2:打印单据名称
			strWidth = g2.getFontMetrics().stringWidth(paras.getPaperName());
			g2.drawString(paras.getPaperName(),sX+(180-strWidth)/2,sY+lineHeight*(++num));
			//L3:打印空行
			g2.drawLine(sX,sY+lineHeight*(++num),400,sY+lineHeight*(num));
			//L4:打印其它附加信息
			g2.drawString("台号:"+paras.getTableName(),sX,sY+lineHeight*(++num));
			g2.drawString("人数："+paras.getGuestNumbers(),sX+120,sY+lineHeight*(num));
			/*在同一行，分隔开*/
			g2.drawString("单号:"+paras.getOrderNumber(),sX,sY+lineHeight*(++num));
			g2.drawString("服务员:"+paras.getWaiter(),sX+120,sY+lineHeight*(num));
			/*在同一行，分隔开*/
			g2.drawString("流水号："+paras.getSerialnumber(),sX,sY+lineHeight*(++num));
			g2.drawLine(sX,sY+lineHeight*(++num),400,sY+lineHeight*(num));
			g2.drawString("时间："+new Date().toLocaleString(),sX,sY+lineHeight*(++num));
			g2.drawLine(sX,sY+lineHeight*(++num),400,sY+lineHeight*(num));
			g2.drawString("菜名             数量             单价",sX,sY+lineHeight*(++num));
			/////////////////////////////////////////////////////////////////////
			String[][] list = paras.getMenuList();
			for(int i=0;i<paras.getMenuList().length;i++)
			{
				/*g2.drawString(list[i][0]+"         "+list[i][1]+"          "+list[i][2],sX,sY+lineHeight*(++num));*/
				g2.drawString(list[i][0],sX,sY+lineHeight*(++num));
				g2.drawString(list[i][1],sX+100,sY+lineHeight*(num));
				g2.drawString(list[i][2],sX+170,sY+lineHeight*(num));
			}
			/*空行*/
			g2.drawString("",sX,sY+lineHeight*(++num));
			g2.drawString("折扣率:"+paras.getDiscountRate(),sX,sY+lineHeight*(++num));
			g2.drawString("消费金额:"+paras.getTotalmoney(),sX,sY+lineHeight*(++num));
			g2.drawLine(sX,sY+lineHeight*(++num),400,sY+lineHeight*(num));
			g2.drawString("收银员:"+paras.getCashier()+"      打印员:"+paras.getPrinter(),sX,sY+lineHeight*(++num));
			return PAGE_EXISTS;
		default:
			return NO_SUCH_PAGE;
		}
	}

	/*public static void DoPrint() throws PrinterException
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
		book.append(new XPrinter80(), pf);
		// 获取打印服务对象
		PrinterJob job = PrinterJob.getPrinterJob();
		// 如果检查是否存在58打印机失败，则提示并退出。
		if(!checkPrinter())
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
	*/
	public static boolean checkPrinter()
	{
		PrintService[] service = PrintServiceLookup.lookupPrintServices(null,null);                    
		for(int i=0;i<service.length;i++){                 
			//找到配置好的网络打印机，厨房端采用XP-58热敏打印机，故匹配58字符串
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