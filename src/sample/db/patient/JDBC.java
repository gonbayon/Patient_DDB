package sample.db.patient;



import java.sql.*;
import java.util.*;

import sample.db.pojos.*;

public class JDBC {
String name;
Connection c;

public JDBC(String n){
	name=n;
}
public void connect(){
	try {
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:./db/"+name +".db");
		c.createStatement().execute("PRAGMA foreign_keys=ON");
	} catch (Exception e) {
		e.printStackTrace();
	}
}
public void disconnect() throws SQLException{
	c.close();
}
public void createTablePat() throws SQLException{
	Statement stmt1 = c.createStatement();
	String sql1 = "CREATE TABLE patient"
			   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT ,"
			   + " name     TEXT     NOT NULL, "
			   + " surname  TEXT	 NULL, "
			   + "room_n   INTEGER  NOT NULL)";
	stmt1.executeUpdate(sql1);
	stmt1.close();
}
public void createTableFood() throws SQLException{
	Statement stmt5=c.createStatement();
	String sql5="CREATE TABLE food"
+ "(id	INTEGER PRIMARY KEY AUTOINCREMENT, "
+ "calories	REAL NULL, "
+ "name	TEXT NULL,"
+ "id_salt REFERENCES salt(id))";
	stmt5.executeUpdate(sql5);
	stmt5.close();
}
public void createTableSalt() throws SQLException{
	Statement stmt8=c.createStatement();
	String sql8="CREATE TABLE salt"
			+ "(id	INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "amount	TEXT NULL, "
			+ "min	REAL NOT NULL, "
			+ "max	REAL NULL)";
	stmt8.executeUpdate(sql8);
	stmt8.close();
}
public void createTableMedic() throws SQLException{
	Statement s=c.createStatement();
	String sql="CREATE TABLE medication"
			+ "(id	INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "name	TEXT NULL, "
			+ "agent TEXT NULL, ";
	s.executeUpdate(sql);
	s.close();
}
public void assignSaltFood(int f,int s) throws SQLException{
	String sql="UPDATE food SET id_salt=? WHERE id=?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setInt(1, s);
	prep.setInt(2, f);
	prep.executeUpdate();
}
public void insertPatient(Patient patient) throws SQLException{
	String sql = "INSERT INTO patient (name, surname , room_n) "
			+ "VALUES (?,?,?);";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setString(1, patient.getName());
	prep.setString(2, patient.getSurname());
	prep.setInt(3, patient.getRoom_n());
	prep.executeUpdate();
	prep.close();
}
public void insertFood(Food food) throws SQLException{
	String sql = "INSERT INTO food (name, calories) "
			+ "VALUES (?,?);";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setString(1, food.getName());
	prep.setFloat(2, food.getCalories());
	prep.executeUpdate();
	prep.close();
}
public void insertSalt(Salt s) throws SQLException{
	String sql = "INSERT INTO salt (min,max) "
			+ "VALUES (?,?);";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setFloat(1, s.getMin());
	prep.setFloat(2, s.getMax());
	prep.executeUpdate();
	prep.close();
}
public void deletePatient(int id) throws SQLException{
	Statement stmt = c.createStatement();
	String sql = "DELETE FROM patient WHERE id=?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setInt(1,id);
	prep.executeUpdate();
}
public void deleteFood(int id) throws SQLException{
	Statement stmt = c.createStatement();
	String sql = "DELETE FROM food WHERE id=?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setInt(1,id);
	prep.executeUpdate();
}
public void deleteMedicat(int id) throws SQLException{
	Statement stmt = c.createStatement();
	String sql = "DELETE FROM medication WHERE id=?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setInt(1,id);
	prep.executeUpdate();
}
public List<Patient> selectP() throws SQLException{
	Statement stmt = c.createStatement();
	String sql = "SELECT * FROM patient";
	ResultSet rs = stmt.executeQuery(sql);
	List<Patient>show=new LinkedList();
	while (rs.next()) {
		int id=rs.getInt("id");
		int n = rs.getInt("room_n");
		String name = rs.getString("name");
		String surname = rs.getString("surname");
		Patient patient = new Patient(name,surname,n );
		patient.setId(id);
		show.add(patient);
	}
	rs.close();
	stmt.close();
	return show;
}
public Salt getSaltid(int i) throws SQLException{
	Statement stmt = c.createStatement();
	String sql1= "SELECT * FROM salt WHERE id ="+i;
	ResultSet rs1 = stmt.executeQuery(sql1);
	rs1.next();
	int id=rs1.getInt("id");
	float mn = rs1.getFloat("min");
	float mx = rs1.getFloat("max");
	Salt f = new Salt(mn,mx);
	f.setId(id);
	rs1.close();
	stmt.close();
	return f;
}
public List<Food> selectF() throws SQLException{
	Statement stmt = c.createStatement();
	String sql = "SELECT * FROM food";
	ResultSet rs = stmt.executeQuery(sql);
	List<Food>show=new LinkedList();
	while (rs.next()) {
		int id=rs.getInt("id");
		float n = rs.getFloat("calories");
		String name = rs.getString("name");
		Food f = new Food(name,n);
		f.setId(id);
		try{
		f.setSalt(getSaltid(rs.getInt("id_salt")));
		}catch(NullPointerException np){
		}
		show.add(f);
	}
	rs.close();
	stmt.close();
	return show;
}
public List<Medication> selectM() throws SQLException{
	Statement stmt = c.createStatement();
	String sql = "SELECT * FROM medication";
	ResultSet rs = stmt.executeQuery(sql);
	List<Medication>show=new LinkedList();
	while (rs.next()) {
		int id=rs.getInt("id");
		String name = rs.getString("name");
		String agent = rs.getString("agent");
		Medication m = new Medication(name, agent);
		m.setId(id);
		show.add(m);
	}
	rs.close();
	stmt.close();
	return show;
}
public List <Salt> selectS() throws SQLException{
	Statement stmt = c.createStatement();
	String sql = "SELECT * FROM salt";
	ResultSet rs = stmt.executeQuery(sql);
	List<Salt>show=new LinkedList();
	while (rs.next()) {
		int id=rs.getInt("id");
		float mn = rs.getFloat("min");
		float mx = rs.getFloat("max");
		Salt f = new Salt(mn,mx);
		f.setId(id);
		show.add(f);
	}
	rs.close();
	stmt.close();
	return show;
}
public void dropTableP() throws SQLException{
	Statement stmt1 = c.createStatement();
	String sql1 = "DROP TABLE patient";
	stmt1.executeUpdate(sql1);
	stmt1.close();
}
public void dropTableF() throws SQLException{
	Statement stmt1 = c.createStatement();
	String sql1 = "DROP TABLE food";
	stmt1.executeUpdate(sql1);
	stmt1.close();	
}
public void dropTableS() throws SQLException{
	Statement stmt1 = c.createStatement();
	String sql1 = "DROP TABLE salt";
	stmt1.executeUpdate(sql1);
	stmt1.close();	
}
public void dropTableM() throws SQLException{
	Statement stmt1 = c.createStatement();
	String sql1 = "DROP TABLE medication";
	stmt1.executeUpdate(sql1);
	stmt1.close();	
}
public Patient searchPatient(int room) throws SQLException{
	Patient patient=null;
	String sql = "SELECT * FROM patient WHERE room_n = ?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setInt(1, room);
	ResultSet rs = prep.executeQuery();
	while (rs.next()) {
		int id = rs.getInt("id");
		String name = rs.getString("name");
		String surname = rs.getString("surname");
		int room_n = rs.getInt("room_n");
		patient = new Patient( name, surname,room_n);
		patient.setId(id);
}
	rs.close();
	prep.close();
	return patient;
}
public Food searchFood(int id) throws SQLException{
	Food food=null;
	String sql = "SELECT * FROM food WHERE id = ?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setInt(1, id);
	ResultSet rs = prep.executeQuery();
	while (rs.next()) {
		int _id = rs.getInt("id");
		Float calories = rs.getFloat("calories");
		String name = rs.getString("name");
		food = new Food( name, calories);
		food.setId(id);
}
	rs.close();
	prep.close();
	return food;
}
public void updatePatient(int id,int room) throws SQLException{
	String sql = "UPDATE patient SET room_n=? WHERE id=?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setInt(1, room);
	prep.setInt(2, id);
	prep.executeUpdate();
}
public void updateFood(int id,int calories) throws SQLException{
	String sql = "UPDATE food SET calories=? WHERE id=?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setInt(1, calories);
	prep.setInt(2, id);
	prep.executeUpdate();
}
public void updateSalt(int id,int min,int max) throws SQLException{
	String sql = "UPDATE salt SET min=? WHERE id=?";
	String sql1 = "UPDATE salt SET max=? WHERE id=?";
	PreparedStatement prep = c.prepareStatement(sql);
	PreparedStatement prep1 = c.prepareStatement(sql1	);
	prep.setInt(1, min);
	prep.setInt(2, max);
	prep.setInt(3, id);
	prep.executeUpdate();
}

}

