package sample.db.pojos;
import java.io.Serializable;
import java.util.List;
public class Food implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1477367006635945074L;
	private int id;
	private float calories;
	private String name;
	private List <Patient> patient;
	private List <Chronic> rej;
	private Salt salt;
	
	
	public Food(String _name,float _calories){
		name=_name;
		calories=_calories;
	}
	public Food(String _name,int _calories
			,List <Patient> _l,List <Chronic> _l1,Salt _salt){
		name=_name;
		calories=_calories;
		patient=_l;
		rej=_l1;
		salt=_salt;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getCalories() {
		return calories;
	}
	public void setCalories(float calories) {
		this.calories = calories;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Patient> getPatient() {
		return patient;
	}
	public void setPatient(List<Patient> patient) {
		this.patient = patient;
	}
	public List<Chronic> getRej() {
		return rej;
	}
	public void setRej(List<Chronic> rej) {
		this.rej = rej;
	}
	public Salt getSalt() {
		return salt;
	}
	public void setSalt(Salt salt) {
		this.salt = salt;
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
		Food other = (Food) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public String toString(){
		if(salt==null){
			return "Id: "+getId()+"\nName: "+getName()+"\nCalories: "+getCalories()+"\n";
	
		}
		else return "\nId: "+getId()+", Name: "+getName()+", Calories: "+getCalories()+", Amount of Salt: "+getSalt().getAmmo()+"\n";
	}
	
	
}
