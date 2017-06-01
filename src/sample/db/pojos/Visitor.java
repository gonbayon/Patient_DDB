package sample.db.pojos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="visitor")
public class Visitor implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7487289227470517910L;
	@Id
	@GeneratedValue(generator="visitor")
	@TableGenerator(name="visitor", table="sql_sequence", pkColumnName="name", valueColumnName="seq", pkColumnValue="visitor")
	private int id;

	private String name,surname;
	
	@ManyToMany
	@JoinTable(name="schedule-visitor",
	joinColumns={@JoinColumn(name="visitor_id", referencedColumnName="id")},
    inverseJoinColumns={@JoinColumn(name="schedule_id", referencedColumnName="id")})

	private List <Schedule> sche;
	

	@Basic(fetch = FetchType.LAZY)
	@Lob
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	private Patient patient;
	
	/**
	 * 
	 */
	public Visitor() {
		super();
		this.sche = new ArrayList<Schedule>();
		
		}
	public Visitor (String _n){
	}

	public Visitor (String _n,String _s){
		surname=_s;

		name=_n;
	}
	public Visitor(String _name, Patient p, List <Schedule> s){
		name=_name;
		patient=p;
		sche=s;
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
		Visitor other = (Visitor) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public String getName() {
		return name;
	}
	public int getId(){
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(int _id){
		id=_id;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public List<Schedule> getSche() {
		return sche;
	}
	public void setSche(List<Schedule> sche) {
		this.sche = sche;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	@Override
	public String toString() {
		return "Visitor id=" + id + ", name=" + name + ", surname=" + surname + ", patient=" + patient + ", sche="
				+ sche ;
	}
	
}
