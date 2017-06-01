package sample.db.patientJPA;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class jpa {

	EntityManager em=null;
	void startMethod(){
		EntityManager em = Persistence.createEntityManagerFactory("hospital-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		}
	
}
