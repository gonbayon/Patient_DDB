package sample.db.patient;

import java.sql.*;
public class PatientConnect {
	
	public static void main(String args[]) {
		try {
			// Open database connection
			Class.forName("org.sqlite.PATIENT");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/patient.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			
			// Here is where I do things with the database
			
			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
}
}
