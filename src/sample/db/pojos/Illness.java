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
	}
	public Illness(String name,List <Patient>p,List <Medication>m){
		this.name=name;
		patient=p;
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
	public List<Patient> getPatient() {
		return patient;
	}
	public void setPatient(List<Patient> patient) {
		this.patient = patient;
	}
	public List<Medication> getTreats() {
		return treats;
	}
	public void setTreats(List<Medication> treats) {
		this.treats = treats;
	}
	
}
