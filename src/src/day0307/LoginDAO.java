package day0307;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kr.co.sist.dao.DbConnection;
import kr.co.sist.vo.LoginVO;

public class LoginDAO {
	private static LoginDAO lDAO;
	private LoginDAO() {
		
	}
	//로그인 코드를 Statement로 구현
	
	public static LoginDAO getInstance(){	
		if(lDAO == null) {
			lDAO=new LoginDAO();
		}
		return lDAO;
	}//getInstance
		
		public LoginResultVO selectLogin(LoginVO lVO) throws SQLException{
		LoginResultVO lrVO=null;
		
		DbConnection dbCon= DbConnection.getInstance();
		
		Connection con=null;
		Statement stmt=null;
		ResultSet rs=null;
		
		try {
			String id="scott";
			String pass="tiger";
			con=dbCon.getConnection(id, pass);
			
			stmt=con.createStatement();
			
			StringBuilder selectData= new StringBuilder();
			selectData
			.append("	select name, input_date"	)
			.append("	from test_login	")
			.append("	where id='"+lVO.getId()+"' and pass='"+lVO.getPass()+"'");
			
			rs=stmt.executeQuery(selectData.toString());
			
			if(rs.next()) {
				lrVO=new LoginResultVO(rs.getString("name"),rs.getDate("input_date"));
			}//end if
		}finally {
			dbCon.dbClose(rs, stmt, con);
		}//end finally
		return lrVO;
	}//selectLogin
		
		//PreparedStatement를 사용하면 SQLInjection이 방어된다.
		public LoginResultVO selectPreparedLogin(LoginVO lVO) throws SQLException{
		LoginResultVO lrVO=null;
		
		DbConnection dbCon= DbConnection.getInstance();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			String id="scott";
			String pass="tiger";
			con=dbCon.getConnection(id, pass);
			
			StringBuilder selectData= new StringBuilder();
			selectData
			.append("	select name, input_date"	)
			.append("	from test_login	")
			.append("	where id=? and pass=? ");

			pstmt=con.prepareStatement(selectData.toString());
			//4.
			pstmt.setString(1, lVO.getId());
			pstmt.setString(2, lVO.getPass());
			
			//5.
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				lrVO=new LoginResultVO(rs.getString("name"),rs.getDate("input_date"));
			}//end if
		}finally {
			dbCon.dbClose(rs, pstmt, con);
		}//end finally
		return lrVO;
	}//selectLogin
		
		public static void main(String[] args) throws SQLException{
			System.out.println(LoginDAO.getInstance().selectLogin(new LoginVO("kim","1234")));
		}
		
		
		
}
