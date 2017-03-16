package sample.db.pojos;
import java.io.Serializable;
enum Week{MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY,SUNDAY};
public class Schedule implements Serializable{

	private static final long serialVersionUID = 5791258505255208298L;
	private int id;
	private String start,end;
	private Week day;
	
	public Schedule(){
		id=(Integer) null;
		start=null;
		end=null;
		day=null;
	}
	public Schedule(String _start,String _end, Week _day){
		start=_start;
		end=_end;
		day=_day;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public Week getDay() {
		return day;
	}
	public void setDay(Week day) {
		this.day = day;
	}
	


}

