package utils;

public class PrinterParas {
	private String name;			//餐馆名称
	private String tableName;		//桌号
	private String guestNumbers;	//就餐人数
	private String orderNumber;		//单号
	private String cashier;			//收银员
	private String printer;			//打印员
	private String[][] menuList;	//菜品列表
	private String paperName; 		//单据名称
	private String waiter;			//服务员
	private String serialnumber;	//流水号
	private String discountRate;	//折扣率
	private String totalmoney;		//总金额
	private String orderTime;       //下单时间
	
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}
	public String getTotalmoney() {
		return totalmoney;
	}
	public void setTotalmoney(String totalmoney) {
		this.totalmoney = totalmoney;
	}
	public String getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(String serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getWaiter() {
		return waiter;
	}
	public void setWaiter(String waiter) {
		this.waiter = waiter;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getGuestNumbers() {
		return guestNumbers;
	}
	public void setGuestNumbers(String guestNumbers) {
		this.guestNumbers = guestNumbers;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getCashier() {
		return cashier;
	}
	public void setCashier(String cashier) {
		this.cashier = cashier;
	}
	public String getPrinter() {
		return printer;
	}
	public void setPrinter(String printer) {
		this.printer = printer;
	}
	public String[][] getMenuList() {
		return menuList;
	}
	public void setMenuList(String[][] menuList) {
		this.menuList = menuList;
	}
	
	public String getPaperName() {
		return paperName;
	}
	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}
}
