package test_menu;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Start extends JFrame {

//	public void addMenu() {
//		String inputData = JOptionPane.showInputDialog("값을 넣어주세요");
//		String[] tempData = inputData.split(",");
//		System.out.println(inputData);
//		System.out.println(tempData[0]);
//
//		String td1 = tempData[0];
//		String td2 = tempData[1];
//
//		MenuVO menuVO = new MenuVO(td1, td2);
//
//		try {
//			MenuDAO menuDAO = MenuDAO.getInstance();
//			menuDAO.insertMenu(menuVO);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//	}
	
	

	public static void main(String[] args) throws SQLException {
		MenuDAO menuDAO = MenuDAO.getInstance();
		
		
//		Start start = new Start();
//		start.addMenu();

		MenuVO menuVO=new MenuVO("맛집", "맛집리뷰");
//		try {
			
			menuDAO.updateMenu(menuVO);
			System.out.println( menuDAO.removeMenu(4));
			
//			List<MenuVO> list = menuDAO.selectAll();
//			
//			
//			StringBuilder output = new StringBuilder();
//			for (MenuVO menuVO : list) {
//				output.append(menuVO.getTitle()).append(",").append(menuVO.getSub_title()).append("\n");
//			}
//			System.out.println(output);

//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

//		String title = "고객센터";
//		String sub_title = "공지사항";


//		try {
//			menuDAO.insertMenu(menuVO);
//
//		} catch (SQLException e) { // TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		
	}// main

}
