package utils;

public class PrinterParas {
	private String name;			//�͹�����
	private String tableName;		//����
	private String guestNumbers;	//�Ͳ�����
	private String orderNumber;		//����
	private String cashier;			//����Ա
	private String printer;			//��ӡԱ
	private String[][] menuList;	//��Ʒ�б�
	private String paperName; 		//��������
	private String waiter;			//����Ա
	private String serialnumber;	//��ˮ��
	private String discountRate;	//�ۿ���
	private String totalmoney;		//�ܽ��
	private String orderTime;       //�µ�ʱ��
	
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
