package sample.db.patientJPA;

import java.util.*;
import sample.db.pojos.*;
import javax.persistence.*;

public class JPA {
	
	public EntityManager em;	
	
	static void startMethod(){
		EntityManager em = Persistence.createEntityManagerFactory("hospital-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		
		}
	
		public void insertDoctor(Doctor doctor){
			
			
			em.getTransaction().begin();
			em.persist(doctor);
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
		
		//Esto no se si esta bien, le paso una id, y con el query busco el objeto y lo borra.
		public void deleteDoctor(int id){
			
			Query q1 =em.createNativeQuery("SELECT * FROM Doctor WHERE id LIKE ?", Doctor.class);
			q1.setParameter(1, "%" + id + "%");
			Doctor doctor = (Doctor) q1.getSingleResult();
			em.getTransaction().begin();
			em.remove(doctor);
			em.getTransaction().commit();
			
		}
				
		public void deleteFood(int id){
					
					Query q1 =em.createNativeQuery("SELECT * FROM Food WHERE id LIKE ?", Food.class);
					q1.setParameter(1, "%" + id + "%");
					Food food = (Food) q1.getSingleResult();
					em.getTransaction().begin();
					em.remove(food);
					em.getTransaction().commit();
					
				}
		
		public void deleteSalt(int id){
			
			Query q1 =em.createNativeQuery("SELECT * FROM Salt WHERE id LIKE ?", Salt.class);
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
					
					Query q1 =em.createNativeQuery("SELECT * FROM Doctor WHERE id LIKE ?", Doctor.class);
					q1.setParameter(1, "%" + id + "%");
					Doctor doctor = (Doctor) q1.getSingleResult();
					return doctor;
					
				}
		
		public Patient getPatientbyId(int id){
			
			Query q1 =em.createNativeQuery("SELECT * FROM Patient WHERE id LIKE ?", Patient.class);
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
			
			Query q1 =em.createNativeQuery("SELECT * FROM Medication WHERE id LIKE ?", Medication.class);
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
			Query q1 = em.createNativeQuery("SELECT * FROM Schedule", Schedule.class);
			List<Schedule> s = (List<Schedule>) q1.getResultList();
			return s;

		}
		
		public List<Illness> selectI() {
			Query q1 = em.createNativeQuery("SELECT * FROM Illness", Illness.class);
			List<Illness> i = (List<Illness>) q1.getResultList();
			return i;

		}
		
		List<Chronic> selectC() {
			Query q1 = em.createNativeQuery("SELECT * Chronic Food",Chronic.class);
			List<Chronic> c = (List<Chronic>) q1.getResultList();
			return c;

		}
		
		public List<Doctor> selectD() {
			Query q1 = em.createNativeQuery("SELECT * FROM Doctor", Doctor.class);
			List<Doctor> d = (List<Doctor>) q1.getResultList();
			return d;

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
