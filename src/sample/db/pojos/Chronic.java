package sample.db.pojos;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
public class Chronic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6110426865531354588L;
	private int id;
	private String name;
	private List <Patient> patient;
	private List <Food> rejects;
	private List <Medication> rej_med;
	public Chronic(String n){
		name=n;
		rej_med=new LinkedList();
		patient=new LinkedList();
		rejects=new LinkedList();
	}

	public Chronic(String _name,List <Patient> _patient,
			List <Food> _rejects,List <Medication> _rej){
		name=_name;
		patient=_patient;
		rejects=_rejects;
		rej_med=_rej;
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
		Chronic other = (Chronic) obj;
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
	public List<Patient> getPatient() {
		return patient;
	}
	public void setPatient(List<Patient> patient) {
		this.patient = patient;
	}
	public List<Food> getRejects() {
		return rejects;
	}
	public void setRejects(List<Food> rejects) {
		this.rejects = rejects;
	}
	public List<Medication> getRej_med() {
		return rej_med;
	}
	public void setRej_med(List<Medication> rej_med) {
		this.rej_med = rej_med;
	}
	public String getMedName(){
		try{
			Medication m=null;
			String s=" ";
			String s1=" ";
			String[] array=new String[rej_med.size()];
			for(int i=0;i<rej_med.size();i++){
				m=rej_med.get(i);
				array[i]=m.getName();
			}
			return Arrays.toString(array);
		}catch(ArrayIndexOutOfBoundsException Ai){
			return "";
		}
	}
	public String getFoodName(){
		try{
			Food f=null;
			String[] array=new String[rejects.size()];
			for(int i=0;i<rejects.size();i++){
				f=rejects.get(i);
				array[i]=f.getName();
			}
			return Arrays.toString(array);
		}catch(ArrayIndexOutOfBoundsException Ai){
			return "";
		}
	}
	
	@Override
	public String toString() {
		return String.format("Id=%s, name=%s, food rejected=%s, med=%s", id, name,getFoodName(),
				getMedName()+"\n");
	}
	
	
	
}
