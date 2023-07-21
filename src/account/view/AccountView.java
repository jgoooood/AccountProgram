package account.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import account.Account;

public class AccountView {

	public void startProgram() {
		finish:
			while(true) {
				int choice = printMenu(); //메뉴출력 메소드
				
				switch(choice) {
				// 내역등록
				case 1: 
					inputInfo
					break;
				// 수입내역 출력
				case 2: break;
				// 지출내역 출력
				case 3:	break;
				// 등록 내역의 내용 검색
				case 4:	break;
				// 내역 삭제
				case 5: break;
				// 수입,지출 합계 출력
				case 6:	break;	
				case 0:
					System.out.println("=======================================");
					System.out.println("프로그램을 종료합니다."); 
					break finish;
				default :
					System.out.println("메뉴에 없는 번호입니다.");
				}
				
			}
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
	
	public void inputInfo() {
		String inOut = "";
		int money = 0;
		Scanner sc = new Scanner(System.in);
		System.out.println("================ 내역 등록 ===============");
		//수입 또는 지출로 구분하여 입력
		while(true) {
			System.out.print("구분(ex.수입 또는 지출) : ");
			inOut = sc.next();
			if(inOut.equals("수입") || inOut.equals("지출")) {
				break;
			} else {
				System.out.println("수입 또는 지출을 입력해주세요");
			} 
		}	
		System.out.print("날짜(ex.23/01/01) : ");
		String date = sc.next();
		//금액에 숫자 외 다른 문자 입력시 예외발생 처리
		while(true) {
			try {
				System.out.print("금액 : ");
				money = sc.nextInt();
				break;
			} catch(InputMismatchException e) {
				System.out.println("숫자만 입력해주세요.");
				sc.next();
			}
		}
		System.out.print("내용 : ");
		String memo = sc.next();
		//수입과 지출 금액 따로 관리
		if(inOut.equals("수입")) {
			sumIncome += money;
			// ArrayList사용
			Account acc = new Account(inOut, date, money, memo);
			incomeList.add(acc);
		}
		if (inOut.equals("지출")) {
			sumSpending += money;
			Account acc = new Account(inOut, date, money, memo);
			spendList.add(acc);
		}
		System.out.println("등록이 완료되었습니다.");
	}

}
