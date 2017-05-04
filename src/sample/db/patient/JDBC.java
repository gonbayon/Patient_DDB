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
			+ "agent TEXT NULL )";
	s.executeUpdate(sql);
	s.close();
}
public void createTableVisit() throws SQLException{
	Statement s=c.createStatement();
	String sql="CREATE TABLE visitor"
			+ "(id	INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "name	TEXT NULL)";
	s.executeUpdate(sql);
	s.close();
}
public void createTableIll() throws SQLException{
	Statement s=c.createStatement();
	String sql="CREATE TABLE illness"
			+ "(id	INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "name	TEXT NULL)";
	s.executeUpdate(sql);
	s.close();
}
public void createTableChronic() throws SQLException{
	Statement s=c.createStatement();
	String sql="CREATE TABLE chronic"
			+ "(id	INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "name	TEXT NULL)";
	s.executeUpdate(sql);
	s.close();
}
public void createTableSchedule() throws SQLException{
	Statement s=c.createStatement();
	String sql="CREATE TABLE Schedule"
			+ "(id	INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ "start TEXT NULL,"
			+ "end TEXT NULL,"
			+ "day TEXT NULL)";
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
public void insertSche(Schedule sche) throws SQLException{
	String sql = "INSERT INTO schedule (start, end, day) "
			+ "VALUES (?,?,?);";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setString(1, sche.getStart());
	prep.setString(2, sche.getEnd());
	prep.setString(3, sche.getDay());
	prep.executeUpdate();
	prep.close();
}
public void insertMed(Medication med) throws SQLException{
	String sql = "INSERT INTO medication (name, agent) "
			+ "VALUES (?,?);";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setString(1, med.getName());
	prep.setString(2, med.getAgent());
	prep.executeUpdate();
	prep.close();
}
public void insertVis(Visitor vis) throws SQLException{
	String sql = "INSERT INTO visitor (name) "
			+ "VALUES (?);";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setString(1, vis.getName());
	prep.executeUpdate();
	prep.close();
}
public void insertIll(Illness ill) throws SQLException{
	String sql = "INSERT INTO illness (name) "
			+ "VALUES (?);";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setString(1, ill.getName());
	prep.executeUpdate();
	prep.close();
}
public void insertChronic(Chronic ch) throws SQLException{
	String sql = "INSERT INTO illness (name) "
			+ "VALUES (?);";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setString(1, ch.getName());
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
public void deleteSchedule(int id) throws SQLException{
	Statement stmt = c.createStatement();
	String sql = "DELETE FROM schedule WHERE id=?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setInt(1,id);
	prep.executeUpdate();
}
public void deleteVisitor(int id) throws SQLException{
	Statement stmt = c.createStatement();
	String sql = "DELETE FROM visitor WHERE id=?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setInt(1,id);
	prep.executeUpdate();
}
public void deleteIllness(int id) throws SQLException{
	Statement stmt = c.createStatement();
	String sql = "DELETE FROM illness WHERE id=?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setInt(1,id);
	prep.executeUpdate();
}
public void deleteChronic(int id) throws SQLException{
	Statement stmt = c.createStatement();
	String sql = "DELETE FROM chronic WHERE id=?";
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
public List<Visitor> selectV() throws SQLException{
	Statement stmt = c.createStatement();
	String sql = "SELECT * FROM visitor";
	ResultSet rs = stmt.executeQuery(sql);
	List<Visitor>show=new LinkedList();
	while (rs.next()) {
		int id=rs.getInt("id");
		String name = rs.getString("name");
		Visitor v = new Visitor(name);
		v.setId(id);
		show.add(v);
	}
	rs.close();
	stmt.close();
	return show;
}
public List<Schedule> selectSh() throws SQLException{
	Statement stmt = c.createStatement();
	String sql = "SELECT * FROM schedule";
	ResultSet rs = stmt.executeQuery(sql);
	List<Schedule>show=new LinkedList();
	while (rs.next()) {
		int id=rs.getInt("id");
		String start = rs.getString("start");
		String end = rs.getString("end");
		String day = rs.getString("day");
		Schedule s = new Schedule(start, end, day);
		s.setId(id);
		show.add(s);
	}
	rs.close();
	stmt.close();
	return show;
}
public List<Illness> selectI() throws SQLException{
	Statement stmt = c.createStatement();
	String sql = "SELECT * FROM illness";
	ResultSet rs = stmt.executeQuery(sql);
	List<Illness>show=new LinkedList();
	while (rs.next()) {
		int id=rs.getInt("id");
		String name = rs.getString("name");
		Illness i= new Illness(name);
		i.setId(id);
		show.add(i);
	}
	rs.close();
	stmt.close();
	return show;
}
public List<Chronic> selectC() throws SQLException{
	Statement stmt = c.createStatement();
	String sql = "SELECT * FROM chronic";
	ResultSet rs = stmt.executeQuery(sql);
	List<Chronic>show=new LinkedList();
	while (rs.next()) {
		int id=rs.getInt("id");
		String name = rs.getString("name");
		Chronic i= new Chronic(name);
		i.setId(id);
		show.add(i);
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
public void dropTableV() throws SQLException{
	Statement stmt1 = c.createStatement();
	String sql1 = "DROP TABLE visitor";
	stmt1.executeUpdate(sql1);
	stmt1.close();	
}
public void dropTableI() throws SQLException{
	Statement stmt1 = c.createStatement();
	String sql1 = "DROP TABLE illness";
	stmt1.executeUpdate(sql1);
	stmt1.close();	
}
public void dropTableC() throws SQLException{
	Statement stmt1 = c.createStatement();
	String sql1 = "DROP TABLE chronic";
	stmt1.executeUpdate(sql1);
	stmt1.close();	
}
public void dropTableSh() throws SQLException{
	Statement stmt1 = c.createStatement();
	String sql1 = "DROP TABLE schedule";
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
public Medication searchMed(int id) throws SQLException{
	Medication med=null;
	String sql = "SELECT * FROM medication WHERE id = ?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setInt(1, id);
	ResultSet rs = prep.executeQuery();
	while (rs.next()) {
		int _id = rs.getInt("id");
		String agent= rs.getString("agent");
		String name = rs.getString("name");
		med = new Medication( name, agent);
		med.setId(id);
}
	rs.close();
	prep.close();
	return med;
}
public Schedule searchSche(int id) throws SQLException{
	Schedule s=null;
	String sql = "SELECT * FROM schedule WHERE id = ?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setInt(1, id);
	ResultSet rs = prep.executeQuery();
	while (rs.next()) {
		int _id = rs.getInt("id");
		String start= rs.getString("start");
		String end = rs.getString("end");
		String day = rs.getString("day");
		s = new Schedule( start,end,day);
		s.setId(id);
}
	rs.close();
	prep.close();
	return s;
}
public Visitor searchVis(int id) throws SQLException{
	Visitor vis=null;
	String sql = "SELECT * FROM visitor WHERE id = ?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setInt(1, id);
	ResultSet rs = prep.executeQuery();
	while (rs.next()) {
		int _id = rs.getInt("id");
		String name = rs.getString("name");
		vis = new Visitor( name);
		vis.setId(id);
}
	rs.close();
	prep.close();
	return vis;
}
public Illness searchIll(int id) throws SQLException{
	Illness ill=null;
	String sql = "SELECT * FROM illness WHERE id = ?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setInt(1, id);
	ResultSet rs = prep.executeQuery();
	while (rs.next()) {
		int _id = rs.getInt("id");
		String name = rs.getString("name");
		ill = new Illness(name);
		ill.setId(id);
}
	rs.close();
	prep.close();
	return ill;
}
public Chronic searchChro(int id) throws SQLException{
	Chronic ch=null;
	String sql = "SELECT * FROM illness WHERE id = ?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setInt(1, id);
	ResultSet rs = prep.executeQuery();
	while (rs.next()) {
		int _id = rs.getInt("id");
		String name = rs.getString("name");
		ch = new Chronic(name);
		ch.setId(id);
}
	rs.close();
	prep.close();
	return ch;
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
	String sql = "UPDATE salt SET min=?, max=? WHERE id=?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setInt(1, min);
	prep.setInt(2, max);
	prep.setInt(3, id);
	prep.executeUpdate();
}
public void updateSchedule(String start, String end, String day, int id) throws SQLException{
	String sql = "UPDATE schedule SET start=?, end=?, day=? WHERE id=?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setString(1, start);
	prep.setString(2, end);
	prep.setString(3, day);
	prep.setInt(4, id);
	prep.executeUpdate();
}
}

