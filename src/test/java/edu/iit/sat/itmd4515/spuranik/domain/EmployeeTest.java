/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spuranik.domain;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Pooja
 */
public class EmployeeTest {
    private static EntityManagerFactory emf;
    private static final Logger LOG = Logger.getLogger(EmployeeTest.class.getName());
    private EntityManager em;
    private EntityTransaction tx;
    private static Validator validator;
    
    @BeforeClass
    public static void beforeClassTestFixture(){
        emf = Persistence.createEntityManagerFactory("itmd4515PU_TEST");
       validator = Validation.buildDefaultValidatorFactory().getValidator();
                
    }
    
    @AfterClass
    public static void afterClassTestFixture(){
        emf.close();
    }
    
    @Before
    public void beforeEachTestMethod(){
        em = emf.createEntityManager();
        tx = em.getTransaction();              
        Employee seed = new Employee("shruti", "puranik", "spuranik@hawk.iit.edu", 98765542546l,"2951 S K D");
        tx.begin();
        em.persist(seed);
        tx.commit();
    }
    
    @After
    public void afterEachTestMethod(){
        
        Employee seed = 
                em.createNamedQuery("Employee.findByEmail", Employee.class)
                        .setParameter("email", "spuranik@hawk.iit.edu")
                        .getSingleResult();
        
        tx.begin();
        em.remove(seed);
        
        assertEquals(0l, em.createNamedQuery("Employee.findByEmail", Employee.class)
                       .setParameter("email", "spuranik@hawk.iit.edu")
                       .getResultList().size());
        tx.commit();
        
        em.close();
    }  
   @Test
    public void validateNullEmailTest() {
        Employee seed = new Employee("shruti", "puranik", null, 98765542546l,"2951 S K D");
        
        Set<ConstraintViolation<Employee>> violations = validator.validate(seed);

              
        assertFalse(violations.isEmpty());
      assertTrue(violations.size() == 1);
        assertEquals(violations.size(), 1);
    }

    
    @Test
    public void verifySeedData(){
        List<Employee> seeds = 
                em.createNamedQuery("Employee.findByEmail", Employee.class)
                        .setParameter("email", "spuranik@hawk.iit.edu")
                        .getResultList();
        
        assertEquals(seeds.size(), 1);
        assertSame(seeds.get(0).getEmail(), "spuranik@hawk.iit.edu");
    }
    
    @Test
    public void persistNewEmployeeTest(){
        Employee o1 = new Employee("soumya", "math", "soumya@hawk.iit.edu", 98765585546l,"2651 S K D");
        
        tx.begin();
        
        assertNull("Employee ID should be null before persist and commit", o1.getId());
        em.persist(o1);
        assertNull("Employee ID should be null after persist but before commit", o1.getId());
        tx.commit();
        System.out.println(o1.getId());
        Employee o2 = em.find(Employee.class, o1.getId());
        System.out.println("Employee Id "+o2.getId()+" Employee Email "+o2.getEmail());
        assertEquals(o2.getEmail(), "soumya@hawk.iit.edu");
        assertNotNull("Employee ID should not be null after persist and commit", o1.getId());
        assertTrue("Employee ID should be greater than 0 after commit", o1.getId()> 0);
       
       //Update Query to set new email 
        tx.begin();
       
       o2.setEmail("smath@hawk.iit.edu");
       tx.commit();
       System.out.println("Employee Id "+o2.getId()+" Employee Updated Email "+o2.getEmail());
    }

 @Test(expected = RollbackException.class)
    public void persistNewEmployeeShouldFailRainyDayTest(){
        
        Employee seed = new Employee("shruti", "puranik", "spuranik@hawk.iit.edu", 98765542546l,"2951 S K D");
        
        tx.begin();
        seed.setId(1l);
        em.persist(seed);       
        tx.commit();
       
    }

     @Test
  public void shouldCheckFirstnameMessage() {

    // Creates a book
    Employee seed = new Employee("s", "puranik", "spuranik@hawk.iit.edu", 98765542546l,"2951 S K D");

    // Validate the cd
    Set<ConstraintViolation<Employee>> violations = validator.validate(seed);
    displayContraintViolations(violations);
    assertEquals(1, violations.size());
    assertEquals("Firstname should be between 4 and 50", violations.iterator().next().getMessage());
  }
  
  private void displayContraintViolations(Set<ConstraintViolation<Employee>> constraintViolations) {
    for (ConstraintViolation constraintViolation : constraintViolations) {
      System.out.println("### " + constraintViolation.getRootBeanClass().getSimpleName() +
              "." + constraintViolation.getPropertyPath() + " - Invalid Value = " + constraintViolation.getInvalidValue() + " - Error Msg = " + constraintViolation.getMessage());

    }
  }
}
