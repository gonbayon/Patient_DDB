package sample.db.patient;



import java.sql.*;
import java.sql.Date;
import java.util.*;

import sample.db.pojos.Patient;


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
public boolean createTable() throws SQLException{
	Statement stmt1 = c.createStatement();
	String sql1 = "CREATE TABLE patient"
			   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT ,"
			   + " name     TEXT     NOT NULL, "
			   + " surname  TEXT	 NULL, "
			   + "room_n   INTEGER  NOT NULL)";
	stmt1.executeUpdate(sql1);
	stmt1.close();
	return true;
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
public void deletePatient(int id) throws SQLException{
	Statement stmt = c.createStatement();
	String sql = "DELETE FROM employees WHERE id=?";
	PreparedStatement prep = c.prepareStatement(sql);
	prep.setInt(1, id);
	prep.executeUpdate();
}
public List<Patient> select() throws SQLException{
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

}
