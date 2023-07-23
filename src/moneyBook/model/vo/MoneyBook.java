package moneyBook.model.vo;

public class MoneyBook {
	//멤버변수
	private String category;
	private String date;
	private String memo;
	private int inMoney;
	private int OutMoney;
	private int balance;
//	private int sumInMoney;
//	private int sumOutMoney;

	public MoneyBook() {}


	//getter setter
	
	public String getDate() {
		return date;
	}
	
	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
	}


	public void setDate(String date) {
		this.date = date;
	}
	
	public String getMemo() {
		return memo;
	}
	
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getInMoney() {
		return inMoney;
	}

	public void setInMoney(int inMoney) {
		this.inMoney = inMoney;
	}

	public int getOutMoney() {
		return OutMoney;
	}

	public void setOutMoney(int outMoney) {
		OutMoney = outMoney;
	}


//	public int getSumInMoney() {
//		return sumInMoney;
//	}
//
//	public void setSumInMoney(int sumInMoney) {
//		this.sumInMoney = sumInMoney;
//	}
//
//	public int getSumOutMoney() {
//		return sumOutMoney;
//	}
//
//	public void setSumOutMoney(int sumOutMoney) {
//		this.sumOutMoney = sumOutMoney;
//	}


	public int getBalance() {
		return balance;
	}


	public void setBalance(int balance) {
		this.balance = balance;
	}
	

	
}
