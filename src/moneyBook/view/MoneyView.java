package moneyBook.view;


import java.util.List;
import java.util.Scanner;

import moneyBook.Account;
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
				// 내역등록
				case 1: 
					mb = inputInfo();
					int result = controller.insertInfo(mb);
					if(result > 0)
						printMsg("등록이 완료되었습니다.");
					else
						printMsg("등록에 실패했습니다.");
					break;
				// 수입내역 출력
				case 2: 
					list = controller.printMoneyInfo("수입");
					printMoney(list, "수입");
					break;
				// 지출내역 출력
				case 3:	
					list = controller.printMoneyInfo("지출");
					printMoney(list, "지출");
					break;
				// 등록 내역의 내용 검색
				case 4:	
					memo = serchMemo();
					list = controller.selectMemo(memo);
					printListByMemo(list);
					break;
				// 내역 삭제
				case 5: break;
				// 수입,지출 합계 출력
				case 6:	
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
	

	private void printListByMemo(List<MoneyBook> list) {
		System.out.println("---------------- 결과 --------------------");
		//System.out.printf("구분\t날짜\t내용\t수입금액\t지출금액\t잔액%n");
		for(MoneyBook mb : list) {
			System.out.printf("구분 : %s%n"
					,mb.getCategory());
//					,mb.getDate()
//					,mb.getMemo()
//					,mb.getInMoney()
//					,mb.getOutMoney()
//					,mb.getBalance());
		}
	}


	private String serchMemo() {
		Scanner sc = new Scanner(System.in);
		System.out.print("검색할 메모 내용 : ");
		sc.nextLine();
		memo = sc.nextLine();
		return memo;
	}


	private void printMsg(String message) {
		System.out.println(message);
	}
	
	
	
//	public void printSum () {
//		System.out.println("================ 합 계 액 ===============");
//		System.out.printf("수입 합계 : %d원%n지출 합계 : %d원%n잔     액 : %d원%n"
//				, mb.getSumInMoney() 
//				, mb.getSumOutMoney());
//	}
	
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

	

	private List<MoneyBook> printMoney(List<MoneyBook> list, String category) {
		printCategory(list, category);
		return list;
	}
	public int printMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("");
		System.out.println("========= 수입/지출 기록 프로그램 ========");
		System.out.println("1. 내역 등록");
		System.out.println("2. 수입 내역 조회");
		System.out.println("3. 지출 내역 조회");
		System.out.println("4. 등록 내역 검색");
		System.out.println("5. 등록 내역 삭제");
		System.out.println("6. 합계금액 및 잔액 조회");
		System.out.println("0. 프로그램 종료");
		System.out.println();
		System.out.print("메뉴 선택 : ");
		int choice = sc.nextInt();
		System.out.println();
		return choice;
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
	
}
