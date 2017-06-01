package sample.db.pojos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
@Entity
@Table(name="doctor")
public class Doctor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7487289227470517910L;
	@Id
	@GeneratedValue(generator="doctor")
	@TableGenerator(name="doctor", table="sql_sequence", pkColumnName="name", valueColumnName="seq", pkColumnValue="doctor")
	private int id;
	private String name,surname,field;
	
	@ManyToMany
	@JoinTable(name="schedule-doctor",
	joinColumns={@JoinColumn(name="doctor_id", referencedColumnName="id")},
    inverseJoinColumns={@JoinColumn(name="schedule_id", referencedColumnName="id")})
	private List <Schedule> schedule;
	
	@ManyToMany(mappedBy="doctor")
	private List <Patient> Looking;
	
	@Basic(fetch = FetchType.LAZY)
	@Lob

	@OneToMany(mappedBy="doctor")
	private List <Patient> In_charge;
	/**
	 * 
	 */
	

	public Doctor() {
		super();
		this.Looking = new ArrayList<Patient>();
		this.schedule = new ArrayList<Schedule>();
		this.In_charge = new ArrayList<Patient>();
		}
	public Doctor(String _name,String _surname, String _field
			,List <Patient>_list,List <Patient> _list1,
			List <Schedule> _sch){
		name=_name;
		surname=_surname;
		field=_field;
		In_charge=_list;
		Looking=_list1;
		schedule=_sch;
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
		Doctor other = (Doctor) obj;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public List<Patient> getIn_charge() {
		return In_charge;
	}
	public void setIn_charge(List<Patient> in_charge) {
		In_charge = in_charge;
		//hola
	}
	public List<Patient> getLooking() {
		return Looking;
	}
	public void setLooking(List<Patient> looking) {
		Looking = looking;
	}
	public List<Schedule> getSchedule() {
		return schedule;
	}
	public void setSchedule(List<Schedule> schedule) {
		this.schedule = schedule;
	}
	
}
