package sample.db.pojos;
import java.io.Serializable;
import java.util.List;

public class Medication implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1904325049902196796L;
	private Integer id;
	private String name,agent;
	private List <Patient> patient;
	private List <Illness> treats;
	private List <Chronic> rejects;
	
	public Medication(String n,String a){
		name=n;
		agent=a;
		
	}
	public Medication(String n,String a,
			List <Patient> p,List <Illness> i,
			List <Chronic> r){
		name=n;
		agent=a;
		patient=p;
		treats=i;
		rejects=r;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Medication other = (Medication) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public List<Patient> getPatient() {
		return patient;
	}
	public void setPatient(List<Patient> patient) {
		this.patient = patient;
	}
	public List<Illness> getTreats() {
		return treats;
	}
	public void setTreats(List<Illness> treats) {
		this.treats = treats;
	}
	public List<Chronic> getRejects() {
		return rejects;
	}
	public void setRejects(List<Chronic> rejects) {
		this.rejects = rejects;
	}
	@Override
	public String toString() {
		return "\nId=" + id + ", name=" + name + ", agent=" + agent ;
	}
	
}
