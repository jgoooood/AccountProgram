package moneyBook.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import moneyBook.model.vo.MoneyBook;

public class MoneyDAO {
	private final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USER = "MONEY";
	private final String PASSWORD = "MONEY";
	public int insertInfo(MoneyBook mb) {
		/*
		 * 1. 드라이버 등록 Class.forName("oracle.jdbc.driver.OracleDriver")
		 * 2. DB 연결 생성 DiverManager.getConnection(url, user, password);
		 * 3. 쿼리문 실행 준비 createStatement();
		 * 4. 쿼리문 실행 -> 5. 결과받기
		 * 6. 자원해제
		 */
		//INSERT INTO MONEY_TBL VALUES('수입', '2023-01-01', '경조사비', 50000, 0, 50000, 0, 50000)
		String query = "INSERT INTO MONEY_TBL VALUES('"+mb.getCategory()+"', '"+mb.getDate()+"', '"+mb.getMemo()+"', "+mb.getInMoney()+", "+mb.getOutMoney()+", '"+mb.getBalance()+"')";
		int result = -1;
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			result = stmt.executeUpdate(query);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}


	public List<MoneyBook> selectCategory(String category) {
		//SELECT * FROM MONEY_TBL WHERE CATEGORY = '수입'
		String query = "SELECT * FROM MONEY_TBL WHERE CATEGORY = '"+category+"'";
		List<MoneyBook> list = new ArrayList<MoneyBook>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			
			if(category.equals("수입")) {
				while(rset.next()) {
					MoneyBook mb = new MoneyBook();
					mb = rsetToInMoney(rset);
					list.add(mb);
				}
			} else {
				while(rset.next()) {
					MoneyBook mb = new MoneyBook();
					mb = rsetToOutMoney(rset);
					list.add(mb);
				}
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}


	private MoneyBook rsetToOutMoney(ResultSet rset) {
		MoneyBook mb = new MoneyBook();
		try {
			mb.setDate(rset.getString("INOUT_DATE"));
			mb.setMemo(rset.getString("MEMO"));
			mb.setOutMoney(rset.getInt("OUT_MONEY"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mb;
	}


	private MoneyBook rsetToInMoney(ResultSet rset) {
		MoneyBook mb = new MoneyBook();
		try {
			mb.setDate(rset.getString("INOUT_DATE"));
			mb.setMemo(rset.getString("MEMO"));
			mb.setInMoney(rset.getInt("IN_MONEY"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mb;
	}


	public List<MoneyBook> selectMemo(String memo) {
		//SELECT * FROM MONEY_TBL WHERE MEMO IN ('경조사비');
		String query = "SELECT * FROM MONEY_TBL WHERE MEMO IN '"+memo+"'";
		List<MoneyBook> list = new ArrayList<MoneyBook>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "MONEY", "MONEY");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			while(rset.next()) {
				MoneyBook mb = new MoneyBook();
				mb.setCategory(rset.getString("CATEGORY"));
				mb.setDate(rset.getString("INOUT_DATE"));
				mb.setMemo(rset.getString("MEMO"));
				mb.setInMoney(rset.getInt("IN_MONEY"));
				mb.setOutMoney(rset.getInt("OUT_MONEY"));
				list.add(mb);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}


	public int deleteInMoney(MoneyBook deleteInInfo) {
		String query = "DELETE FROM MONEY_TBL WHERE CATEGORY = ? AND INOUT_DATE = ? AND IN_MONEY = ?";
		int result = -1;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "MONEY", "MONEY");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, deleteInInfo.getCategory());
			pstmt.setString(2, deleteInInfo.getDate());
			pstmt.setInt(3, deleteInInfo.getInMoney());
			result = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}


	public int deleteOutMoney(MoneyBook deleteOutInfo) {
		String query = "DELETE FROM MONEY_TBL WHERE CATEGORY = ? AND INOUT_DATE = ? AND OUT_MONEY = ?";
		int result = -1;
		PreparedStatement pstmt = null;
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "MONEY", "MONEY");
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, deleteOutInfo.getCategory());
			pstmt.setString(2, deleteOutInfo.getDate());
			pstmt.setInt(3, deleteOutInfo.getOutMoney());
			result = pstmt.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}


	public void selectSumMoney() {
		String query = "SELECT SUM(IN_MONEY) AS sumInMoney, SUM(OUT_MONEY) AS sumOutMoney, (SUM(IN_MONEY) - SUM(OUT_MONEY)) AS sumBalance FROM MONEY_TBL";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "MONEY", "MONEY");
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery(query);
			if(rset.next()) {
				int sumInMoney = rset.getInt("sumInMoney");			
				int sumOutMoney = rset.getInt("sumOutMoney");			
				int sumBalance = rset.getInt("sumBalance");
				
				printSum(sumInMoney, sumOutMoney, sumBalance);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	private void printSum(int sumInMoney, int sumOutMoney, int sumBalance) {
		System.out.println("================ 합 계 액 ===============");
		System.out.printf("수입 합계 : %d원%n지출 합계 : %d원%n잔     액 : %d원%n"
				, sumInMoney ,sumOutMoney, sumBalance);		
	}




}
