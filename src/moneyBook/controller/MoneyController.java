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


	

}
