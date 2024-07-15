package test_menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import kr.co.sist.dao.DbConnection;
import test_jdbc.TestVO;

public class MenuDAO extends JFrame {

	private static MenuDAO menuDAO;

	private MenuDAO() {

	}

	public static MenuDAO getInstance() {
		if (menuDAO == null) {
			menuDAO = new MenuDAO();
		}
		return menuDAO;
	}

	public List<MenuVO> selectAll() throws SQLException {

		List<MenuVO> list = new ArrayList<MenuVO>();

		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String id = "scott";
			String pass = "tiger";

			con = dbCon.getConnection(id, pass);

			StringBuilder sb = new StringBuilder();
			sb.append(" select * from title_bar ");
			// .append(" where sub_title like '%리스트%' ");
			pstmt = con.prepareStatement(sb.toString());

			rs = pstmt.executeQuery();

			MenuVO menuVO = null;

			while (rs.next()) {
				menuVO = new MenuVO(rs.getString("title"), rs.getString("sub_title"));
				list.add(menuVO);
			}
		} finally {
			dbCon.dbClose(rs, pstmt, con);
		}
		return list;

	}


	public MenuVO insertMenu(MenuVO menuVO) throws SQLException {

		List<MenuVO> list= new ArrayList<MenuVO>();

		DbConnection dbCon = DbConnection.getInstance();

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			String id = "scott";
			String pass = "tiger";

			con = dbCon.getConnection(id, pass);
			String output = " insert into title_bar values(?, ?) ";
			pstmt = con.prepareStatement(output);

			pstmt.setString(1, menuVO.getTitle());
			pstmt.setString(2, menuVO.getSub_title());

			pstmt.executeUpdate();

		} finally {
			dbCon.dbClose(null, pstmt, con);
		}
		return menuVO;
	}
	
	public int removeMenu(int tabNum) throws SQLException{
		
		int result=0;
		DbConnection dbcon= DbConnection.getInstance();

		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			String id="scott";
			String pass="tiger";
			
			con=dbcon.getConnection(id, pass);
			
			String delete="delete from title_bar where tabNum=? ";
			
			pstmt= con.prepareStatement(delete);
			
			pstmt.setInt(1, tabNum);
			
			result=pstmt.executeUpdate();
			
		}finally {
			dbcon.dbClose(null, pstmt, con);
		}
		return result;
		
	}
	
	public void updateMenu(MenuVO menuVO) throws SQLException {
		DbConnection dbcon= DbConnection.getInstance();
		
		Connection con= null;
		PreparedStatement pstmt=null;
		
		try {
			String id="scott";
			String pass="tiger";
			
			con=dbcon.getConnection(id, pass);
			
			String update=" update title_bar set sub_title=? where title=? ";
			
			pstmt=con.prepareStatement(update);
			
			pstmt.setString(1, menuVO.getTitle());
			pstmt.setString(2, menuVO.getSub_title());
			
			pstmt.executeUpdate();
		}finally {
			dbcon.dbClose(null, pstmt, con);
		}
	}
	


}
