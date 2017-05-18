/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spuranik.domain;
///
import java.util.GregorianCalendar;
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
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
/**
 *
 * @author Pooja
 */
public class ProductTest {
    
    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;
    private static Validator validator;
    private static final Logger LOG = Logger.getLogger(CustomerTest.class.getName());
    
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
        
        Product prod = new Product("2 States","Book",60f,"fjhsdjkfhfbhfjhf",10l,"Romance",new GregorianCalendar(2016, 6, 11).getTime(),"Chetan Bhagat");
        tx.begin();
        em.persist(prod);
        tx.commit();
    }
    
    @After
    public void afterEachTestMethod(){
        
        Product prod = 
                em.createNamedQuery("Product.findByProductname", Product.class)
                        .setParameter("productname", "2 States")
                        .getSingleResult();
        
        tx.begin();
        em.remove(prod);
        
        assertEquals(0l, em.createNamedQuery("Product.findByProductname", Product.class)
                       .setParameter("productname", "2 States")
                       .getResultList().size());
        tx.commit();
        
        em.close();
    }
    
    @Test
    public void validateNullProductNameTest() {
        Product prod = new Product(null,"Book",60f,"fjhsdjkfhfbhfjhf",10l,"Romance",new GregorianCalendar(2016, 6, 11).getTime(),"Chetan Bhagat");
        Set<ConstraintViolation<Product>> violations = validator.validate(prod);

              
        assertFalse(violations.isEmpty());
      assertTrue(violations.size() == 1);
        assertEquals(violations.size(), 1);
    }
    
    @Test
  public void shouldRaiseNoConstraintViolation() {

    Product prod = new Product("2 States","Book",60f,"fjhsdjkfhfbhfjhf",10l,"Romance",new GregorianCalendar(2016, 6, 11).getTime(),"Chetan Bhagat");

    Set<ConstraintViolation<Product>> violations = validator.validate(prod);
    assertEquals(0, violations.size());
  }

  
    @Test
    public void validatePastDateSunnyDay() {
        Product ot = new Product("2 States","Book",60f,"fjhsdjkfhfbhfjhf",10l,"Romance",new GregorianCalendar(2016, 6, 11).getTime(),"Chetan Bhagat");
        Set<ConstraintViolation<Product>> violations = validator.validate(ot);
        assertTrue(violations.isEmpty());
    }
   
    
    
   @Test
    public void verifySeedData(){
        List<Product> prods = 
                em.createNamedQuery("Product.findByProductname", Product.class)
                        .setParameter("productname", "2 States")
                        .getResultList();
        
        assertEquals(prods.size(), 1);
        assertSame(prods.get(0).getProductname(), "2 States");
    }
    
   @Test(expected = RollbackException.class)
    public void persistNewUserShouldFailRainyDayTest(){
        
        Product prod = new Product("2 States","Book",60f,"fjhsdjkfhfbhfjhf",10l,"Romance",new GregorianCalendar(2016, 6, 11).getTime(),"Chetan Bhagat");
        
        tx.begin();
        prod.setId(1l);
        em.persist(prod);       
        tx.commit();
       
    }

    private void displayContraintViolations(Set<ConstraintViolation<Product>> constraintViolations) {
    for (ConstraintViolation constraintViolation : constraintViolations) {
      System.out.println("### " + constraintViolation.getRootBeanClass().getSimpleName() +
              "." + constraintViolation.getPropertyPath() + " - Invalid Value = " + constraintViolation.getInvalidValue() + " - Error Msg = " + constraintViolation.getMessage());

    }
  }
    
}
