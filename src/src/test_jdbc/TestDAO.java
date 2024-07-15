package test_jdbc;

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
		
	}
	
	public static TestDAO getInstance() {
		if(testDAO == null) {
			testDAO= new TestDAO();
		}
		return testDAO;
	}

	public List<TestVO> selectAll() throws SQLException {
		List<TestVO> list= new ArrayList<TestVO>();
		
		DbConnection dbCon= DbConnection.getInstance();
		
		Connection con= null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		
		try {
			String id="scott";
			String pass="tiger";
			
			con=dbCon.getConnection(id, pass);
			
			StringBuilder sb = new StringBuilder();
			sb.append(" select * from test_sa ");
			
			pstmt=con.prepareStatement(sb.toString());
			
			rs=pstmt.executeQuery();
			
			TestVO testVO=null;
			
			while(rs.next()) {
				testVO=new TestVO(rs.getString("sa_name"),rs.getString("sa_category"),
						rs.getInt("age"), rs.getInt("standing"));
				list.add(testVO);
			}
		}finally {
			dbCon.dbClose(rs, pstmt, con);
		}
		return list;
	}

}
