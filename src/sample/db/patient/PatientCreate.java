package sample.db.patient;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PatientCreate {
	public static void main(String args[]) {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/patient.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			
			// Create tables: begin
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE patients "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT ON DELETE CASCADE,"
					   + " name     TEXT     NOT NULL, "
					   + " surname  TEXT	 NULL"
					   + "room_nº   INTEGER  NOT NULL"
					   + "id_doctor INTEGER REFERENCES doctor(id)"
					   + "id_chronic INTEGER REFERENCES chronic(id)"
					   + "id_illness INTEGER REFERENCES ILLNESS(id)"
					   + "photo 	BLOB)";
			stmt1.executeUpdate(sql1);
			stmt1.close();
			Statement stmt2 = c.createStatement();
			String sql2 = "CREATE TABLE visitors "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " id_pat INTEGER REFERENCES patients(id))";
			stmt2.executeUpdate(sql2);
			stmt2.close();
			Statement stmt3 = c.createStatement();
			String sql3 = "CREATE TABLE doctor "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name     TEXT     NOT NULL, "
					   + " surname  TEXT  	NOT NULL, "
					   + " field	TEXT NOT NULL DEFAULT 'nurse')";
			stmt3.executeUpdate(sql3);
			stmt3.close();
			Statement stmt4 = c.createStatement();
			String sql4 = "CREATE TABLE chronic "
					   + "(id	INTEGER PRIMARY KEY AUTOINCREMENT"
					   + "name	TEXT NULL)";
			stmt4.executeUpdate(sql4);
			stmt4.close();
			Statement stmt5=c.createStatement();
			String sql5="CREATE TABLE food("
		+ "id	INTEGER PRIMARY KEY AUTOINCREMENT "
		+ "calories	REAL NULL"
		+ "name	TEXT NULL"
		+ "id_salt REFERENCES salt(id))";
			stmt5.executeUpdate(sql5);
			stmt5.close();
			Statement stmt6=c.createStatement();
			String sql6="CREATE TABLE schedule("
					+ "id	INTEGER PRIMARY KEY AUTOINCREMENT"
					+ "day	TEXT NOT NULL"
					+ "start	TIME NULL"
					+ "end	TIME NULL)";
			stmt6.executeUpdate(sql6);
			stmt6.close();
			Statement stmt7=c.createStatement();
			String sql7="CREATE TABLE medication("
					+ "id	INTEGER PRIMARYKEY AUTOINCREMENT"
					+ "name	TEXT NOT NULL"
					+ "actv_agent	TEXT NOT NULL"
					+ "id_food REFERENCES food(id))";
			stmt7.executeUpdate(sql7);
			stmt7.close();
			Statement stmt8=c.createStatement();
			String sql8="CREATE TABLE salt("
					+ "id	INTEGER PRIMARY KEY AUTOINCREMENT"
					+ "amount	TEXT NULL"
					+ "min_range	INTEGER NOT NULL"
					+ "max_range	INTEGER NULL)";
			stmt8.executeUpdate(sql8);
			stmt8.close();
			Statement stmt9=c.createStatement();
			String sql9="CREATE TABLE illness("
					+ "id	INTEGER PRIMARY KEY AUTOINCREMENT"
					+ "name	TEXT)";
			stmt9.executeUpdate(sql9);
			stmt9.close();
			Statement stmt10=c.createStatement();
			String sql10="CREATE TABLE P_food("
					+ "id_pat	REFERENCES patients(id)"
					+ "id_food	REFERENCES food(id)"
					+ "date		DATE NOT NULL"
					+ "PRIMARY KEY(id_pat,id_food))";
			stmt10.executeUpdate(sql10);
			stmt10.close();
			Statement stmt11=c.createStatement();
			String sql11="CREATE TABLE P_medication("
					+ "	id_pat	REFERENCES patients(id) "
					+ "	id_medication	REFERENCES medication(id) "
					+ "PRIMARY KEY(id_pat,id_medication))";
			stmt11.executeUpdate(sql11);
			stmt11.close();
			Statement stmt12=c.createStatement();
			String sql12="CREATE TABLE P_doctor("
					+ "	id_pat	REFERENCES patients(id) "
					+ "	id_doc	REFERENCES doctor(id) "
					+ "PRIMARY KEY(id_pat,id_doc))";
			stmt12.executeUpdate(sql12);
			stmt12.close();
			Statement stmt13=c.createStatement();
			String sql13="CREATE TABLE D_schedule("
					+ "	id_schedule	REFERENCES schedule(id) "
					+ "	id_doctor	REFERENCES doctor(id) "
					+ "PRIMARY KEY(id_doctor,id_schedule))";
			stmt13.executeUpdate(sql13);
			stmt13.close();
			Statement stmt14=c.createStatement();
			String sql14="CREATE TABLE V_schedule("
					+ "	id_visistors	REFERENCES visitors(id)"
					+ "	id_schedule		REFERENCES schedule(id) "
					+ "PRIMARY KEY(id_visitors,id_schedule))";
			stmt14.executeUpdate(sql14);
			stmt14.close();
			Statement stmt15=c.createStatement();
			String sql15="CREATE TABLE C_food("
					+ "	id_food	REFERENCES food(id) "
					+ "	id_Chronic	REFERENCES chronic(id) "
					+ "PRIMARY KEY(id_food,id_chronic))";
			stmt15.executeUpdate(sql15);
			stmt15.close();
			Statement stmt16=c.createStatement();
			String sql16="CREATE TABLE C_medication("
					+ "	id_chronic REFERENCES chronic(id) "
					+ "	id_medication	REFERENCES medication(id)"
					+ "PRIMARY KEY(id_chronic,id_medication))";
			stmt16.executeUpdate(sql16);
			stmt16.close();
			Statement stmt17=c.createStatement();
			String sql17="CREATE TABLE I_medication("
					+ "	id_illness	REFERENCES illness(id) "
					+ "	id_medication	REFERENCES medication(id)"
					+ "PRIMARY KEY(id_illness,id_medication))";
			stmt17.executeUpdate(sql17);
			stmt17.close();
			System.out.println("Tables created.");
			// Create table: end
			
			// - Set initial values for the Primary Keys
			// - Don't try to understand this until JPA is explained
			// This is usually not needed, since the initial values
			// are set when the first row is inserted, but since we
			// are using JPA and JDBC in the same project, and JPA
			// needs an initial value, we do this.
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('departments', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('employees', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('reports', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			stmtSeq.close();
			
			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
