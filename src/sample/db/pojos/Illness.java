package sample.db.pojos;
import java.io.Serializable;
import java.util.*;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
@Entity
@Table(name="illness")
public class Illness implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7740673539295188042L;
	@Id
	@GeneratedValue(generator="illness")
	@TableGenerator(name="illness", table="sql_sequence", pkColumnName="name", valueColumnName="seq", pkColumnValue="illness")
	private Integer id;
	private String name;


	
	@ManyToMany
	@JoinTable(name="Ill-pat",
	joinColumns={@JoinColumn(name="illness_id", referencedColumnName="id")},
    inverseJoinColumns={@JoinColumn(name="patient_id", referencedColumnName="id")})
	private List <Patient> patient;
	
	@ManyToMany(mappedBy="illness")

	private List <Medication> treats;
	
	public Illness() {
		super();
		
		this.treats = new ArrayList<Medication>();
		this.patient = new ArrayList<Patient>();
		
	}
	
	public Illness(String n){

		name=n;
		treats=new LinkedList<>();
	}
	public Illness(String name,List <Patient>p,List <Medication>m){
		this.name=name;
		treats=m;
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
		Illness other = (Illness) obj;
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
	public List<Medication> getTreats() {
		return treats;
	}
	public String getMedName(){
		try{
			Medication m=null;
			String s=" ";
			String s1=" ";
			String[] array=new String[treats.size()];
			for(int i=0;i<treats.size();i++){
				m=treats.get(i);
				array[i]=m.getName();
			}
			return Arrays.toString(array);
		}catch(ArrayIndexOutOfBoundsException Ai){
			return "";
		}
	}
	public void setTreats(List<Medication> treats) {
		this.treats = treats;
	}
	@Override
	public String toString() {
		if(treats.size()<1 ){
			return "\nId=" + id + ", name=" + name ;
		}
		else 
			return "\nId=" + id + ", name=" + name +", treated by:" +getMedName() ;
	}
	
	
}
