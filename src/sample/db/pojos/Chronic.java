package sample.db.pojos;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;



	@Entity
	@Table(name="chronic")
	public class Chronic implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -7487289227470517910L;
		@Id
		@GeneratedValue(generator="chronic")
		@TableGenerator(name="chronic", table="sql_sequence", pkColumnName="name", valueColumnName="seq", pkColumnValue="chronic")
		private String type;
		private String name;
		private Integer id;

		@ManyToMany
		@JoinTable(name="med-chronic",
		joinColumns={@JoinColumn(name="chronic_id", referencedColumnName="id")},
	    inverseJoinColumns={@JoinColumn(name="medication_id", referencedColumnName="id")})
		private List <Medication> rej_med;
		@ManyToMany(mappedBy="chronic")
		private List <Food> rejects;
		@OneToMany(mappedBy="chronic")
		private List <Patient> patient;
	/**
	 * 
	 */
	/*private static final long serialVersionUID = -6110426865531354588L;
	private id;*/
	//private String name;
	//private List <Patient> patient;
	//private List <Food> rejects;
	//private List <Medication> rej_med;
	public Chronic() {
		super();
		this.patient = new ArrayList<Patient>();
		this.rejects = new ArrayList<Food>();
		this.rej_med = new ArrayList<Medication>();
		}
	
	public Chronic(String n){
		name=n;
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
}

