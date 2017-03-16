package sample.db.pojos;
import java.io.Serializable;
public class Doctor implements Serializable{

	private static final long serialVersionUID = -7296233488665503819L;
	private int id;
	private String name,surname,field;
	
	public Doctor(){
		id=(Integer) null;
		name=null;
		surname=null;
		field=null;
	}
	public Doctor(String _name,String _surname, String _field){
		name=_name;
		surname=_surname;
		field=_field;
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
	
}
