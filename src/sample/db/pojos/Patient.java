package sample.db.pojos;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import sample.db.*;

public class Patient implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3000387307546279723L;
	private int id,room_n;
	private String name,surname;
	private Doctor In_charge;
	private List <Doctor> lookedafter;
	private Illness illness;
	private Chronic chronic;
	private List <Food> food;
	private List <Visitor> visitor;
	
	public Patient(){
		lookedafter=new LinkedList<>();
		food=new LinkedList<>();
		visitor=new LinkedList<>();
	}
	public Patient (String _name, String _surname, int n){
		name=_name;
		surname=_surname;
		room_n=n;
		lookedafter=new LinkedList<>();
		food=new LinkedList<>();
		visitor=new LinkedList<>();
	}
	public Patient(String _name,String _surname,int room
			,Doctor _doctor, List <Doctor> _look
			,Illness _illness,
			Chronic _chronic,List <Food> _food,List <Visitor> _visitor){
		name=_name;
		surname=_surname;
		room_n=room;
		In_charge=_doctor;
		lookedafter=_look;
		illness=_illness;
		chronic=_chronic;
		food=_food;
		visitor=_visitor;
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
		Patient other = (Patient) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public int getId() {
		return id;
	}
	public int getRoom_n() {
		return room_n;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public void setId(int _id){
		id=_id;
	}
	public void setRoom_nº(int room_n) {
		this.room_n = room_n;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Doctor getIn_charge() {
		return In_charge;
	}
	public void setIn_charge(Doctor in_charge) {
		In_charge = in_charge;
	}
	public List<Doctor> getLookedafter() {
		return lookedafter;
	}
	public void setLookedafter(List<Doctor> lookedafter) {
		this.lookedafter = lookedafter;
	}
	public Chronic getChronic() {
		return chronic;
	}
	public void setChronic(Chronic chronic) {
		this.chronic = chronic;
	}
	public String showChronic(){
		if(chronic!=null){
			return "Name: "+chronic.getName()+" ,Medicines: "+chronic.getMedName();

		}
		else
			return "";
	}
	public List<Food> getFood() {
		return food;
	}
	public String getFoodName(){
		try{
			Food f=null;
			String s=" ",st="";
			String[] array=new String [food.size()];
			for(int i=0;i<food.size();i++){
				f=food.get(i);
				s=f.getName();
				st=f.getDate();
				array[i]=s+" "+st;
			}
			return Arrays.toString(array);
		}catch(ArrayIndexOutOfBoundsException Ai){
			return "";
		}
	}
	public void setFood(List<Food> food) {
		this.food = food;
	}
	public List<Visitor> getVisitor() {
		return visitor;
	}
	public void setVisitor(List<Visitor> visitor) {
		this.visitor = visitor;
	}
	public String showVisitors(){
		if(visitor.size()<1) return "";
		else {
			Visitor vis=null;
			String s=" ",n="",sch;
			List<Schedule> st=new LinkedList();
			Schedule sche=null;
			String[] array=new String [visitor.size()];
			String[] ss=null;;
			for(int i=0;i<visitor.size();i++){
				vis=visitor.get(i);
				n=vis.getName();
				s=vis.getSurname();
				st=vis.getSche();
				if(st.size()>0){
					ss=new String[st.size()];
					for (int j = 0; j <st.size(); j++) {
						ss[j]=st.get(j).getScheduleprettyly();
					}
					array[i]=n+" "+s+" "+Arrays.toString(ss);
				}
				else array[i]=n+" "+s;
			}
			return Arrays.toString(array);
		}
	}
	public String showDoc(){
		String s1=In_charge.getName();
		String s2=In_charge.getSurname();
		return s1+" "+s2;
	}
	public Illness getIllness() {
		return illness;
	}
	public void setIllness(Illness illness) {
		this.illness = illness;
	}
	public String showIllness(){
		if(illness!=null){
			return "Name: "+illness.getName()+" ,Medicines: "+illness.getMedName();
		}
		else
			return "";
	}
	public String toString(){
		if( food.size()<1){
			return "	\nId:"+id+"\nName: "+name+"\nSurname: "+surname+"\nRoom: "+room_n+"\n";
		}
		else
			return "	\nId:"+id+"\nName: "+name+"\nSurname: "+surname+"\nRoom: "+room_n+"\nFood: "+getFoodName()+"\nIllness: "+showIllness()+""
					+ "\nChronic illness: "+showChronic()+"\nVisitors: "+showVisitors()+"\nDoctor: "+showDoc();
	}

}
