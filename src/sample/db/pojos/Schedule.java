package sample.db.pojos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
@Entity
@Table(name="schedule")
public class Schedule implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7487289227470517910L;
	@Id
	@GeneratedValue(generator="schedule")
	@TableGenerator(name="schedule", table="sql_sequence", pkColumnName="name", valueColumnName="seq", pkColumnValue="schedule")
	private int id;
	private String start,end;
	private String day;
	
	@ManyToMany(mappedBy="schedule")
	private List <Doctor> doc;
	
	@ManyToMany(mappedBy="schedule")
	private List <Visitor> vis;
	/**
	 * 
	 */
	public Schedule() {
		super();
		this.doc = new ArrayList<Doctor>();
		this.vis = new ArrayList<Visitor>();
		
		}
	public Schedule(String s, String e, String _day){
		start=s;
		end=e;
		day=_day;
	}
	public Schedule(String _start,String _end, String _day
			,List <Doctor> d,List <Visitor> v){
		start=_start;
		end=_end;
		day=_day;
		doc=d;
		vis=v;
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
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public List<Doctor> getDoc() {
		return doc;
	}
	public void setDoc(List<Doctor> doc) {
		this.doc = doc;
	}
	public List<Visitor> getVis() {
		return vis;
	}
	public void setVis(List<Visitor> vis) {
		this.vis = vis;
	}
	


}

