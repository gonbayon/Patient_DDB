package sample.db.pojos;
import java.io.Serializable;
public class Food implements Serializable{


	private static final long serialVersionUID = 726085249714505863L;
	private int id,calories;
	private String name;
	
	public Food(){
		id=(Integer) null;
		name=null;
		calories=(Integer) null;
	}
	public Food(String _name,int _calories){
		name=_name;
		calories=_calories;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
}
