package kr.co.sist.callable.service;

import javax.swing.*;

import kr.co.sist.callable.dao.CallableStatementDAO;
import kr.co.sist.prepared.dao.PreparedStatementDAO;
import kr.co.sist.statement.dao.StatementDAO;
import kr.co.sist.statement.vo.EmployeeVO;
import kr.co.sist.vo.ResultVO;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * CRUD를 사용하는 클래스
 */
public class RunCallableStatementDAO {
	public void addEmp() {
		String inputData = JOptionPane.showInputDialog("추가할 사원정보를 입력해주세요.\n사원번호, 사원명, 직무, 연봉");
		if (inputData != null) {
			String[] tempData = inputData.split(",");
			if (tempData.length != 4) {
				JOptionPane.showMessageDialog(null, "입력 형식을 확인해 주세요");
				return;
			} // end if

//			// 사원번호는 숫자 4자리, 숫자형태, 10000이하: 유효성 검증
//	
//				if (tempData[0].length() > 4)  {
//					JOptionPane.showMessageDialog(null, "사원번호 0~9999까지만 입력");
//					return;
//				} // end if
			

			// 입력된 데이터를 하나로 묶어서 관리
			try {
				EmployeeVO eVO = new EmployeeVO(parseInt(tempData[0]), tempData[1], tempData[2],
						parseDouble(tempData[3]), null);
				// DB에 추가하면 된다.
//				PreparedStatementDAO psDAO=PreparedStatementDAO.getInstance();
				CallableStatementDAO csDAO= CallableStatementDAO.getInstance();
				try {
					ResultVO rVO=csDAO.insertEmployee(eVO);
					if(rVO.getCnt() == 1) {
						JOptionPane.showMessageDialog(null, tempData[0] + "번 사원정보가 추가되었습니다.");
						
					}else {
						JOptionPane.showMessageDialog(null, rVO.getErrMsg());
					}//end else

				} catch (SQLException se) {
					se.printStackTrace();
					JOptionPane.showMessageDialog(null, "DBMS에서 문제가 발생");
				} // end catch
				
			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "사원번호나 연봉은 숫자형태이어야 합니다.");
			}//end catch
		}//end if
	}//addEmp
	
	public void modifyEmp() {
		String inputData = JOptionPane.showInputDialog("사원정보 변경\n사원번호,직무,연봉을 입력하면 사원번호에 해당하는 사원의 직무와 연봉을 변경합니다.");
		if (inputData != null) {
			String[] tempData = inputData.split(",");
			if (tempData.length != 3) {
				JOptionPane.showMessageDialog(null, "입력은 사원번호,사원명,직무,연봉의 형식입니다.");
				return; // early return
			} // end if

			try {
				EmployeeVO eVO = new EmployeeVO(parseInt(tempData[0]), null, tempData[1], parseDouble(tempData[2]),
						null);

				// DBMS테이블의 update수행
//				변경전
//				StatementDAO sDAO = new StatementDAO();
//				int cnt = sDAO.updateEmp(eVO);// 0~n건 변경
				
//				PreparedStatementDAO psDAO=PreparedStatementDAO.getInstance();
//				int cnt=psDAO.updateEmp(eVO);
				
				CallableStatementDAO csDAO= CallableStatementDAO.getInstance();
				
				ResultVO rVO=csDAO.updateEmployee(eVO);
				
				int cnt=rVO.getCnt();
				
				String msg = tempData[0] + "번 사원은 존재하지 않습니다. 사원번호를 확인해주세요";
				if (cnt != 0) {// 1건 이상 변경
					msg = tempData[0] + "번으로" + cnt + "건 변경 되었습니다.";
				} // end if

				JOptionPane.showMessageDialog(null, msg);

			} catch (NumberFormatException nfe) {
				JOptionPane.showMessageDialog(null, "사원번호와 연봉은 숫자형태 입니다.");
			} catch (SQLException se) {
				se.printStackTrace();

				String errMsg = "";
				switch (se.getErrorCode()) {
				case 1438:
					errMsg = "연봉은 정수 5자리 실수 2자리로 입력가능합니다.";
					break;
				case 12899:
					errMsg = "사원명과 직무는 한글 3자까지만 가능합니다,";
					break;
				default:
					errMsg = "문제가 발생하였습니다.";
				}// end catch
				JOptionPane.showMessageDialog(null, errMsg);
			}
		} // end if
	}

	public void removeEmp() {
		String inputData = JOptionPane.showInputDialog(null, "사원정보 삭제\\n사원번호를 입력하면 사원번호에 해당하는 사원의 정보를 삭제합니다.");
		if (inputData == null) {
			JOptionPane.showMessageDialog(null, "사원번호를 확인해주세요"); // 사원번호만 받아온 후 유효성검증
			return;
		} // end if

		// 유효성 검증(사원번호 4자리)
		if (inputData.length() > 4) {
			JOptionPane.showMessageDialog(null, "사원번호는 4자리 입니다.");
			return;
		} // end if

		try {
			int empno = parseInt(inputData);// 사원번호 범위 0~9999 사이

			// DBMS에서 조회된 결과를 받아 사용자에게 보여준다.
			PreparedStatementDAO psDAO= PreparedStatementDAO.getInstance();
			int cnt=psDAO.deleteEmp(empno);
			
			String msg = inputData + "번 사원정보를 확인하세요.";

			if (cnt != 0) {
				msg = inputData + "번 사원정보가" + cnt + "건 삭제되었습니다.";
			} // end if

			JOptionPane.showMessageDialog(null, msg);

		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "사원번호는 4자리 숫자입니다.");
			return;
		} catch (SQLException se) {
			se.printStackTrace();
			JOptionPane.showMessageDialog(null, "삭제 중 문제가 발생했습니다.");
		} // end catch
	}// removeEmp

	public void searchAllEmp() {
		// DBMS에서 조회된 결과를 받아서 사용자에게 보여준다.
//		PreparedStatementDAO psDAO=PreparedStatementDAO.getInstance();
		CallableStatementDAO csDAO=CallableStatementDAO.getInstance();
		try {
			List<EmployeeVO> listAllEmp = csDAO.selectAllEmp();
			System.out.println(listAllEmp);
			StringBuilder output = new StringBuilder();
			output.append("사원번호\t사원명\t직무\t연봉\t입사일\n");

			if (listAllEmp.isEmpty()) {
				output.append("데이터가 없습니다.");
			} else {
				for (EmployeeVO eVO : listAllEmp) {
					output.append(eVO.getEMPNO()).append("\t").append(eVO.getENAME()).append("\t").append(eVO.getJOB())
							.append("\t").append(eVO.getSAL()).append("\t").append(eVO.getHIREDATE2())
							.append("\n");

				} // end for
			} // end else

			JTextArea jta = new JTextArea(output.toString(), 10, 80);
			JScrollPane jsp = new JScrollPane(jta);
			JOptionPane.showMessageDialog(null, jsp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}// searchAllEmp

	public void searchOneEmp() {
		String inputData = JOptionPane.showInputDialog("사원검색\n사원번호를 입력해주세요.");
		if (inputData == null) {
			JOptionPane.showMessageDialog(null, "사원번호를 입력해주세요.");
			return;
		} // end if

		try {
			int empno = parseInt(inputData);
			// DBMS에서 조회된 결과를 받아서 사용자에게 보여준다.
//			StatementDAO sDAO = new StatementDAO();
//			EmployeeVO eVO = sDAO.selectOneEmp(empno);
			
//			PreparedStatementDAO psDAO=PreparedStatementDAO.getInstance();
//			EmployeeVO eVO= psDAO.selectOneEmp(empno);

			CallableStatementDAO csDAO=CallableStatementDAO.getInstance();
			EmployeeVO eVO= csDAO.selectOneEmp(empno);
					
			StringBuilder output = new StringBuilder();
			output.append(empno).append("번 사원번호 검색결과\n");

			if (eVO == null) {
				output.append("사원번호를 확인하세요");

			} else {

				output.append("사원명: ").append(eVO.getENAME()).append("\n");
				output.append("직무: ").append(eVO.getJOB()).append("\n");
				output.append("연봉: ").append(eVO.getSAL()).append("\n");
				SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
				output.append("입사일: ").append(sdf.format(eVO.getHIREDATE()));
				output.append("입사일2: ").append(eVO.getHIREDATE2());

			} // end if
			JTextArea jta = new JTextArea(output.toString(), 10, 80);
			JScrollPane jsp = new JScrollPane(jta);
			JOptionPane.showMessageDialog(null, jsp);

		} catch (NumberFormatException nfe) {
			JOptionPane.showMessageDialog(null, "사원번호는 숫자형태 이어야 합니다.");

		} // end catch
		catch (SQLException e) {
			e.printStackTrace();
		} // end catch
	}// searchOneEmp

	public void menu() {
		boolean exitFlag = false;
		String inputMenu = "";

		do {
			inputMenu = JOptionPane.showInputDialog("CallableStatement 메뉴 선택\n1.사원정보추가\n 2.사원정보변경\n 3.사원정보삭제\n 4.전체사원검색\n 5.특정사원검색\n 6.종료");

			if (inputMenu != null) {
				switch (inputMenu) {
				case "1":
					addEmp();
					break;
				case "2":
					modifyEmp();
					break;
				case "3":
					removeEmp();
					break;
				case "4":
					searchAllEmp();
					break;
				case "5":
					searchOneEmp();
					break;
				case "6":
					exitFlag = true;
					break;
				default:
					JOptionPane.showMessageDialog(null, "메뉴는 1,2,3,4,5,6 중 하나만 입력해 주세요");
				}
			}
		} while (!exitFlag);

	}

	public static void main(String[] args) {
		RunCallableStatementDAO runStatementDAO = new RunCallableStatementDAO();
		runStatementDAO.menu();
	}
}
