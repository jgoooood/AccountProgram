package account;

public class Account {
	//멤버변수
	private String inOut;
	private String date;
	private int money;
	private String memo;

	
	public Account() {}
	public Account(String inOut, String date, int money, String memo) {
		this.inOut = inOut;
		this.date = date;
		this.money = money;
		this.memo = memo;
	}
	
	//getter
	public String getInOut() {
		return this.inOut;
	}
	public String getDate() {
		return this.date;
	}
	public int getMoney() {
		return this.money;
	}
	public String getMemo() {
		return this.memo;
	}

	
	//setter
	public void setInOut(String inOut) {
		this.inOut = inOut;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Override
	public String toString() {
		return "날짜 : " + getDate() + "%n금액 : " + getMoney() + "%n내용 : " + getMemo()+ "%n";
	}
	
}
