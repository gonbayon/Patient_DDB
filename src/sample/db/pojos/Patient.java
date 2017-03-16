package sample.db.pojos;
import java.io.Serializable;
import java.util.List;

public class Patient implements Serializable {


	private int id,room_nº;
	private String name,surname;
	private Doctor In_charge;
	private List <Doctor> lookedafter;
	//private Illness illness;
	//private List <Medication> med;
	private Chronic chronic;
	private List <Food> food;
	private List <Visitor> visitor;
	
	public Patient(){
		name=null;
		surname=null;
		room_nº=(Integer) null;
		In_charge=null;
		lookedafter=null;
		//illness=null;
		//med=null;
		chronic=null;
		food=null;
		visitor=null;
		
	}
	public Patient(String _name,String _surname,int room
			,Doctor _doctor, List <Doctor> _look
			/*,Illness _illness,List <Medication> _med*/,
			Chronic _chronic,List <Food> _food,List <Visitor> _visitor){
		name=_name;
		surname=_surname;
		room_nº=room;
		In_charge=_doctor;
		lookedafter=_look;
		//illness=_illness;
		//med=_med;
		chronic=_chronic;
		food=_food;
		visitor=_visitor;
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
		Patient other = (Patient) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public int getId() {
		return id;
	}
	public int getRoom_nº() {
		return room_nº;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public void setId(int _id){
		id=_id;
	}
	public void setRoom_nº(int room_nº) {
		this.room_nº = room_nº;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
}
