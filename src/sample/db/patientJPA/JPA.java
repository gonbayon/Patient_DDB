package sample.db.patientJPA;

import java.util.*;
import sample.db.pojos.*;
import javax.persistence.*;

public class JPA {
	
	String name;
	
	public EntityManager em;	

	public JPA(){
		
		
	}
	public String getName(){
		
		return name;
	}
	
	//private static final String PERSISTENCE_PROVIDER = "patient-provider";
	
	public void startMethod(){
		em = Persistence.createEntityManagerFactory("hospital-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		
		}
	
		public void insertDoc(Doctor doctor){
			
			System.out.println(doctor);
			Query q1 = em.createNativeQuery("INSERT INTO doctor (name,surname,field) " + "VALUES (?,?,?)",Doctor.class);
			q1.setParameter(1, "%" + doctor.getName() + "%");
			q1.setParameter(2, "%" + doctor.getSurname() + "%");
			q1.setParameter(3, "%" + doctor.getField() + "%");
			Doctor doc = (Doctor) q1.getSingleResult();
			System.out.println(doc);
			em.getTransaction().begin();
			em.persist(doc);
			em.getTransaction().commit();
		}
		public void insertPatient(Patient patient){
	
			em.getTransaction().begin();
			em.persist(patient);
			em.getTransaction().commit();
		}
		
		public void insertFood(Food food){
			
			em.getTransaction().begin();
			em.persist(food);
			em.getTransaction().commit();
		}
		
		public void insertSche(Schedule sche){
			
			em.getTransaction().begin();
			em.persist(sche);
			em.getTransaction().commit();
		}
		
		public void insertVis(Visitor vis){
			
			em.getTransaction().begin();
			em.persist(vis);
			em.getTransaction().commit();
		}
		
		public void insertIll(Illness ill){
			
			em.getTransaction().begin();
			em.persist(ill);
			em.getTransaction().commit();
		}

		public void insertChronic(Chronic ch){
		
			em.getTransaction().begin();
			em.persist(ch);
			em.getTransaction().commit();
		}
		
		
		public void deleteDoctor(int id){
			
			Query q1 =em.createNativeQuery("SELECT * FROM doctor WHERE id LIKE ?", Doctor.class);
			q1.setParameter(1, "%" + id + "%");
			Doctor doctor = (Doctor) q1.getSingleResult();
			em.getTransaction().begin();
			em.remove(doctor);
			em.getTransaction().commit();
			
		}
		
		public void deleteDoctor(String name, String surname)	{
			
			Query q1 =em.createNativeQuery("SELECT * FROM doctor WHERE name LIKE ? AND surname LIKE ?", Doctor.class);
			q1.setParameter(1, "%" + name + "%");
			q1.setParameter(2, "%" + surname + "%");
			Doctor doc = (Doctor) q1.getSingleResult();
			em.getTransaction().begin();
			em.remove(doc);
			em.getTransaction().commit();
			
		}
		
		public void deleteFood(int id){
					
					Query q1 =em.createNativeQuery("SELECT * FROM food WHERE id = ?", Food.class);
					q1.setParameter(1, "%" + id + "%");
					Food food = (Food) q1.getSingleResult();
					em.getTransaction().begin();
					em.remove(food);
					em.getTransaction().commit();
					
				}
		
		public void deleteSalt(int id){
			
			Query q1 =em.createNativeQuery("SELECT * FROM salt WHERE id = ?", Salt.class);
			q1.setParameter(1, "%" + id + "%");
			Salt salt = (Salt) q1.getSingleResult();
			em.getTransaction().begin();
			em.remove(salt);
			em.getTransaction().commit();
			
		}
		
		public void deleteMedicat(int id){
			
			Query q1 =em.createNativeQuery("SELECT * FROM Medication WHERE id LIKE ?", Medication.class);
			q1.setParameter(1, "%" + id + "%");
			Medication medication = (Medication) q1.getSingleResult();
			em.getTransaction().begin();
			em.remove(medication);
			em.getTransaction().commit();
			
		}
		
		public void deleteSchedule(int id){
			
			Query q1 =em.createNativeQuery("SELECT * FROM Schedule WHERE id LIKE ?", Schedule.class);
			q1.setParameter(1, "%" + id + "%");
			Schedule schedule = (Schedule) q1.getSingleResult();
			em.getTransaction().begin();
			em.remove(schedule);
			em.getTransaction().commit();
			
		}
		
		public void deleteVisitor(int id){
			
			Query q1 =em.createNativeQuery("SELECT * FROM Visitor WHERE id LIKE ?", Visitor.class);
			q1.setParameter(1, "%" + id + "%");
			Visitor visitor = (Visitor) q1.getSingleResult();
			em.getTransaction().begin();
			em.remove(visitor);
			em.getTransaction().commit();
			
		}
		
		public void deleteIllness(int id){
			
			Query q1 =em.createNativeQuery("SELECT * FROM Illness WHERE id LIKE ?", Illness.class);
			q1.setParameter(1, "%" + id + "%");
			Illness illness = (Illness) q1.getSingleResult();
			em.getTransaction().begin();
			em.remove(illness);
			em.getTransaction().commit();
			
		}
		
		public void deleteChronic(int id){
					
					Query q1 =em.createNativeQuery("SELECT * FROM Chronic WHERE id LIKE ?", Chronic.class);
					q1.setParameter(1, "%" + id + "%");
					Chronic chronic = (Chronic) q1.getSingleResult();
					em.getTransaction().begin();
					em.remove(chronic);
					em.getTransaction().commit();
					
				}
		
		public Doctor getDoctorbyId(int id){
					
					Query q1 =em.createNativeQuery("SELECT * FROM doctor WHERE id= ?", Doctor.class);
					q1.setParameter(1, "%" + id + "%");
					Doctor doctor = (Doctor) q1.getSingleResult();
					return doctor;
					
				}
		
		public Patient getPatientbyId(int id){
			
			Query q1 =em.createNativeQuery("SELECT * FROM Patient WHERE id = ?", Patient.class);
			q1.setParameter(1, "%" + id + "%");
			Patient patient = (Patient) q1.getSingleResult();
			return patient;
			
		}
		
		
		public Food getFoodbyId(int id){
			
			Query q1 =em.createNativeQuery("SELECT * FROM Food WHERE id LIKE ?",Food.class);
			q1.setParameter(1, "%" + id + "%");
			Food food = (Food) q1.getSingleResult();
			return food;
			
		}
		
		public Medication getMedbyId(int id){
			
			Query q1 =em.createNativeQuery("SELECT * FROM medication WHERE id LIKE ?", Medication.class);
			q1.setParameter(1, "%" + id + "%");
			Medication medication = (Medication) q1.getSingleResult();
			return medication;
			
		}
		
		
		
		public List<Food> getFoodPatient(int id){
			
			Query q1 = em.createNativeQuery("SELECT * FROM p_food WHERE id_pat LIKE ?", Food.class);
			q1.setParameter(1, "%" + id + "%");
			List<Food> patfood = (List<Food>) q1.getResultList();
			return patfood;
			
		}
		
		public List<Medication> getMedPatient(int id){

					
					Query q1 = em.createNativeQuery("SELECT * FROM p_medic WHERE id_pat LIKE ?", Medication.class);
					q1.setParameter(1, "%" + id + "%");
					List<Medication> patmedic = (List<Medication>) q1.getResultList();
					return patmedic;
					
				}
		
		
		
		public List<Food> selectF() {
			Query q1 = em.createNativeQuery("SELECT * FROM Food", Food.class);
			List<Food> food = (List<Food>) q1.getResultList();
			return food;

		}
		
		public List<Patient> selectP() {
			Query q1 = em.createNativeQuery("SELECT * FROM patient", Patient.class);
			List<Patient> p = (List<Patient>) q1.getResultList();
			return p;
		}
		
		public List<Medication> selectM() {
			Query q1 = em.createNativeQuery("SELECT * FROM Medication", Medication.class);
			List<Medication> m = (List<Medication>) q1.getResultList();
			return m;

		}
		
		public List<Visitor> selectV() {
			Query q1 = em.createNativeQuery("SELECT * FROM Visitor",Visitor.class);
			List<Visitor> v = (List<Visitor>) q1.getResultList();
			return v;

		}
		
		public List<Schedule> selectSh() {
			Query q1 = em.createNativeQuery("SELECT * FROM schedule", Schedule.class);
			List<Schedule> s = (List<Schedule>) q1.getResultList();
			return s;

		}
		
		public List<Illness> selectI() {
			Query q1 = em.createNativeQuery("SELECT * FROM Illness", Illness.class);
			List<Illness> i = (List<Illness>) q1.getResultList();
			return i;

		}
		
		public List<Chronic> selectC() {
			Query q1 = em.createNativeQuery("SELECT * Chronic Food",Chronic.class);
			List<Chronic> c = (List<Chronic>) q1.getResultList();
			return c;

		}
		
		public List<Doctor> selectD() {

			Query q1 = em.createNativeQuery("SELECT * FROM doctor", Doctor.class);
			List<Doctor> d = (List<Doctor>) q1.getResultList();
			return d;

		}

	
		public List<Doctor> selectDbyfield(String f){
					
					Query q1 = em.createNativeQuery("SELECT * FROM doctor WHERE field LIKE ?", Doctor.class);
					q1.setParameter(1, "%" + f + "%");
					List<Doctor> docfield = (List<Doctor>) q1.getResultList();
					return docfield;
					
				}
		
		public Patient searchPatient(int room){
					
					Query q1 =em.createNativeQuery("SELECT * FROM patient WHERE room LIKE ?",Patient.class);
					q1.setParameter(1, "%" + room + "%");
					Patient p = (Patient) q1.getSingleResult();
					return p;
					
				}
		public Food searchFood(int id){
					
					Query q1 =em.createNativeQuery("SELECT * FROM Food WHERE id LIKE ?",Food.class);
					q1.setParameter(1, "%" + id + "%");
					Food food = (Food) q1.getSingleResult();
					return food;
					
				}
		public Medication searchMed(String name){
			
			Query q1 =em.createNativeQuery("SELECT * FROM medication WHERE name LIKE ?",Medication.class);
			q1.setParameter(1, "%" + name + "%");
			Medication m = (Medication) q1.getSingleResult();
			return m;
			
		}
	
		
		public Doctor searchDoc(String n,String surname){
					
					Query q1 =em.createNativeQuery("SELECT * FROM doctor WHERE name LIKE ? AND surname LIKE ?",Doctor.class);
					q1.setParameter(1, "%" + name + "%");
					q1.setParameter(2, "%" + surname + "%");
					Doctor d = (Doctor) q1.getSingleResult();
					return d;
					
				}
		
		/*public Schedule searchSche(String start, String end){
			
			Query q1 =em.createNativeQuery("SELECT * FROM Food WHERE id LIKE ?",Food.class);
			q1.setParameter(1, "%" + id + "%");
			Food food = (Food) q1.getSingleResult();
			return food;
			
		}*/
		
		//public Visitor searchVis(String name, String surname)
		//public Doctor searchDoc(String n,String surname)
		
		public Illness searchIll(String name){
			
			Query q1 =em.createNativeQuery("SELECT * FROM illness WHERE name LIKE ?",Illness.class);
			q1.setParameter(1, "%" + name + "%");
			Illness i = (Illness) q1.getSingleResult();
			return i;
			
		}
		
		public Chronic searchChro(String name){
					
					Query q1 =em.createNativeQuery("SELECT * FROM chronic WHERE name LIKE ?",Chronic.class);
					q1.setParameter(1, "%" + name + "%");
					Chronic c = (Chronic) q1.getSingleResult();
					return c;
					
				}
		
		
		
		public void updatePatient(int id,int room){
			Query q1 =em.createNativeQuery("SELECT * FROM Patient WHERE id LIKE ?", Patient.class);
			q1.setParameter(1, "%" + id + "%");
			Patient pat = (Patient) q1.getSingleResult();
			
			em.getTransaction().begin();
			pat.setRoom_nº(room);
			em.getTransaction().commit();
		}
		
		public void updateSchedule(String start, String end, String day, int id){
			Query q1 =em.createNativeQuery("SELECT * FROM Schedule WHERE id LIKE ?", Schedule.class);
			q1.setParameter(1, "%" + id + "%");
			Schedule sch = (Schedule) q1.getSingleResult();
			
			em.getTransaction().begin();
			sch.setStart(start);
			sch.setEnd(end);
			sch.setDay(day);
			em.getTransaction().commit();
		}
		
		public void updateFood(int id,int calories){
			Query q1 =em.createNativeQuery("SELECT * FROM Food WHERE id LIKE ?", Food.class);
			q1.setParameter(1, "%" + id + "%");
			Food food = (Food) q1.getSingleResult();
			
			em.getTransaction().begin();
			food.setCalories(calories);
			
			em.getTransaction().commit();
		}
		

	}
