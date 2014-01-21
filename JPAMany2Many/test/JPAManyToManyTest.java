import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;

import com.joe.bean.Student;
import com.joe.bean.Teacher;


public class JPAManyToManyTest {

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
		em.getTransaction().begin();
		em.persist(new Student("Stud_Xiao"));
		em.persist(new Teacher("teach_Joe"));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	@Test
	public void buildTeacherAndStudentRelationship() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("joePersistenceUnit");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Student student = em.find(Student.class, 1);
		//add teacher 1 to student 1
		student.addTeacher(em.getReference(Teacher.class, 1));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	@Test
	public void removeTeacherAndStudentRelationship() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("joePersistenceUnit");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Student student = em.find(Student.class, 1);
		//remove teacher 1 from student 1
		student.removeTeacher(em.getReference(Teacher.class, 1));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	//this method can NOT delete a teacher
	@Test
	public void deleteTeacher() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("joePersistenceUnit");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.remove(em.getReference(Teacher.class, 1));
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	//this can delete a teacher
	@Test
	public void deleteTeacher2() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("joePersistenceUnit");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Student student = em.find(Student.class, 1);
		Teacher teacher = em.getReference(Teacher.class, 1);
		student.removeTeacher(teacher);
		em.remove(teacher);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
	
	//this one can delete a student, and the relationship record in student_teacher table 
	//will be deleted at the same time.
	@Test
	public void deleteStudent() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("joePersistenceUnit");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Student student = em.getReference(Student.class, 1);
		em.remove(student);
		em.getTransaction().commit();
		em.close();
		factory.close();
	}
}
