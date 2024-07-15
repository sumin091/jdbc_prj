package test_jdbc;

import java.sql.SQLException;
import java.util.List;


public class Test_start {

	public static void main(String[] args){

		 TestDAO testDAO= TestDAO.getInstance();
		 
		 try {
			 List<TestVO> list= testDAO.selectAll();
			 
			 StringBuilder output= new StringBuilder();
			 for(TestVO testVO: list) {
				 output.append(testVO.getSa_name()).append(",").append(testVO.getSa_category())
				 .append(",").append(testVO.getAge()).append(",").append(testVO.getStanding()).append("\n");
			 }
			 System.out.println(output);
			 
		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
	}

}
