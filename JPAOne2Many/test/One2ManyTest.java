import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import com.joe.bean.Order;
import com.joe.bean.OrderItem;



public class One2ManyTest {

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
		Order order = new Order();
		order.setTotalPrice(34f);
		//UUID.randomUUID().toString()
		order.setOrderId("000001");
		
		OrderItem orderItem = new OrderItem();
		orderItem.setProductName("football");
		orderItem.setSellPrice(15f);
		//add it
		order.addOrderItem(orderItem);
		
		orderItem = new OrderItem();
		orderItem.setProductName("yoga");
		orderItem.setSellPrice(19f);
		//add it
		order.addOrderItem(orderItem);
		
		em.persist(order);
		
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
}
