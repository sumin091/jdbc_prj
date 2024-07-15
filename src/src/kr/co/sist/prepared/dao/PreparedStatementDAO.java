package kr.co.sist.prepared.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import day0304.Singleton;
import kr.co.sist.dao.DbConnection;
import kr.co.sist.statement.vo.EmployeeVO;

public class PreparedStatementDAO {
	// 싱글턴 페턴을 적용
	private static PreparedStatementDAO psDAO;

	private PreparedStatementDAO() {
	}// PreparedStatementDAO

	public static PreparedStatementDAO getInstance() {
		if (psDAO == null) {
			psDAO = new PreparedStatementDAO();
		} // end if
		return psDAO;
	}// getInstance

	/**
	 * 로컬 DBMS에 연동하여 Connection을 반환
	 * 
	 * @param eVO
	 * @throws SQLException
	 */
	public void insertEmp(EmployeeVO eVO) throws SQLException {
		DbConnection dbCon = DbConnection.getInstance();

		// 1.드라이버로딩
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 2.connection 얻기
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);
			// 3.쿼리문 생성객체 얻기(값이 들어가는 위치는 바인드 변수 사용)
			String insertEmp = "insert into employee(empno,ename,job,sal) values(?,?,?,?)";
			pstmt = con.prepareStatement(insertEmp);
			// 4.바인드변수 값 설정
			pstmt.setInt(1, eVO.getEMPNO());
			pstmt.setString(2, eVO.getENAME());
			pstmt.setString(3, eVO.getJOB());
			pstmt.setDouble(4, eVO.getSAL());
			// 5.쿼리문 수행 후 결과얻기(주의: Statement의 executeXxx(SQL)는 절대로 사용하지 않는다.)
			pstmt.executeUpdate();
		} finally {
			// 6.연결끊기
			dbCon.dbClose(null, pstmt, con);
		} // end finally
	}// insertEmp

	/**
	 * 사원번호에 해당하는 레코드를 찾아서 직무와 연봉을 변경
	 * 
	 * @param eVO
	 * @return
	 * @throws SQLException
	 */
	public int updateEmp(EmployeeVO eVO) throws SQLException {
		int cnt = 0;

		DbConnection dbCon = DbConnection.getInstance();
		// 1.드라이버로딩
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			// 2.Connection 얻기
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);
			// 3.쿼리문 생성객체 얻기
			String updateEmp = "update employee set job=?, sal=? where empno=?"; // where절 앞에 , 주의
//			StringBuilder updateEmp=new StringBuilder();
//			updateEmp
//			.append("update employee ")
//			.append("set job=?, sal=?")
//			.append("where empno=? ");

			pstmt = con.prepareStatement(updateEmp);

			// 4.바인드 변수 값 설정
			pstmt.setString(1, eVO.getJOB());
			pstmt.setDouble(2, eVO.getSAL());
			pstmt.setInt(3, eVO.getEMPNO());

			// 5.쿼리문 수행후 결과 얻기
			cnt = pstmt.executeUpdate();
		} finally {
			// 6.연결끊기
			dbCon.dbClose(null, pstmt, con);
		} // end finally
		return cnt;
	}// updateEmp

	/**
	 * 사원번호를 입력받아 해당 사원을 삭제
	 * 
	 * @param empno
	 * @return
	 * @throws SQLException
	 */
	public int deleteEmp(int empno) throws SQLException {
		int cnt = 0;
		DbConnection dbCon = DbConnection.getInstance();

		// 1.드라이버 로딩
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 2.Connection
			String id = "scott";
			String pass = "tiger";
			con = dbCon.getConnection(id, pass);
			// 3.쿼리문 생성객체 생성
			String deleteEmp = "delete from employee where empno=?";

			pstmt = con.prepareStatement(deleteEmp);
			// 4.바인드 변수 값 설정
			pstmt.setInt(1, empno);

			// 5.쿼리문 결과 얻기
			cnt = pstmt.executeUpdate();
		} finally {
			// 6.연결끊기
			dbCon.dbClose(null, pstmt, con);
		}

		return cnt;
	}// deleteEmp
	
	public EmployeeVO selectOneEmp(int empno) throws SQLException{
		EmployeeVO eVO=null;
		
		DbConnection dbCon=DbConnection.getInstance();
		
		//1.
		Connection con= null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
		//2.
			String id="scott";
			String pass="tiger";
			
			con=dbCon.getConnection(id, pass);
		//3.
			StringBuilder selectOneEmp=new StringBuilder();
			selectOneEmp
			.append(" select ENAME,JOB,SAL,hiredate,to_char(HIREDATE,'yyyy-mm-dd') hiredate2 ")
			.append(" from employee ")
			.append(" where empno=? ");
			
			pstmt=con.prepareStatement(selectOneEmp.toString());
		//4.
			pstmt.setInt(1, empno);
		//5.
			rs=pstmt.executeQuery();	//조회된 결과를 움질일수 있는 커서의 제어권을 받는다.
			
			//쿼리문 실행시 조회결과가 있다면
			if(rs.next()) {	//조회결과 있음
				eVO=new EmployeeVO(empno, rs.getString("ename"), rs.getString("job"), rs.getDouble("sal"),rs.getDate("hiredate"),rs.getString("hiredate2"));
			}//end if
		}finally {
		//6.
			dbCon.dbClose(rs, pstmt, con);
		}//end finally
		return eVO;
	}//selectOneEmp
	
	public List<EmployeeVO> selectAllEmp() throws SQLException{
		List<EmployeeVO> list= new ArrayList<EmployeeVO>();

		DbConnection dbCon=DbConnection.getInstance();
		//1.드라이버 로딩
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
		//2.Connection 얻기
			String id="scott";
			String pass="tiger";
			
			con=dbCon.getConnection(id, pass);
		//3.쿼리문 생성 객체 얻기
			String selectAllEmp="select EMPNO,ENAME,JOB,SAL,to_char(HIREDATE,'yyyy-mm-dd q\"분기\"') hiredate from employee";
			
			pstmt=con.prepareStatement(selectAllEmp);
		//4.바인드 변수 값 설정
			
		//5.쿼리문 수행 후 결과얻기
			rs=pstmt.executeQuery();		//커서의 제어권
			EmployeeVO eVO=null;
			
			while(rs.next()) {	//조회된 레코드가 존재하면
				//VO에 검색결과 저장하고
				eVO=new EmployeeVO(rs.getInt("empno"),rs.getString("ename"),rs.getString("job"),rs.getDouble("sal"),null, rs.getString("hiredate"));
				list.add(eVO);	//heap영역으로 올라가면 이전의 값을 사용할수 없기때문
			}
		}finally{
		//6.연결끊기
			dbCon.dbClose(rs, pstmt, con);
		}
		return list;
	}//selectAllEmp
	
	
	
}// class
