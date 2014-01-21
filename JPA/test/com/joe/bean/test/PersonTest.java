package com.joe.bean.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.BeforeClass;
import org.junit.Test;

import com.joe.bean.Person;

public class PersonTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void save() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("joePersistenceUnit");
		EntityManager em = factory.createEntityManager();
		
		//transaction start
		em.getTransaction().begin();
		
		//JPA defines persist() function. it equals save() in hibernate, but JPA recommend to use persost() function.
		em.persist(new Person("Big Joe 2"));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}

	@Test
	public void getPerson() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("joePersistenceUnit");
		EntityManager em = factory.createEntityManager();
		
		//same as get() in hibernate 
		Person person = em.find(Person.class, 1);
		System.out.println(person.getName());
		em.close();
		factory.close();
	}
	
	@Test
	public void getPerson2() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("joePersistenceUnit");
		EntityManager em = factory.createEntityManager();
		
		//same as load() in hibernate. it returns the proxy object.
		//only when some attribute is read, the data can be retrieved from db
		Person person = em.getReference(Person.class, 1);
		System.out.println(person.getName());
		em.close();
		factory.close();
	}
	
	@Test
	public void updatePerson() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("joePersistenceUnit");
		EntityManager em = factory.createEntityManager();
		
		//transaction start
		em.getTransaction().begin();
		
		Person person = em.find(Person.class, 1);
		person.setName("Joe Xiao");
		
		//when this line is executed, the name will be updated in database.
		em.getTransaction().commit();
		
		em.close();
		factory.close();
	}
	
	@Test
	public void updatePerson2() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("joePersistenceUnit");
		EntityManager em = factory.createEntityManager();
		
		//transaction start
		em.getTransaction().begin();
		
		Person person = em.find(Person.class, 1);
		//set all instances in entity manager to detached
		em.clear();
		
		
		person.setName("Joe Xiao 2");
		
		//when this line is executed, the name will NOT be updated in database.
		//because it is a detahed object
		em.getTransaction().commit();
		
		em.close();
		factory.close();
	}
	
	@Test
	public void updatePerson3() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("joePersistenceUnit");
		EntityManager em = factory.createEntityManager();
		
		//transaction start
		em.getTransaction().begin();
		
		Person person = em.find(Person.class, 1);
		//set all instances in entity manager to detached
		em.clear();
		
		person.setName("Joe Xiao 2");

		//merge() can synchronize instances from detached state to DB
		em.merge(person);
		em.getTransaction().commit();
		
		em.close();
		factory.close();
	}
	
	@Test
	public void delete() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("joePersistenceUnit");
		EntityManager em = factory.createEntityManager();
		
		//transaction start
		em.getTransaction().begin();
		
		Person person = em.find(Person.class, 1);
		em.remove(person);
		em.getTransaction().commit();
		
		em.close();
		factory.close();
	}
	
	@Test
	public void querySingle() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("joePersistenceUnit");
		EntityManager em = factory.createEntityManager();
		
		Query q = em.createQuery("select p from Person p where p.id=?");
		q.setParameter(1, 1);
		Person person = (Person)q.getSingleResult();
		
		System.out.println(person.getName());
		em.close();
		factory.close();
	}
	
	@Test
	public void queryMultiple() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("joePersistenceUnit");
		EntityManager em = factory.createEntityManager();
		
		Query q = em.createQuery("select p from Person p");
		List<Person> persons = (List<Person>)q.getResultList();
		
		for (Person person:persons) {
			System.out.println(person.getName());
		}
		em.close();
		factory.close();
	}
	
	@Test
	public void queryUpdate() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("joePersistenceUnit");
		EntityManager em = factory.createEntityManager();
		
		//transaction start
		em.getTransaction().begin();
		
		Query q = em.createQuery("update Person p set p.name=? where p.id=?");
		q.setParameter(1, "ok, well");
		q.setParameter(2, 1);
		int updatedResultNumber = q.executeUpdate();
		System.out.println(updatedResultNumber);
		em.getTransaction().commit();
		
		em.close();
		factory.close();
	}
}
