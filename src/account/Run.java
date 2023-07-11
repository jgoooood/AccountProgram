package account;

public class Run {

	public static void main(String[] args) {
		AccountFunction aFunc = new AccountFunction();
		finish:
		while(true) {
			int choice = aFunc.printMenu(); //메뉴출력 메소드
			
			switch(choice) {
			// 내역등록
			case 1: aFunc.inputInfo(); break;
			// 수입내역 출력
			case 2: aFunc.printIncomeInfo(); break;
			// 지출내역 출력
			case 3:	aFunc.printSpendingInfo(); break;
			// 등록 내역의 내용 검색
			case 4:	aFunc.serchInfo(); break;
			// 내역 삭제
			case 5: aFunc.removeInfo(); break;
			// 수입,지출 합계 출력
			case 6:	aFunc.printSum(); break;	
			case 7:
				System.out.println("=======================================");
				System.out.println("프로그램을 종료합니다."); 
				break finish;
			default :
				System.out.println("메뉴에 없는 번호입니다.");
			}
			
		}
	}

}
