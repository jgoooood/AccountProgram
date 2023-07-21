package account.model.vo;

public class Account {
	//멤버변수
	private int inMoney;
	private int OutMoney;
	private String date;
	private String memo;
	private int sumInMoney;
	private int sumOutMoney;
	private int balance;

	public Account() {}

	//getter setter
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

	public String getDate() {
		return date;
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

	public int getSumInMoney() {
		return sumInMoney;
	}

	public void setSumInMoney(int sumInMoney) {
		this.sumInMoney = sumInMoney;
	}

	public int getSumOutMoney() {
		return sumOutMoney;
	}

	public void setSumOutMoney(int sumOutMoney) {
		this.sumOutMoney = sumOutMoney;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "잔액관리 [수입=" + inMoney + ", 지출=" + OutMoney + ", 총 수입=" + sumInMoney
				+ ", 총 지출=" + sumOutMoney + ", 잔액=" + balance + "]";
	}

	
	


	
	
	
	
	
}
