package sample.db.pojos;
import java.io.Serializable;
import java.util.List;

public class Visitor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7772062054424291787L;
	private int id;
	private String name;
	private Patient patient;
	private List <Schedule> sche;
	
	public Visitor (String _n){
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
	
}
