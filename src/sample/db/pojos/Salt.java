package sample.db.pojos;
import java.io.Serializable;
enum Amount{LOW,MEDIUM,HIGH};
public class Salt implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -21877968393831801L;
	private int id;
	private float min,max;
	private Amount ammo;
	
	public Salt(){
		
	}
	public Salt(float min,float max){
		this.min=min;
		this.max=max;
		if(min==0 && max==0){
			ammo=null;
		}
		else if (min<=1.5 && max<1.5){
			ammo=ammo.LOW;
		}
		else if((min<=1.5 && max>1.5) || (min<=2.9 && max<2.9)){
			ammo=ammo.MEDIUM;
		}
		else ammo=ammo.HIGH;
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
		Salt other = (Salt) obj;
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
	public float getMin() {
		return min;
	}
	public void setMin(float min) {
		this.min = min;
	}
	public float getMax() {
		return max;
	}
	public void setMax(float max) {
		this.max = max;
	}
	public Amount getAmmo() {
		return ammo;
	}
	public void setAmmo(Amount ammo) {
		this.ammo = ammo;
	}
	public String toString(){
		return "\nId: "+id+"\nAmmount: "+ammo+"\nMin: "+min+"\nMax: "+max;
	}
}
