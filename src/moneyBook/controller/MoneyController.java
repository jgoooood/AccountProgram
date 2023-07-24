package moneyBook.controller;

import java.util.List;

import moneyBook.model.dao.MoneyDAO;
import moneyBook.model.vo.MoneyBook;

public class MoneyController {
	MoneyDAO dao = null;
	
	public MoneyController() {
		dao = new MoneyDAO();
	}
	
	public int insertInfo(MoneyBook mb) {
		int result = dao.insertInfo(mb);
		return result;
	}

	public List<MoneyBook> printMoneyInfo(String category) {
		List<MoneyBook> list = dao.selectCategory(category);
		return list;
	}

	public List<MoneyBook> selectMemo(String memo) {
		List<MoneyBook> list = dao.selectMemo(memo);
		return list;
	}

	public int deleteInMoney(MoneyBook deleteInInfo) {
		int result = dao.deleteInMoney(deleteInInfo);
		return result;
	}

	public int deleteOutMoney(MoneyBook deleteOutInfo) {
		int result = dao.deleteOutMoney(deleteOutInfo);
		return result;
	}

	public void selectSumMoney() {
		dao.selectSumMoney();
	}


	

}
