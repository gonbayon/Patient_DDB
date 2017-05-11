package sample.db.patient;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Start {
	void startMethod(){
	EntityManager em = Persistence.createEntityManagerFactory("hospital-provider").createEntityManager();
	em.getTransaction().begin();
	em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
	em.getTransaction().commit();
	}
}
