package serverVO;

/** CommandCheckS에서 HallTableShowJdbcS로
 * tableNo와 codeNumber를 데려오기 위한 VO
 * */
public class TableCodeS {
	int tableNoS_VO;
	String codeNoS_VO;

	public TableCodeS(){}
	public TableCodeS(int tableNoS_VO, String codeNoS_VO) {
		this.tableNoS_VO = tableNoS_VO;
		this.codeNoS_VO = codeNoS_VO;
	}

	public int getTableNoS_VO() {
		return tableNoS_VO;
	}

	public String getCodeNoS_VO() {
		return codeNoS_VO;
	}

	public void setTableNoS_VO(int tableNoS_VO) {
		this.tableNoS_VO = tableNoS_VO;
	}

	public void setCodeNoS_VO(String codeNoS_VO) {
		this.codeNoS_VO = codeNoS_VO;
	}

	
}
