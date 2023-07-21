package account;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AccountFunction {

	
	// ============================================== 메뉴 메소드 ============================================
	// 1. 내역 등록
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
	
	// 2. 수입내역 출력
	public void printIncomeInfo () {
		System.out.println("=============== 수입 내역 ===============");
		if(incomeList.size() != 0) {
			for(int i = 0; i < incomeList.size(); i++) {
				System.out.printf("%d번째 등록한 수입 내역%n날    짜 : %s%n수입금액 : %d원%n내    용 : %s%n"
						, (i+1), incomeList.get(i).getDate()
						, incomeList.get(i).getMoney()
						, incomeList.get(i).getMemo()); 
				System.out.println();
			}
			System.out.println("-----------------------------------------");
			System.out.println("수입 누계 : "+sumIncome+"원");
		} else {
			System.out.println("등록된 수입 내역이 없습니다. 내역 등록을 진행해주세요.");
		}

	}
	
	// 3. 지출내역출력
	public void printSpendingInfo () {
		System.out.println("================ 지출 내역 ===============");
		if(spendList.size() != 0) {
			for(int i = 0; i < spendList.size(); i++) {
				System.out.printf("%d번째 등록한 지출 내역%n날    짜 : %s%n지출금액 : %d원%n내    용 : %s%n"
						, (i+1), spendList.get(i).getDate()
						, spendList.get(i).getMoney()
						, spendList.get(i).getMemo()); 
				System.out.println();
			}			
			System.out.println("-----------------------------------------");
			System.out.println("지출 누계 : " + sumSpending+"원");
		} else {
			System.out.println("등록된 지출내역이 없습니다. 내역 등록을 진행해주세요.");
		}
	}
	
	// 4. 등록 내역의 내용 검색
	public void serchInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("================ 내역 검색 ===============");
		System.out.println("등록한 내용을 정확하게 입력해주세요.");
		System.out.print("내용 검색 : ");
		String searchMemo = sc.next();
		System.out.println("---------------- 결과 --------------------");
		// 수입
		for(Account account : incomeList) {
			if(account.getMemo().equals(searchMemo)) {
				System.out.println("▶ 수입 내역");
				System.out.printf(account.toString());
				break;
			}
		}
		// 지출
		for(Account account : spendList) {
			if(account.getMemo().equals(searchMemo)) {
				System.out.println("▶ 지출 내역");
				System.out.printf(account.toString());
				break;
			}
		}
		System.out.println();
		System.out.println("[검색 완료]");
	}
	
	// 5. 등록 내역 삭제
	public void removeInfo() {
		String inOut = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("================ 내역 삭제 ===============");
		//내역 구분 시 수입 또는 지출만 입력
		while(true) {
			System.out.print("삭제할 내역(ex.수입 또는 지출)  : ");
			inOut = sc.next();
			if(inOut.equals("수입") || inOut.equals("지출")) {
				break;
			} else {
				System.out.println("수입 또는 지출을 입력해주세요");
			} 
		}
		//삭제할 내용 검색
		System.out.print("삭제할 내용 검색 : ");
		String memo = sc.next();
		//수입->일치하는 내용있으면 삭제, 없으면 안내 메시지 출력
		if (inOut.equals("수입") && incomeList.size() != 0) {
			for(int i = 0; i < incomeList.size(); i++) {
				Account account = incomeList.get(i);
				if(account.getMemo().equals(memo)) {
					sumIncome -= incomeList.get(i).getMoney();
					incomeList.remove(i);
					System.out.println("[삭제완료]");
					break;
				} else {
					System.out.println("일치하는 내용이 없습니다.");
				}
			} 
		//지출->일치하는 내용있으면 삭제, 없으면 안내 메시지 출력
		} else	if (inOut.equals("지출") && spendList.size() != 0) {
			for(int i = 0; i < spendList.size(); i++) {
				Account account = spendList.get(i);
				if(account.getMemo().equals(memo)) {
					sumSpending -= spendList.get(i).getMoney();
					spendList.remove(i);
					System.out.println("[삭제완료]");
					break; 
				} else {
					System.out.println("일치하는 내용이 없습니다.");
				}
			}
		// 등록된 수입과 지출 없을 경우 출력되는 메시지
		} else {
			System.out.println("삭제할 내역이 없습니다.");
		}
		System.out.println();
	}
	
	// 6. 합계액 출력
	public void printSum () {
		System.out.println("================ 합 계 액 ===============");
		System.out.printf("수입 합계 : %d원%n지출 합계 : %d원%n잔     액 : %d원%n"
				, sumIncome ,sumSpending, (sumIncome - sumSpending));
	}

}
