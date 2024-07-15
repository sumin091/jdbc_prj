package day0304;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsePrepatedStatement {

	/**
	 * CP_DEPT 테이블에 insert
	 * @throws SQLException
	 */
	public UsePrepatedStatement() throws SQLException {
		//1.드라이버로딩
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="tiger";
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
		//2.connection 얻기
			con=DriverManager.getConnection(url,id,pass);
		//3.쿼리문 생성객체 얻기 : preparedStatement는 쿼리문을 알고있기땨문에 쿼리문부터 생성, 바인드 인덱스는 1번부터
			String insertCpDept="insert into cp_dept(deptno,dname,loc)values(?,?,?)";
			pstmt=con.prepareStatement(insertCpDept);
		//4.바인드 변수 값 설정
			int deptno=41;
			String dname="개발부";
			String loc="서울";

			//값과 쿼리가 분리되어있다.
			pstmt.setInt(1, deptno);
			pstmt.setString(2, dname);
			pstmt.setString(3, loc);
			
		//5.쿼리문 수행 후 결과얻기
			int cnt=pstmt.executeUpdate();	//pstmt.execute();와 동일
			
			System.out.println(cnt+"건 추가되었습니다.");
		}finally {
		//6.연결끊기
			if(pstmt != null) {pstmt.close();}
			if(con != null) {con.close();}
			
		}//end finally
	}//UsePrepatedStatement
	
	public static void main(String[] args) {
		try {
			new UsePrepatedStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}//end catch
		
	}//main

}//class
