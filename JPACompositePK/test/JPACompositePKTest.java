import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import com.joe.bean.AirLine;

public class JPACompositePKTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void save() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("joePersistenceUnit");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(new AirLine("PEK","SHA","Air line from beijing to shanghai"));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

}
