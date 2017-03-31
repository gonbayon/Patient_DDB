package sample.db.pojos;
import java.io.Serializable;
import java.util.List;

public class Patient implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3000387307546279723L;
	private int id,room_n;
	private String name,surname;
	private Doctor In_charge;
	private List <Doctor> lookedafter;
	private Illness illness;
	private List <Medication> med;
	private Chronic chronic;
	private List <Food> food;
	private List <Visitor> visitor;
	
	public Patient(){
	}
	public Patient (String _name, String _surname, int n){
		name=_name;
		surname=_surname;
		room_n=n;
		
	}
	public Patient(String _name,String _surname,int room
			,Doctor _doctor, List <Doctor> _look
			,Illness _illness,List <Medication> _med,
			Chronic _chronic,List <Food> _food,List <Visitor> _visitor){
		name=_name;
		surname=_surname;
		room_n=room;
		In_charge=_doctor;
		lookedafter=_look;
		illness=_illness;
		med=_med;
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
	public int getRoom_n() {
		return room_n;
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
	public void setRoom_nº(int room_n) {
		this.room_n = room_n;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Doctor getIn_charge() {
		return In_charge;
	}
	public void setIn_charge(Doctor in_charge) {
		In_charge = in_charge;
	}
	public List<Doctor> getLookedafter() {
		return lookedafter;
	}
	public void setLookedafter(List<Doctor> lookedafter) {
		this.lookedafter = lookedafter;
	}
	public Chronic getChronic() {
		return chronic;
	}
	public void setChronic(Chronic chronic) {
		this.chronic = chronic;
	}
	public List<Food> getFood() {
		return food;
	}
	public void setFood(List<Food> food) {
		this.food = food;
	}
	public List<Visitor> getVisitor() {
		return visitor;
	}
	public void setVisitor(List<Visitor> visitor) {
		this.visitor = visitor;
	}
	public Illness getIllness() {
		return illness;
	}
	public void setIllness(Illness illness) {
		this.illness = illness;
	}
	public List<Medication> getMed() {
		return med;
	}
	public void setMed(List<Medication> med) {
		this.med = med;
	}
	public String toString(){
		return "Id:"+id+"\nName: "+name+"\nSurname: "+surname+"\nRoom: "+room_n+"\n";
	}
}
