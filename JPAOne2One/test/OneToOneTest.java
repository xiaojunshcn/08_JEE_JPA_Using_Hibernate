import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import com.joe.bean.IDCard;
import com.joe.bean.Person;


public class OneToOneTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void createTables() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("joePersistenceUnit");
		factory.close();
	}

	
	@Test
	public void save() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("joePersistenceUnit");
		EntityManager em = factory.createEntityManager();
		
		//transaction start
		em.getTransaction().begin();
		
		Person person = new Person("肖さん");
		IDCard idCard = new IDCard("123456789012345678");
		idCard.setPerson(person);
		person.setIdCard(idCard);
		
		em.persist(person);
		
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	
	@Test
	public void save2() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("joePersistenceUnit");
		EntityManager em = factory.createEntityManager();
		
		//transaction start
		em.getTransaction().begin();
		
		Person person = new Person("肖ですよ");
		person.setIdCard(new IDCard("123456789012345678"));
		
		em.persist(person);
		
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
}
