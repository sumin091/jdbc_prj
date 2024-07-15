package test_menu;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class run extends JFrame {
    public static void main(String[] args) throws SQLException {
        /*
        //selectAll사용
        TestDAO testDAO = TestDAO.getInstance();
        try{
            List<TestVO> list = testDAO.selectAll();

            StringBuilder output = new StringBuilder();
            for(TestVO testVO : list) {
                output.append(testVO.getId()).append(",").append(testVO.getPw()).append("\n");
            }
            System.out.println(output);
        } catch (SQLException e) {
            e.printStackTrace();
        }//end catch
         */

        /*
        //selectOne사용
        TestDAO testDAO = TestDAO.getInstance();
        TestVO result = testDAO.selectOne("hello");
        System.out.println(result);
         */

        /*
        //insertData사용
        TestDAO testDAO = TestDAO.getInstance();
        testDAO.insertData("안녕", "34567");
        */

        /*
        //insertDialog사용
        TestDAO testDAO = TestDAO.getInstance();
        String input = JOptionPane.showInputDialog(null, "입력하세요.");
        testDAO.insertDialog(input);
        */

        /*
        //update사용
        TestDAO testDAO = TestDAO.getInstance();
        TestVO testVO = new TestVO("byebye", "00000");
        testDAO.update(testVO);
        */

        //delete사용
        TestDAO testDAO = TestDAO.getInstance();
        JOptionPane.showMessageDialog(null, testDAO.delete("hihi") + "건 삭제하였습니다.");
    }//main
}//class
