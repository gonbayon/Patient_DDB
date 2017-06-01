package sample.db.patient;

import java.sql.*;
import java.util.*;

import sample.db.pojos.*;

public class JDBC {
	String name;
	Connection c;

	public JDBC(String n) {
		name = n;
	}

	public void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/" + name + ".db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnect() throws SQLException {
		c.close();
	}
	public void createTablePat() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "CREATE TABLE patient" + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT ,"
				+ " name     TEXT     NOT NULL, " + " surname  TEXT	 NULL, " + "room_n   INTEGER  NOT NULL,"
				+ "id_ill	REFERENCES illness(id)," + "id_chro 	REFERENCES chronic(id),"
				+ "id_doc	REFERENCES doctor(id)" + "	)";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public void createTableFood() throws SQLException {
		Statement stmt5 = c.createStatement();
		String sql5 = "CREATE TABLE food" + "(id	INTEGER PRIMARY KEY AUTOINCREMENT, " + "calories	REAL NULL, "
				+ "name	TEXT NULL," + "id_salt REFERENCES salt(id))";
		stmt5.executeUpdate(sql5);
		stmt5.close();
	}

	public void createTableDoctor() throws SQLException {
		Statement stmt5 = c.createStatement();
		String sql5 = "CREATE TABLE doctor" + "(id	INTEGER PRIMARY KEY AUTOINCREMENT, " + "name	TEXT NULL, "
				+ "surname	TEXT NULL," + "field TEXT NULL)";
		stmt5.executeUpdate(sql5);
		stmt5.close();
	}

	public void createTableSalt() throws SQLException {
		Statement stmt8 = c.createStatement();
		String sql8 = "CREATE TABLE salt" + "(id	INTEGER PRIMARY KEY AUTOINCREMENT, " + "amount	TEXT NULL, "
				+ "min	REAL NOT NULL, " + "max	REAL NULL)";
		stmt8.executeUpdate(sql8);
		stmt8.close();
	}

	public void createTableMedic() throws SQLException {
		Statement s = c.createStatement();
		String sql = "CREATE TABLE medication" + "(id	INTEGER PRIMARY KEY AUTOINCREMENT, " + "name	TEXT NULL, "
				+ "agent TEXT NULL )";
		s.executeUpdate(sql);
		s.close();
	}

	public void createTableVisit() throws SQLException {
		Statement s = c.createStatement();
		String sql = "CREATE TABLE visitor" + "(id	INTEGER PRIMARY KEY AUTOINCREMENT, " + "name	TEXT NULL,"
				+ "surname TEXT NULL," + "id_pat REFERENCES patient(id))";
		s.executeUpdate(sql);
		s.close();
	}

	public void createTableIll() throws SQLException {
		Statement s = c.createStatement();
		String sql = "CREATE TABLE illness" + "(id	INTEGER PRIMARY KEY AUTOINCREMENT, " + "name	TEXT NULL)";
		s.executeUpdate(sql);
		s.close();
	}

	public void createTableChronic() throws SQLException {
		Statement s = c.createStatement();
		String sql = "CREATE TABLE chronic" + "(id	INTEGER PRIMARY KEY AUTOINCREMENT, " + "name	TEXT NULL)";
		s.executeUpdate(sql);
		s.close();
	}

	public void createTableSchedule() throws SQLException {
		Statement s = c.createStatement();
		String sql = "CREATE TABLE schedule" + "(id	INTEGER PRIMARY KEY AUTOINCREMENT, " + "start TEXT NULL,"
				+ "end TEXT NULL," + "day TEXT NULL)";
		s.executeUpdate(sql);
		s.close();
	}

	public void createTablePatFood() throws SQLException {
		Statement stmt9 = c.createStatement();
		String sql9 = "CREATE TABLE p_food" + "(id_pat	INTEGER NOT NULL REFERENCES patient (id) , "
				+ "id_food	INTEGER NOT NULL REFERENCES food (id), " + "date	TEXT NULL,"
				+ "PRIMARY KEY(id_pat,id_food))";
		stmt9.executeUpdate(sql9);
		stmt9.close();
	}

	public void createTablePatDoc() throws SQLException {
		Statement stmt9 = c.createStatement();
		String sql9 = "CREATE TABLE p_doc" + "(id_pat	INTEGER NOT NULL REFERENCES patient (id) , "
				+ "id_doc	INTEGER NOT NULL REFERENCES doctor (id), " + "PRIMARY KEY(id_pat,id_doc))";
		stmt9.executeUpdate(sql9);
		stmt9.close();
	}

	public void createTableVisSche() throws SQLException {
		Statement stmt9 = c.createStatement();
		String sql9 = "CREATE TABLE v_sche" + "(id_vis	INTEGER NOT NULL REFERENCES visitor (id) , "
				+ "id_sche	INTEGER NOT NULL REFERENCES Schedule (id), " + "PRIMARY KEY(id_vis,id_sche))";
		stmt9.executeUpdate(sql9);
		stmt9.close();
	}
	
	public void createTableDocSche() throws SQLException{
		Statement stmt9 = c.createStatement();
		String sql9 = "CREATE TABLE d_sche" + "(id_doc	INTEGER NOT NULL REFERENCES doctor (id) , "
				+ "id_sche	INTEGER NOT NULL REFERENCES Schedule (id), " + "PRIMARY KEY(id_doc,id_sche))";
		stmt9.executeUpdate(sql9);
		stmt9.close();
	}

	public void createTableChroFood() throws SQLException {
		Statement stmt9 = c.createStatement();
		String sql9 = "CREATE TABLE chro_food" + "(id_chro	INTEGER NOT NULL REFERENCES chronic (id) , "
				+ "id_food INTEGER NOT NULL REFERENCES food (id), " + "PRIMARY KEY(id_chro,id_food))";
		stmt9.executeUpdate(sql9);
		stmt9.close();
	}

	public void createTableIllMed() throws SQLException {
		Statement stmt9 = c.createStatement();
		String sql9 = "CREATE TABLE ill_med" + "(id_ill	INTEGER NOT NULL REFERENCES illness (id) , "
				+ "id_med INTEGER NOT NULL REFERENCES medication (id), " + "PRIMARY KEY(id_ill,id_med))";
		stmt9.executeUpdate(sql9);
		stmt9.close();
	}

	public void createTableChroMed() throws SQLException {
		Statement stmt9 = c.createStatement();
		String sql9 = "CREATE TABLE chro_medication" + "(id_chro	INTEGER NOT NULL REFERENCES chronic (id) , "
				+ "id_med INTEGER NOT NULL REFERENCES medication (id), " + "PRIMARY KEY(id_chro,id_med))";
		stmt9.executeUpdate(sql9);
		stmt9.close();
	}

	public void assignSaltFood(int f, int s) throws SQLException {
		String sql = "UPDATE food SET id_salt=? WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, s);
		prep.setInt(2, f);
		prep.executeUpdate();
	}

	public boolean assignPatientFood(int pat, int food, String when) throws SQLException {
		boolean booleano = true;
			String sql = "INSERT INTO p_food (id_pat, id_food ,date) " + "VALUES (?,?,?);";
			String sql1 = "SELECT id_chro FROM patient WHERE id=?;";
			PreparedStatement prep = c.prepareStatement(sql);
			PreparedStatement prep1 = c.prepareStatement(sql1);
			prep.setInt(1, pat);
			prep.setInt(2, food);
			prep.setString(3, when);
			prep1.setInt(1, pat);
			ResultSet rs = prep1.executeQuery();
			int chro = 0;
			while (rs.next()) {
				chro = rs.getInt("id_chro");
			}
			String sql3 = "SELECT id_food FROM chro_food WHERE id_chro=?;";
			PreparedStatement prep2 = c.prepareStatement(sql3);
			prep2.setInt(1, chro);
			ResultSet rs1 = prep2.executeQuery();
			List<Integer> id_f = new LinkedList<>();
			while (rs1.next()) {
				int i = rs1.getInt("id_food");
				id_f.add(i);
			}
			for (int j = 0; j < id_f.size(); j++) {
				if (id_f.get(j) == food) {
					booleano = false;
				}
			}
			prep1.close();
			prep2.close();
			if(booleano==true){
				prep.executeUpdate();
			}
			prep.close();
			return booleano;
	}

	public void assignPatientIllness(int p, String n) throws SQLException {
		String sql = "UPDATE patient SET id_ill=? WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		int id = obtainIllnessid(n);
		prep.setInt(1, id);
		prep.setInt(2, p);
		prep.executeUpdate();
	}

	public void assignPatientChronic(int p, String n) throws SQLException {
		String sql = "UPDATE patient SET id_chro=? WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		int id = obtainChronicid(n);
		prep.setInt(1, id);
		prep.setInt(2, p);
		prep.executeUpdate();
	}

	public void assignPatientVisitor(int p, int v) throws SQLException {
		String sql = "UPDATE visitor SET id_pat=? WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, p);
		prep.setInt(2, v);
		prep.executeUpdate();
		prep.close();
	}

	public void assignPatientDoctor(int p, String n, String surname) throws SQLException {
		String sql = "UPDATE patient SET id_doc=? WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		int id = obtainDoctorid(n, surname);
		prep.setInt(1, id);
		prep.setInt(2, p);
		prep.executeUpdate();
	}

	public void assignVisitorSchedule(int v, int s) throws SQLException {
		String sql = "INSERT INTO v_sche (id_vis, id_sche) " + "VALUES (?,?);";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, v);
		prep.setInt(2, s);
		prep.executeUpdate();
		prep.close();
	}
	public void assignDoctorSchedule(int d, int s) throws SQLException {
		String sql = "INSERT INTO d_sche (id_doc, id_sche) " + "VALUES (?,?);";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, d);
		prep.setInt(2, s);
		prep.executeUpdate();
		prep.close();
	}

	public void assignIllnessMedication(int ill, String med) throws SQLException {
		String sql = "INSERT INTO ill_med (id_ill, id_med) " + "VALUES (?,?);";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, ill);
		prep.setInt(2, searchMed(med).getId());
		prep.executeUpdate();
		prep.close();
	}

	public void assignChronicMedication(int ill, String med) throws SQLException {
		String sql = "INSERT INTO chro_medication (id_chro, id_med) " + "VALUES (?,?);";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, ill);
		prep.setInt(2, searchMed(med).getId());
		prep.executeUpdate();
		prep.close();
	}

	public void rejectFoodbyChronic(int id_c, int calories) throws SQLException {
		String sql = "INSERT INTO chro_food (id_chro,id_food) VALUES (?,?);";
		PreparedStatement prep = c.prepareStatement(sql);
		List<Integer> id_f = obtainFoodid(calories);
		for (int i = 0; i < id_f.size(); i++) {
			prep.setInt(1, id_c);
			prep.setInt(2, id_f.get(i));
			prep.executeUpdate();
		}
		prep.close();
	}

	public void insertPatient(Patient patient) throws SQLException {
		String sql = "INSERT INTO patient (name, surname , room_n) " + "VALUES (?,?,?);";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, patient.getName());
		prep.setString(2, patient.getSurname());
		prep.setInt(3, patient.getRoom_n());
		prep.executeUpdate();
		prep.close();
	}

	public void insertFood(Food food) throws SQLException {
		String sql = "INSERT INTO food (name, calories) " + "VALUES (?,?);";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, food.getName());
		prep.setFloat(2, food.getCalories());
		prep.executeUpdate();
		prep.close();
	}

	public void insertDoc(Doctor doc) throws SQLException {
		String sql = "INSERT INTO doctor (name,surname,field) " + "VALUES (?,?,?);";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, doc.getName());
		prep.setString(2, doc.getSurname());
		prep.setString(3, doc.getField());
		prep.executeUpdate();
		prep.close();
	}

	public void insertSche(Schedule sche) throws SQLException {
		String sql = "INSERT INTO schedule (start, end, day) " + "VALUES (?,?,?);";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, sche.getStart());
		prep.setString(2, sche.getEnd());
		prep.setString(3, sche.getDay());
		prep.executeUpdate();
		prep.close();
	}

	public void insertMed(Medication med) throws SQLException {
		String sql = "INSERT INTO medication (name, agent) " + "VALUES (?,?);";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, med.getName());
		prep.setString(2, med.getAgent());
		prep.executeUpdate();
		prep.close();
	}

	public void insertVis(Visitor vis) throws SQLException {
		String sql = "INSERT INTO visitor (name,surname) " + "VALUES (?,?);";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, vis.getName());
		prep.setString(2, vis.getSurname());
		prep.executeUpdate();
		prep.close();
	}

	public void insertIll(Illness ill) throws SQLException {
		String sql = "INSERT INTO illness (name) " + "VALUES (?);";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, ill.getName());
		prep.executeUpdate();
		prep.close();
	}

	public void insertChronic(Chronic ch) throws SQLException {
		String sql = "INSERT INTO chronic (name) " + "VALUES (?);";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, ch.getName());
		prep.executeUpdate();
		prep.close();
	}

	public void insertSalt(Salt s) throws SQLException {
		String sql = "INSERT INTO salt (min,max) " + "VALUES (?,?);";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setFloat(1, s.getMin());
		prep.setFloat(2, s.getMax());
		prep.executeUpdate();
		prep.close();
	}

	public void deletePatient(int id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "DELETE FROM patient WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, id);
		prep.executeUpdate();
	}

	public void deleteFood(int id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "DELETE FROM food WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, id);
		prep.executeUpdate();
	}

	public void deleteSalt(int id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "DELETE FROM salt WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, id);
		prep.executeUpdate();
	}

	public void deleteMedicat(int id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "DELETE FROM medication WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, id);
		prep.executeUpdate();
	}

	public void deleteDoctor(String name, String surname) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "DELETE FROM doctor WHERE name LIKE ? AND surname LIKE ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, name);
		prep.setString(2, surname);
		prep.executeUpdate();
	}

	public void deleteSchedule(int id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "DELETE FROM schedule WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, id);
		prep.executeUpdate();
	}

	public void deleteVisitorbyId(int id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "DELETE FROM visitor WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, id);
		prep.executeUpdate();
	}

	public void deleteVisitorbyName(String name, String surname) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "DELETE FROM visitor WHERE name LIKE ? AND surname LIKE ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, name);
		prep.setString(2, surname);
		prep.executeUpdate();
	}

	public void deleteIllness(int id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "DELETE FROM illness WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, id);
		prep.executeUpdate();
	}

	public void deleteChronic(int id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "DELETE FROM chronic WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, id);
		prep.executeUpdate();
	}

	public Patient getPatientbyId(int id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql1 = "SELECT * FROM patient WHERE id =" + id;
		ResultSet rs1 = stmt.executeQuery(sql1);
		rs1.next();
		int i = rs1.getInt("id");
		String name = rs1.getString("name");
		String sur = rs1.getString("surname");
		int r = rs1.getInt("room_n");
		Patient p = new Patient(name, sur, r);
		p.setId(i);
		rs1.close();
		stmt.close();
		return p;
	}

	public Salt getSaltbyid(int i) throws SQLException {
		Statement stmt = c.createStatement();
		String sql1 = "SELECT * FROM salt WHERE id =" + i;
		ResultSet rs1 = stmt.executeQuery(sql1);
		if (rs1.next()) {
			int id = rs1.getInt("id");
			float mn = rs1.getFloat("min");
			float mx = rs1.getFloat("max");
			Salt f = new Salt(mn, mx);
			f.setId(id);
			rs1.close();
			stmt.close();
			return f;
		}
		return null;
	}

	public Food getFoodbyId(int id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql1 = "SELECT * FROM food WHERE id =" + id;
		ResultSet rs1 = stmt.executeQuery(sql1);
		rs1.next();
		int idf = rs1.getInt("id");
		float cal = rs1.getFloat("calories");
		String nm = rs1.getString("name");
		Food f = new Food(nm, cal);
		f.setId(id);
		rs1.close();
		stmt.close();
		return f;
	}

	public Medication getMedbyId(int id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql1 = "SELECT * FROM medication WHERE id =" + id;
		ResultSet rs1 = stmt.executeQuery(sql1);
		rs1.next();
		int idf = rs1.getInt("id");
		String ag = rs1.getString("agent");
		String nm = rs1.getString("name");
		Medication m = new Medication(nm, ag);
		m.setId(id);
		rs1.close();
		stmt.close();
		return m;
	}

	public Schedule getSchebyId(int id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql1 = "SELECT * FROM schedule WHERE id =" + id;
		ResultSet rs1 = stmt.executeQuery(sql1);
		rs1.next();
		int idf = rs1.getInt("id");
		String st = rs1.getString("start");
		String end = rs1.getString("end");
		String wh = rs1.getString("day");
		Schedule s = new Schedule(st, end, wh);
		s.setId(idf);
		rs1.close();
		stmt.close();
		return s;
	}

	public Illness getIllbyId(int id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql1 = "SELECT * FROM illness WHERE id =" + id;
		ResultSet rs1 = stmt.executeQuery(sql1);
		List<Medication> ls = new LinkedList();
		Illness i = null;
		if (rs1.next()) {
			int _id = rs1.getInt("id");
			String name = rs1.getString("name");
			i = new Illness(name);
			i.setId(_id);
			ls = getMedIllness(_id);
			i.setTreats(ls);
			rs1.close();
			stmt.close();
		}
		return i;
	}
	
	public Doctor getdocbyId(int id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql1 = "SELECT * FROM doctor WHERE id =" + id;
		ResultSet rs1 = stmt.executeQuery(sql1);
		List<Medication> ls = new LinkedList();
		Doctor d = null;
		if (rs1.next()) {
			int _id = rs1.getInt("id");
			String name = rs1.getString("name");
			String sur=rs1.getString("surname");
			String f=rs1.getString("field");
			d =new Doctor(name, sur, f);
			d.setId(_id);
			rs1.close();
			stmt.close();
		}
		return d;
	}
	

	public Chronic getChrobyId(int id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql1 = "SELECT * FROM chronic WHERE id =" + id;
		ResultSet rs1 = stmt.executeQuery(sql1);
		List<Medication> ls = new LinkedList();
		Chronic c = null;
		if (rs1.next()) {
			int _id = rs1.getInt("id");
			String name = rs1.getString("name");
			c = new Chronic(name);
			c.setId(_id);
			ls = getMedChronic(_id);
			rs1.close();
			stmt.close();
		}
		return c;
	}

	public List<Food> getFoodPatient(int id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql1 = "SELECT * FROM p_food WHERE id_pat =" + id;
		ResultSet rs1 = stmt.executeQuery(sql1);
		LinkedList<Food> ls = new LinkedList();
		while (rs1.next()) {
			int idf = rs1.getInt("id_food");
			String dt = rs1.getString("date");
			Food f = getFoodbyId(idf);
			f.setDate(dt);
			ls.add(f);
		}
		rs1.close();
		stmt.close();
		return ls;
	}
	
	public List<Food>getFoodRejected(int id) throws SQLException{
		Statement stmt=c.createStatement();
		String sql="SELECT * FROM chro_food WHERE id_chro="+id;
		ResultSet rs=stmt.executeQuery(sql);
		LinkedList <Food> show=new LinkedList<>();
		while(rs.next()){
			int idf=rs.getInt("id_food");
			Food f=getFoodbyId(idf);
			show.add(f);
		}
		stmt.close();
		rs.close();
		return show;
	}

	public List<Visitor> getVisPatient(int id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql1 = "SELECT * FROM visitor WHERE id_pat =" + id;
		ResultSet rs1 = stmt.executeQuery(sql1);
		LinkedList<Visitor> ls = new LinkedList();
		while (rs1.next()) {
			int idm = rs1.getInt("id");
			String nm = rs1.getString("name");
			String sur = rs1.getString("surname");
			Visitor v = new Visitor(nm, sur);
			v.setId(idm);
			v.setSche(getScheVis(idm));
			ls.add(v);
		}
		rs1.close();
		stmt.close();
		return ls;
	}

	public List<Schedule> getScheVis(int id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql1 = "SELECT * FROM v_sche WHERE id_vis =" + id;
		ResultSet rs1 = stmt.executeQuery(sql1);
		LinkedList<Schedule> ls = new LinkedList();
		while (rs1.next()) {
			int ids = rs1.getInt("id_sche");
			Schedule m = getSchebyId(ids);
			ls.add(m);
		}
		rs1.close();
		stmt.close();
		return ls;
	}
	
	public List<Schedule> getScheDoc(int id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql1 = "SELECT * FROM d_sche WHERE id_doc =" + id;
		ResultSet rs1 = stmt.executeQuery(sql1);
		LinkedList<Schedule> ls = new LinkedList();
		while (rs1.next()) {
			int ids = rs1.getInt("id_sche");
			Schedule m = getSchebyId(ids);
			ls.add(m);
		}
		rs1.close();
		stmt.close();
		return ls;
	}

	public List<Medication> getMedIllness(int id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql1 = "SELECT * FROM ill_med WHERE id_ill =" + id;
		ResultSet rs1 = stmt.executeQuery(sql1);
		LinkedList<Medication> ls = new LinkedList();
		while (rs1.next()) {
			int idm = rs1.getInt("id_med");
			Medication m = getMedbyId(idm);
			ls.add(m);
		}
		rs1.close();
		stmt.close();
		return ls;
	}

	public List<Medication> getMedChronic(int id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql1 = "SELECT * FROM chro_medication WHERE id_chro =" + id;
		ResultSet rs1 = stmt.executeQuery(sql1);
		LinkedList<Medication> ls = new LinkedList();
		while (rs1.next()) {
			int idm = rs1.getInt("id_med");
			Medication m = getMedbyId(idm);
			ls.add(m);
		}
		rs1.close();
		stmt.close();
		return ls;
	}
	public List<Patient> getDoctorPatients(int id) throws SQLException{
		Statement stmt = c.createStatement();
		String sql1 = "SELECT * FROM patient WHERE id_doc =" + id;
		ResultSet rs1 = stmt.executeQuery(sql1);
		Patient p = null;
		List <Patient> show=new LinkedList<>();
		while (rs1.next()) {
			int idm = rs1.getInt("id");
			String nm = rs1.getString("name");
			String sur = rs1.getString("surname");
			int room = rs1.getInt("room_n");
			p = new Patient(nm, sur, room);
			p.setId(idm);
			show.add(p);
		}
		rs1.close();
		stmt.close();
		return show;
	}

	public Doctor getPatientDoctor(int id) throws SQLException {
		Statement stmt = c.createStatement();
		String sql1 = "SELECT * FROM doctor WHERE id =" + id;
		ResultSet rs1 = stmt.executeQuery(sql1);
		Doctor d = null;
		while (rs1.next()) {
			int idm = rs1.getInt("id");
			String nm = rs1.getString("name");
			String sur = rs1.getString("surname");
			String field = rs1.getString("field");
			d = new Doctor(nm, sur, field);
			d.setId(idm);
		}
		rs1.close();
		stmt.close();
		return d;
	}

	public List<Patient> selectP() throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM patient";
		ResultSet rs = stmt.executeQuery(sql);
		List<Patient> show = new LinkedList();
		while (rs.next()) {
			int id = rs.getInt("id");
			int n = rs.getInt("room_n");
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			Patient patient = new Patient(name, surname, n);
			patient.setId(id);
			try {
				patient.setFood(getFoodPatient(id));
				patient.setIllness(getIllbyId(rs.getInt("id_ill")));
				patient.setChronic(getChrobyId(rs.getInt("id_chro")));
				patient.setVisitor(getVisPatient(id));
				patient.setIn_charge(getPatientDoctor(rs.getInt("id_doc")));
			} catch (NullPointerException np) {
			}
			show.add(patient);
		}
		rs.close();
		stmt.close();
		return show;
	}

	public List<Patient> selectPid() throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM patient";
		ResultSet rs = stmt.executeQuery(sql);
		List<Patient> show = new LinkedList();
		while (rs.next()) {
			int id = rs.getInt("id");
			int n = rs.getInt("room_n");
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			Patient patient = new Patient(name, surname, n);
			patient.setId(id);
			show.add(patient);
		}
		rs.close();
		stmt.close();
		return show;
	}

	public List<Food> selectF() throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM food";
		ResultSet rs = stmt.executeQuery(sql);
		List<Food> show = new LinkedList();
		while (rs.next()) {
			int id = rs.getInt("id");
			float n = rs.getFloat("calories");
			String name = rs.getString("name");
			Food f = new Food(name, n);
			f.setId(id);
			try {
				f.setSalt(getSaltbyid(rs.getInt("id_salt")));
			} catch (NullPointerException np) {
			}
			show.add(f);
		}
		rs.close();
		stmt.close();
		return show;
	}

	public List<Medication> selectM() throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM medication";
		ResultSet rs = stmt.executeQuery(sql);
		List<Medication> show = new LinkedList();
		while (rs.next()) {
			int id = rs.getInt("id");
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

	public List<Doctor> selectD() throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM doctor";
		ResultSet rs = stmt.executeQuery(sql);
		List<Doctor> show = new LinkedList();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String sur = rs.getString("surname");
			String field = rs.getString("field");
			Doctor d = new Doctor(name, sur, field);
			d.setId(id);
			try{
				d.setSchedule(getScheDoc(id));
				d.setIn_charge(getDoctorPatients(id));
			}catch(NullPointerException np){
			}
			show.add(d);
		}
		rs.close();
		stmt.close();
		return show;
	}

	public List<Doctor> selectDbyfield(String f) throws SQLException {
		String sql = "SELECT * FROM doctor WHERE field LIKE ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, f);
		ResultSet rs = prep.executeQuery();
		List<Doctor> show = new LinkedList();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String sur = rs.getString("surname");
			String field = rs.getString("field");
			Doctor d = new Doctor(name, sur, field);
			d.setId(id);
			show.add(d);
		}
		rs.close();
		prep.close();
		return show;
	}

	public List<Visitor> selectV() throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM visitor";
		ResultSet rs = stmt.executeQuery(sql);
		List<Visitor> show = new LinkedList();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String sur = rs.getString("surname");
			Visitor v = new Visitor(name, sur);
			v.setId(id);
			try {
				v.setSche(getScheVis(id));
			} catch (NullPointerException np) {
			}
			show.add(v);
		}
		rs.close();
		stmt.close();
		return show;
	}

	public List<Schedule> selectSh() throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM schedule";
		ResultSet rs = stmt.executeQuery(sql);
		List<Schedule> show = new LinkedList();
		while (rs.next()) {
			int id = rs.getInt("id");
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

	public List<Illness> selectI() throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM illness";
		ResultSet rs = stmt.executeQuery(sql);
		List<Illness> show = new LinkedList();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			Illness i = new Illness(name);
			i.setId(id);
			try {
				i.setTreats(getMedIllness(id));
			} catch (NullPointerException np) {
			}
			show.add(i);
		}
		rs.close();
		stmt.close();
		return show;
	}

	public List<Chronic> selectC() throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM chronic";
		ResultSet rs = stmt.executeQuery(sql);
		List<Chronic> show = new LinkedList();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			Chronic i = new Chronic(name);
			i.setId(id);
			try{
				i.setRejects(getFoodRejected(id));
				i.setRej_med(getMedChronic(id));
			}catch(NullPointerException np){
			}
			show.add(i);
		}
		rs.close();
		stmt.close();
		return show;
	}

	public List<Salt> selectS() throws SQLException {
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM salt";
		ResultSet rs = stmt.executeQuery(sql);
		List<Salt> show = new LinkedList();
		while (rs.next()) {
			int id = rs.getInt("id");
			float mn = rs.getFloat("min");
			float mx = rs.getFloat("max");
			Salt f = new Salt(mn, mx);
			f.setId(id);
			show.add(f);
		}
		rs.close();
		stmt.close();
		return show;
	}

	public void dropTableP() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "DROP TABLE patient";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}
	
	public void dropTableD() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "DROP TABLE doctor";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public void dropTableF() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "DROP TABLE food";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public void dropTableS() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "DROP TABLE salt";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public void dropTableM() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "DROP TABLE medication";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public void dropTableV() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "DROP TABLE visitor";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public void dropTableI() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "DROP TABLE illness";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public void dropTableC() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "DROP TABLE chronic";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public void dropTableSh() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "DROP TABLE schedule";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public void dropTableV_Schedule() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "DROP TABLE v_sche";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}
	
	public void dropTableP_Doctor() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "DROP TABLE p_doc";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}
	
	public void dropTableP_Food() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "DROP TABLE p_food";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}

	public void dropTableC_Food() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "DROP TABLE chro_food";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}
	
	public void dropTableI_Med() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "DROP TABLE ill_med";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}
	
	public void dropTableChro_Med() throws SQLException {
		Statement stmt1 = c.createStatement();
		String sql1 = "DROP TABLE chro_med";
		stmt1.executeUpdate(sql1);
		stmt1.close();
	}
	
	public Patient searchPatient(int room) throws SQLException {
		Patient patient = null;
		String sql = "SELECT * FROM patient WHERE room_n = ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, room);
		ResultSet rs = prep.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String surname = rs.getString("surname");
			int room_n = rs.getInt("room_n");
			patient = new Patient(name, surname, room_n);
			patient.setId(id);
			try {
				patient.setFood(getFoodPatient(id));
				patient.setIllness(getIllbyId(rs.getInt("id_ill")));
				patient.setChronic(getChrobyId(rs.getInt("id_chro")));
				patient.setVisitor(getVisPatient(id));
			} catch (NullPointerException np) {
			}
		}
		rs.close();
		prep.close();
		return patient;
	}

	public Food searchFood(int id) throws SQLException {
		Food food = null;
		String sql = "SELECT * FROM food WHERE id = ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, id);
		ResultSet rs = prep.executeQuery();
		while (rs.next()) {
			int _id = rs.getInt("id");
			Float calories = rs.getFloat("calories");
			String name = rs.getString("name");
			food = new Food(name, calories);
			food.setId(id);
		}
		rs.close();
		prep.close();
		return food;
	}

	public Medication searchMed(String name) throws SQLException {
		Medication med = null;
		String sql = "SELECT * FROM medication WHERE name LIKE ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, name);
		ResultSet rs = prep.executeQuery();
		while (rs.next()) {
			int _id = rs.getInt("id");
			String agent = rs.getString("agent");
			String name1 = rs.getString("name");
			med = new Medication(name1, agent);
			med.setId(_id);
		}
		rs.close();
		prep.close();
		return med;
	}

	public Schedule searchSche(String start, String end) throws SQLException {
		Schedule s = null;
		String sql = "SELECT * FROM schedule WHERE start LIKE ? AND end LIKE ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, start);
		prep.setString(2, end);
		ResultSet rs = prep.executeQuery();
		while (rs.next()) {
			int _id = rs.getInt("id");
			String _start = rs.getString("start");
			String _end = rs.getString("end");
			String day = rs.getString("day");
			s = new Schedule(_start, _end, day);
			s.setId(_id);
		}
		rs.close();
		prep.close();
		return s;
	}

	public Visitor searchVis(String name, String surname) throws SQLException {
		Visitor vis = null;
		String sql = "SELECT * FROM visitor WHERE name LIKE ? AND surname LIKE ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, name);
		prep.setString(2, surname);
		ResultSet rs = prep.executeQuery();
		while (rs.next()) {
			int _id = rs.getInt("id");
			String _name = rs.getString("name");
			String sur = rs.getString("surname");
			vis = new Visitor(_name, sur);
			vis.setId(_id);
		}
		rs.close();
		prep.close();
		return vis;
	}

	public Illness searchIll(String name) throws SQLException {
		Illness ill = null;
		String sql = "SELECT * FROM illness WHERE name LIKE ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, name);
		ResultSet rs = prep.executeQuery();
		while (rs.next()) {
			int _id = rs.getInt("id");
			String n = rs.getString("name");
			ill = new Illness(n);
			ill.setId(_id);
		}
		rs.close();
		prep.close();
		return ill;
	}

	public Chronic searchChro(String name) throws SQLException {
		Chronic ch = null;
		String sql = "SELECT * FROM chronic WHERE name LIKE ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, name);
		ResultSet rs = prep.executeQuery();
		while (rs.next()) {
			int _id = rs.getInt("id");
			String _name = rs.getString("name");
			ch = new Chronic(_name);
			ch.setId(_id);
		}
		rs.close();
		prep.close();
		return ch;
	}
	public Doctor searchDoc(String n,String surname) throws SQLException{
		Doctor d = null;
		String sql = "SELECT * FROM doctor WHERE name LIKE ? AND surname LIKE ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, n);
		prep.setString(2,surname);
		ResultSet rs = prep.executeQuery();
		while (rs.next()) {
			int _id = rs.getInt("id");
			String _name = rs.getString("name");
			String _s = rs.getString("surname");
			String _f = rs.getString("field");
			d=new Doctor(_name, _s, _f);
			d.setId(_id);
		}
		rs.close();
		prep.close();
		return d;
	}

	public void updatePatient(int id, int room) throws SQLException {
		String sql = "UPDATE patient SET room_n=? WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, room);
		prep.setInt(2, id);
		prep.executeUpdate();
	}

	public void updateFood(int id, int calories) throws SQLException {
		String sql = "UPDATE food SET calories=? WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, calories);
		prep.setInt(2, id);
		prep.executeUpdate();
	}

	public void updateSalt(int id, float min, float max) throws SQLException {
		String sql = "UPDATE salt SET min=?,max=? WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setFloat(1, min);
		prep.setFloat(2, max);
		prep.setInt(3, id);
		prep.executeUpdate();
	}

	public void updateSchedule(String start, String end, String day, int id) throws SQLException {
		String sql = "UPDATE schedule SET start=?, end=?, day=? WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, start);
		prep.setString(2, end);
		prep.setString(3, day);
		prep.setInt(4, id);
		prep.executeUpdate();
	}

	public int obtainIllnessid(String n) throws SQLException {
		String sql = "SELECT * FROM illness WHERE name = ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, n);
		ResultSet rs = prep.executeQuery();
		int id = 0;
		while (rs.next()) {
			id = rs.getInt("id");
		}
		rs.close();
		prep.close();
		return id;
	}

	public int obtainChronicid(String n) throws SQLException {
		String sql = "SELECT * FROM chronic WHERE name = ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, n);
		ResultSet rs = prep.executeQuery();
		int id = 0;
		while (rs.next()) {
			id = rs.getInt("id");
		}
		rs.close();
		prep.close();
		return id;
	}

	public int obtainVisitorid(Visitor visitor) throws SQLException {
		String sql = "SELECT * FROM visitor WHERE name LIKE ? AND surname LIKE ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, visitor.getName());
		prep.setString(2, visitor.getSurname());
		ResultSet rs = prep.executeQuery();
		int id = 0;
		while (rs.next()) {
			id = rs.getInt("id");
		}
		rs.close();
		prep.close();
		return id;
	}

	public int obtainDoctorid(String name, String surname) throws SQLException {
		String sql = "SELECT * FROM doctor WHERE name LIKE ? AND surname LIKE ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, name);
		prep.setString(2, surname);
		ResultSet rs = prep.executeQuery();
		int id = 0;
		while (rs.next()) {
			id = rs.getInt("id");
		}
		rs.close();
		prep.close();
		return id;
	}

	public List<Integer> obtainFoodid(int calories) throws SQLException {
		String sql = "SELECT id FROM food WHERE calories > ?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, calories);
		List<Integer> id = new LinkedList<>();
		ResultSet rs = prep.executeQuery();
		while (rs.next()) {
			id.add(rs.getInt("id"));
		}
		rs.close();
		prep.close();
		return id;
	}
}
