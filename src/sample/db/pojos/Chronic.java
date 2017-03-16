package sample.db.pojos;
import java.io.Serializable;
public class Chronic implements Serializable {

	private static final long serialVersionUID = -8152478955758638140L;
	private int id;
	private String name;
	public Chronic(){
		id=(Integer) null;
		name=null;
	}
	public Chronic(String _name){
		name=_name;
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
	
}
