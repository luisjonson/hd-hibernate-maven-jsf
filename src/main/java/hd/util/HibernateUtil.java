package hd.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	public static EntityManagerFactory factory = null;

	static {
		init();
	}

	private static void init() {
		try {
			// verifica se há uma coneção aberta no banco
			if (factory == null) {
				factory = Persistence.createEntityManagerFactory("hd-hibernate-maven-jsf");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static EntityManager getEntityManager() {
		return factory.createEntityManager(); /* Prove a parte de persistencia */
	}

	public static Object getPrimaryKey(Object entity) { // Retorna a primay key
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
	}

}
