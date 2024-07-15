package kr.co.sist.statement.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.statement.vo.EmployeeVO;

/**
 * DAO(Data Access Object)
 */
public class StatementDAO {
	public void insertEmp(EmployeeVO eVO) throws SQLException {
		// 1.드라이버로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} // end catch
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "scott";
		String pass = "tiger";

		Connection con = null;
		Statement stmt = null;

		try {
			// 2.커넥션얻기: autocommit
			con = DriverManager.getConnection(url, id, pass);
			// 3.쿼리문 생성객체 얻기
			stmt = con.createStatement();
			// 4.쿼리문 수행 후 결과 얻기
			StringBuilder insertEmployee = new StringBuilder();
			insertEmployee.append("insert into employee(empno,ename,job,sal) values(").append(eVO.getEMPNO())
					.append(",'").append(eVO.getENAME()).append("','").append(eVO.getJOB()).append("',")
					.append(eVO.getSAL()).append(")");

			// insert는 1건 추가 아니면 예외
			/* int cnt= */stmt.executeUpdate(insertEmployee.toString());
			System.out.println(insertEmployee);
		} finally {
			// 5.연결 끊기
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}

		}

	}// insertEmp

	public int updateEmp(EmployeeVO eVO) throws SQLException {
		int cnt = 0;
		// 1.드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} // end catch

		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "scott";
		String pass = "tiger";

	
		Connection con = null;
		Statement stmt = null;

		try {
			// 2.DB 연결 얻기
			con = DriverManager.getConnection(url, id, pass);
			// 3.쿼리문 생성객체 얻기
			stmt = con.createStatement();
			// 4.쿼리문 수행 후 결과 얻기
			// 사원번호에 해당하는 레코드를 찾아서 직무와 연봉을 변경
			StringBuilder updateEmp = new StringBuilder();
			updateEmp.append("update employee ") /* 띄어쓰기 주의 */
					.append("set job='").append(eVO.getJOB()).append("',sal=").append(eVO.getSAL())
					.append("where empno=").append(eVO.getEMPNO());
			System.out.println(updateEmp);
			cnt = stmt.executeUpdate(updateEmp.toString());
		} finally {
			// 5.DB 연결 끊기
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		} // end finally

		return cnt;
	}// updateEmp

	public int deleteEmp(int empno) throws SQLException {
		int cnt = 0;
		//1.드라이버 로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		Connection con=null;
		Statement stmt=null;
		
		try {
			//2.연결
			con=DriverManager.getConnection(url,id,pass);
			//3.쿼리문 객체생성
			stmt=con.createStatement();
			//4.쿼리문 삭제 후 결과 얻기
			StringBuilder deleteEmp= new StringBuilder();
			deleteEmp.append("delete from employee where empno=").append(empno);
			
			cnt=stmt.executeUpdate(deleteEmp.toString());
		}finally{
			//5.연결끊기
			if(con != null) {con.close();}
			if(stmt != null) {stmt.close();}
		}
		return cnt;
	}// deleteEmp

	public List<EmployeeVO> selectAllEmp() throws SQLException {
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		//1.드라이버로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		
		String url="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		ResultSet rs= null;
		Statement stmt=null;
		Connection con=null;
		
		try {
		//2.커넥션 얻기
			con=DriverManager.getConnection(url,id,pass);
		//3.쿼리문 생성객체
			stmt=con.createStatement();
		//4.쿼리문 수행 후 결과얻기
			String selectEmp="select empno, ename, job, sal, hiredate from employee";
			rs=stmt.executeQuery(selectEmp);
		
			EmployeeVO eVO=null;
			while(rs.next()) {
				eVO=new EmployeeVO(rs.getInt("empno"),rs.getString("ename"),
						rs.getString("job"),rs.getDouble("sal"),rs.getDate("hiredate"));
				
				list.add(eVO);
			}//end while
		}finally {
			//5.연결끊기
			if(rs != null) {rs.close();}
			if(stmt != null) {stmt.close();
			if(con != null) {con.close();}
			}
		}
		return list;
	}// selectAllEmp

	public EmployeeVO selectOneEmp(int empno) throws SQLException {
		EmployeeVO eVO = null;
		// 1.드라이버로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} // end catch

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "scott";
		String pass = "tiger";

		try {
			// 2.커넥션 얻기
			con = DriverManager.getConnection(url, id, pass);
			// 3.쿼리문 생성객체 얻기
			stmt = con.createStatement();
			// 4.쿼리문 수행 후 결과 얻기
			StringBuilder selectEmp = new StringBuilder();
			selectEmp.append("select empno, ename, job, sal, hiredate ").append("from employee ").append("where empno=")
					.append(empno);

			rs = stmt.executeQuery(selectEmp.toString());

			//// 쿼리문이 실행되고 결과가 나왔을때 레코드포인터(cusrsor)다음에 레코드가 존재하는지?
			String ename = "", job = "";
			double sal = 0.0;
			Date hiredate = null;

			if (rs.next()) {
				// 레코드 포인터가 다음레코드로 이동, 이동된 위치의 컬럼값을 얻는다
				// eVO= new EmployeeVO(empno,
				// rs.getString("empno"),rs.getString("job"),rs.getDouble("sal",rs.getDate("hiredate"));
				ename = rs.getString("ename");
				job = rs.getString("job");
				sal = rs.getDouble("sal");
				hiredate = rs.getDate("hiredate");
				eVO = new EmployeeVO(empno, ename, job, sal, hiredate);
			}
			System.out.println(rs.next());
		} finally {
			// 5.연결끊기
			if (rs != null) {
				rs.close();
			}
			if (con != null) {
				con.close();
			}
			if (stmt != null) {
				stmt.close();
			}
		}
		return eVO;
	}// selectOneEmp
}// class
