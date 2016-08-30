package event;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {

	private int id=new Random().nextInt(100);
	
	private String msg;
	private Date date;
	private DateFormat df;
	
	
	public Event(Date date,DateFormat df) {
		this.date=date;
		this.df=df;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}



	@Override
	public String toString() {
		return "Event [id=" + id + ", msg=" + msg + ", date=" + date + ", date formatted=" + df.format(date) + "]";
	}
	
	
	
}
