package day0305;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import kr.co.sist.dao.DbConnection;

public class UseClob {

	public List<TestClobVO> selectClob() throws SQLException{
		List<TestClobVO> list= new ArrayList<TestClobVO>();
		DbConnection dbCon= DbConnection.getInstance();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		//1.
		try {
		//2.
			String id="scott";
			String pass="tiger";
			
			con=dbCon.getConnection(id, pass);
		//3.
			String selectClob="select num,title,content,writer,input_date from test_clob";
			pstmt=con.prepareStatement(selectClob);
		//4.
		//5.
			rs=pstmt.executeQuery();
			
			TestClobVO tcVO=null;
			
			BufferedReader br=null;
			StringBuilder content= new StringBuilder();
			String temp="";
			
			while(rs.next()) {
				tcVO= new TestClobVO();
				tcVO.setNUM(rs.getInt("num"));
				tcVO.setTITLE(rs.getString("title"));
				tcVO.setWRITER(rs.getString("writer"));
				tcVO.setINPUT_DATE(rs.getDate("input_date"));
//				tcVO.setCONTENT(rs.getString("content"));
				//CLOB데이터형을 읽어들이기 위해 별도에 Stream을 연결
				br=new BufferedReader(rs.getClob("content").getCharacterStream());
				
				try {
					while( (temp=br.readLine()) != null) {
						content.append(temp).append("\n");
					}//end while
					br.close();
					
				} catch (IOException ie) {
					content.append("N/A");
					ie.printStackTrace();
				}//end catch
				
				tcVO.setCONTENT(content.toString());
				//저장된 content를 삭제한다.
				content.delete(0, content.length());
				list.add(tcVO);
			}//end while
			
		}finally {
		//6.
			dbCon.dbClose(rs, pstmt, null);
		}
		return list;
	}//useClob
	
	public void useSelectClob(List<TestClobVO> list) {

		Iterator<TestClobVO> ita=list.iterator();
		
		if(list.isEmpty()) {
			System.out.println("작성된 기사가 없습니다.");
		}//end if
		
		TestClobVO tcVO=null;
		
		while(ita.hasNext()) {
			tcVO=ita.next();
			System.out.println("번호: "+tcVO.getNUM()+",제목: "+tcVO.getTITLE());
			System.out.println("내용: "+tcVO.getCONTENT());
			System.out.println(tcVO.getWRITER());
			System.out.println("--------------------------------------------");
		}//end while
		
	}//useSelectClob
	
	
	
	public static void main(String[] args) {
		UseClob uc= new UseClob();
		try {
			List<TestClobVO> list= uc.selectClob();
			uc.useSelectClob(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}//main

}//class
