package clientVO;

import java.io.Serializable;
import java.util.Date;

public class MessageInfoC implements Serializable{
	int toTable;
	String content;
	Date sentTime;
	public MessageInfoC (int toTable, String content,Date sentTime) {
		this.toTable = toTable;
		this.content = content;
		this.sentTime = sentTime;
	}
	public int getToTable() {
		return toTable;
	}
	public void setToTable(int toTable) {
		this.toTable = toTable;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSentTime() {
		return sentTime;
	}
	public void setSentTime(Date sentTime) {
		this.sentTime = sentTime;
	}
	@Override
	public String toString() {
		return "MessageInfoC [toTable=" + toTable + ", content=" + content + ", sentTime=" + sentTime + "]";
	}
	
}
