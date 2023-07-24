package moneyBook.view;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import moneyBook.controller.MoneyController;
import moneyBook.model.vo.MoneyBook;

public class MoneyView {
	private MoneyController controller= null;
	
	int balance = 0;
	int money = 0;
	String date = "";
	String category = "";
	String memo = "";
	
	public MoneyView() {
		controller = new MoneyController();
	}
	
	
	public void startProgram() {
		MoneyBook mb = null;
		List<MoneyBook> list = null; 
		finish:
			while(true) {
				int choice = printMenu(); //메뉴출력 메소드
				
				switch(choice) {
				case 1: 
					// 내역등록
					mb = inputInfo();
					int result = controller.insertInfo(mb);
					if(result > 0)
						printMsg("등록이 완료되었습니다.");
					else
						printMsg("등록에 실패했습니다.");
					break;
				case 2: 
					// 수입내역 출력
					list = controller.printMoneyInfo("수입");
					printMoney(list, "수입");
					break;
				case 3:	
					// 지출내역 출력
					list = controller.printMoneyInfo("지출");
					printMoney(list, "지출");
					break;
				case 4:	
					// 수입내역 삭제
					MoneyBook deleteInInfo = inputDelInfo("수입"); 
					result = controller.deleteInMoney(deleteInInfo);
					if(result > 0)
						printMsg("정보가 삭제되었습니다.");
					else
						printMsg("삭제에 실패했습니다.");
					break;
				case 5:	
					// 지출내역 삭제
					MoneyBook deleteOutInfo = inputDelInfo("지출"); 
					result = controller.deleteOutMoney(deleteOutInfo);
					if(result > 0)
						printMsg("정보가 삭제되었습니다.");
					else
						printMsg("삭제에 실패했습니다.");
					break;
				case 6:	
					// 등록 내역의 내용 검색
					String memo = serchMemo();
					list = controller.selectMemo(memo);
					printListByMemo(list);
					break;
					
				case 7:	
					// 수입,지출 합계 출력
					controller.selectSumMoney();
					break;	
				case 0:
					System.out.println("=======================================");
					printMsg("프로그램을 종료합니다.");
					break finish;
				default :
					printMsg("메뉴에 없는 번호입니다.");

				}
				
			}
	}
	

	public MoneyBook inputInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("================ 내역 등록 ===============");
		//수입 또는 지출로 구분하여 입력
		System.out.print("구분(ex.수입 또는 지출) : ");
		category = sc.next();
		System.out.print("날짜(ex.2023-01-01) : ");
		date = sc.next();
		System.out.print("금액 : ");
		money = sc.nextInt();
		System.out.print("내용 : ");
		sc.nextLine();
		memo = sc.nextLine();
		
		MoneyBook mb = addInfo(category, money, date, memo);
		
		return mb;
	}


	private MoneyBook inputDelInfo(String category) {
		Scanner sc = new Scanner(System.in);
		System.out.println("삭제할 "+category+"내역을 입력해주세요");
		System.out.print("날짜(ex.2023-01-01) : ");
		date = sc.next();
		System.out.print("금액 : ");
		money = sc.nextInt();
		MoneyBook deleteMb = deleteInfo(category, date, money);
		return deleteMb;
	}


	private MoneyBook addInfo(String category, int money, String date, String memo) {
		MoneyBook mb = new MoneyBook();
		if(category.equals("수입")) {
			mb.setInMoney(money);
			mb.setOutMoney(0);
			balance += mb.getInMoney();		
			mb.setBalance(balance);
		}
		if (category.equals("지출")) {
			mb.setOutMoney(money);
			mb.setInMoney(0);	
			balance -= mb.getOutMoney();
			mb.setBalance(balance);
		}
		mb.setCategory(category);
		mb.setDate(date);
		mb.setMemo(memo);
		return mb;
	}


	private MoneyBook deleteInfo(String category, String date, int money) {
		MoneyBook deleteMb = new MoneyBook();
		if(category.equals("수입")) {
			deleteMb.setInMoney(money);
		} else if (category.equals("지출")) {
			deleteMb.setOutMoney(money);
		}
		deleteMb.setCategory(category);
		deleteMb.setDate(date);
		return deleteMb;
	}


	private String serchMemo() {
		Scanner sc = new Scanner(System.in);
		System.out.print("검색할 메모 내용 : ");
		memo = sc.nextLine();
		return memo;
	}


	private List<MoneyBook> printMoney(List<MoneyBook> list, String category) {
		printCategory(list, category);
		return list;
	}


	private void printListByMemo(List<MoneyBook> list) {
	
		System.out.println("------------------------------------------------------------");
		System.out.println(" 구   분  |  날    짜  |  내    용  |  수입금액  |  지출금액");
		System.out.println("------------------------------------------------------------");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for(MoneyBook mb : list) {
			String dateString = mb.getDate();
			Date date = null;
			try {
				date = dateFormat.parse(dateString);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String formatDate = dateFormat.format(date);
			System.out.printf("   %s   | %s | %s | %-10d| %-10d%n"
					,mb.getCategory()
					,formatDate
					,mb.getMemo()
					,mb.getInMoney()
					,mb.getOutMoney());
		}
		System.out.println("------------------------------------------------------------");
	}


	private void printCategory(List<MoneyBook> list, String category) {
		int inSum = 0;
		int outSum = 0;
		System.out.println("=============== "+category+" 내역 ===============");
		for(int i = 0; i < list.size(); i++) {
			if(category.equals("수입")) {
				date = list.get(i).getDate();
				memo = list.get(i).getMemo();
				money = list.get(i).getInMoney();
				inSum += money;
			} else if(category.equals("지출")) {
				date = list.get(i).getDate();
				memo = list.get(i).getMemo();
				money = list.get(i).getOutMoney();
				outSum += money;
			}
			System.out.printf("%d번째 등록한 %s 내역%n날    짜 : %s%n내    용 : %s%n%s금액 : %d원%n"
					, (i+1)
					, category
					, date
					, memo
					, category
					, money);
			System.out.println();
		}
		System.out.println("-----------------------------------------");
		if(category.equals("수입"))
			System.out.printf("%s 합계 : "+inSum+"원",category);
		else if(category.equals("지출"))
			System.out.printf("%s 합계 : "+outSum+"원",category);
	}

	

	public int printMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.println("========= 수입/지출 기록 프로그램 ========");
		System.out.println("1. 내역 등록");
		System.out.println("2. 수입 내역 조회");
		System.out.println("3. 지출 내역 조회");
		System.out.println("4. 수입 내역 삭제");
		System.out.println("5. 지출 내역 삭제");
		System.out.println("6. 등록 내역 검색");
		System.out.println("7. 합계금액 및 잔액 조회");
		System.out.println("0. 프로그램 종료");
		System.out.println();
		System.out.print("메뉴 선택 : ");
		int choice = sc.nextInt();
		System.out.println();
		return choice;
	}


	private void printMsg(String message) {
		System.out.println(message);
	}
	
}
