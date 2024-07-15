package work0305;

import java.sql.SQLException;
import java.util.List;

public class Work0305DAO  {
	private static Work0305DAO wkDAO;
	
	public Work0305DAO getInscnace(){
		if(wkDAO==null) {
			wkDAO=new Work0305DAO();
		}//end if
		return wkDAO;
		
	}//geninstance
		
		try {
			new Work0305DAO();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
