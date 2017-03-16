package sample.db.pojos;
import java.io.Serializable;

public class Visitor implements Serializable {

	private static final long serialVersionUID = -5664026289077853989L;
	private int id;
	private String name;
	
	public Visitor (){
		super();
	}
	public Visitor(String _name){
		super();
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
		Visitor other = (Visitor) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public String getName() {
		return name;
	}
	public int getId(){
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setId(int _id){
		id=_id;
	}
	
}
