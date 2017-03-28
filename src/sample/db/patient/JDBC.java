package sample.db.patient;



import java.sql.*;
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
public boolean insertPatient(Patient patient) throws SQLException{
	Statement stmt = c.createStatement();
	String sql = "INSERT INTO patient (name, surname,room_n) "
			+ "VALUES ('" + patient.getName() + "', '" + patient.getSurname()	+ "', "+patient.getRoom_n()+");";
	stmt.executeUpdate(sql);
	stmt.close();
	return true;
}
public List<Patient> select() throws SQLException{
	Statement stmt = c.createStatement();
	String sql = "SELECT * FROM patient";
	ResultSet rs = stmt.executeQuery(sql);
	List<Patient>show=new LinkedList();
	while (rs.next()) {
		int n = rs.getInt("room_n");
		String name = rs.getString("name");
		String surname = rs.getString("surname");
		Patient patient = new Patient(name,surname,n );
		show.add(patient);
	}
	rs.close();
	stmt.close();
	return show;
}

}

