package serverUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class HallTableButtonListenerS implements ActionListener{
	int tableNo;
	
	public HallTableButtonListenerS(int num){
		this.tableNo= num;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new HallTableShowJdbcS(tableNo);
	}
}
