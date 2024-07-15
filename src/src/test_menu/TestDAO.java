package test_menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.DbConnection;

public class TestDAO {

	private static TestDAO testDAO;

	private TestDAO() {

	}// 생성자

	public static TestDAO getInstance() {
		if (testDAO == null) {
			testDAO = new TestDAO();
		}
		return testDAO;
	}// getInstance

	public List<TestVO> selectAll() throws SQLException {
		List<TestVO> list = new ArrayList<TestVO>();

		// 1. 드라이버 로딩
		// 2. DB 연결 얻기
		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 3. 쿼리문 생성 객체 얻기
			// String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String id = "scott";
			String pw = "tiger";

			con = dbCon.getConnection(id, pw);

			// 4. 쿼리 실행 후 결과 얻기
			StringBuilder sql = new StringBuilder();
			sql.append("select * from test_login");
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();

			TestVO testVO = null;
			while (rs.next()) {
				testVO = new TestVO(rs.getNString("id"), rs.getNString(2));
				list.add(testVO);
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end try

		return list;

	}// selectAll

	public TestVO selectOne(String testId) throws SQLException {
		TestVO testVO = null;

		// 1. 드라이버 로딩
		// 2. DB 연결 얻기
		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// 3. 쿼리문 생성 객체 얻기
			// String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String id = "scott";
			String pw = "tiger";

			con = dbCon.getConnection(id, pw);

			// 4. 쿼리 실행 후 결과 얻기
			StringBuilder sql = new StringBuilder();
			sql.append("select * from test_login where id = ?");

			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, testId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				testVO = new TestVO(rs.getNString("id"), rs.getNString(2));
			} // end while
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		} // end try

		return testVO;

	}// selectAll

	public int insertData(String testId, String testPw) throws SQLException {
		// 1. 드라이버 로딩
		// 2. DB 연결 얻기
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;

		DbConnection dbCon = DbConnection.getInstance();

		try {
			// 3. 쿼리문 생성 객체 얻기
			String id = "scott";
			String pass = "tiger";

			con = dbCon.getConnection(id, pass);
			// 4. 쿼리문 실행 후 결과 얻기
			pstmt = con.prepareStatement("  insert into test_login values(?, ?)   ");
			pstmt.setString(1, testId);
			pstmt.setString(2, testPw);

			result = pstmt.executeUpdate();

		} finally {
			// 5. 연결 끊기
			/*
			 * if(con != null) { con.close(); } if(pstmt != null) { pstmt.close(); }
			 */
			dbCon.dbClose(null, pstmt, con);
		} // end finally

		return result;

	}// insertData

	public void insertDialog(String str) throws SQLException {
		String[] list = str.split(",");

		// 1. 드라이버 로딩
		// 2. DB 연결 얻기
		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 3. 쿼리문 생성 객체 얻기
			String id = "scott";
			String pass = "tiger";

			con = dbCon.getConnection(id, pass);

			// 4. 쿼리문 실행 후 결과 얻기
			pstmt = con.prepareStatement("insert into test_login values(?, ?)");
			pstmt.setString(1, list[0]);
			pstmt.setString(2, list[1]);
			pstmt.executeUpdate();

		} finally {
			dbCon.dbClose(null, pstmt, con);
		} // end finally
	}// insertDialog

	public void update(TestVO testVO) throws SQLException {
		// 1. 드라이버 로딩
		// 2. DB 연결 얻기
		Connection con = null;
		PreparedStatement pstmt = null;

		DbConnection dbCon = DbConnection.getInstance();

		try {
			String id = "scott";
			String pass = "tiger";

			// 3. 쿼리문 생성 객체 얻기
			con = dbCon.getConnection(id, pass);

			// 4. 쿼리 생성 후 실행
			pstmt = con.prepareStatement("update test_login set pw=? where id=?");
			pstmt.setString(1, testVO.getPw());
			pstmt.setString(2, testVO.getId());

			int count = pstmt.executeUpdate();
			System.out.println(count);
		} finally {
			dbCon.dbClose(null, pstmt, con);
		} // end finally
	}// update

	public int delete(String str) throws SQLException {
		// 1. 드라이버 로딩
		// 2. DB 연결 얻기
		Connection con = null;
		PreparedStatement pstmt = null;

		DbConnection dbCon = DbConnection.getInstance();

		try {
			String id = "scott";
			String pass = "tiger";
			// 3. 쿼리문 생성 객체 얻기
			con = dbCon.getConnection(id, pass);

			// 4. 쿼리 실행 후 결과 얻기
			pstmt = con.prepareStatement("delete from test_login where id=?");

			pstmt.setString(1, str);
			int count = pstmt.executeUpdate();
			return count;

		} finally {
			dbCon.dbClose(null, pstmt, con);
		} // end finally

	}// delete

}// class
