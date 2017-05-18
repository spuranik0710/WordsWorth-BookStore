/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spuranik.domain;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
;
import javax.persistence.Persistence;
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
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
////

/**
 *
 * @author Pooja
 */


public class OrderTableTest {

    private static EntityManagerFactory emf;
    private EntityManager em;
    private EntityTransaction tx;
    private static final Logger LOG = Logger.getLogger(OrderTableTest.class.getName());
    private static Validator validator;

    @BeforeClass
    public static void beforeClassTestFixture() {
        emf = Persistence.createEntityManagerFactory("itmd4515PU_TEST");
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @AfterClass
    public static void afterClassTestFixture() {
        emf.close();
    }

    @Before
    public void beforeEachTestMethod() {
        em = emf.createEntityManager();
        tx = em.getTransaction();

        OrderTable ot = new OrderTable("delivered", "UPS", 987687988l, 10l, new GregorianCalendar(2016, 6, 11).getTime());
        tx.begin();
        em.persist(ot);
        tx.commit();
    }

    @After
    public void afterEachTestMethod() {

        em.close();
    }

    @Test
    public void validateOnetoManyrelation() {
        OrderTable ot1 = new OrderTable("Pending", "USPS Chicago", 987857988l, 1000l, new GregorianCalendar(2015, 5, 10).getTime());
        OrderDetails od1 = new OrderDetails(10f, 500f, 50, new GregorianCalendar(2011, 5, 11).getTime());
        OrderDetails od2 = new OrderDetails(10f, 1000f, 100, new GregorianCalendar(2015, 5, 10).getTime());
        Product p1 = new Product("Data Networks", "Textbook", 350f, "book on networking", 10l, "Technology", new GregorianCalendar(2017, 6, 11).getTime(), "Ryan Ray");
        Product p2 = new Product("Tell me your dreams", "Book", 100f, "Best Novel", 2l, "Mystery", new GregorianCalendar(2016, 6, 11).getTime(), "Sidney Sheldon");

        od1.addProduct(p2);
        ot1.addOrderdetails(od1);
        tx.begin();

        em.persist(p2);
        em.persist(od1);
        em.persist(ot1);
        tx.commit();

        OrderDetails findorderDetails = em.find(OrderDetails.class, od1.getId());
        System.out.println("Result  " + findorderDetails);
        System.out.println("Result  " + findorderDetails.getProduct());
        assertTrue(findorderDetails.getProduct().getId() >= 1);

        //assertTrue();
    }

    @Test
    public void verifyOrderTableData() {
        List<OrderTable> trans
                = em.createNamedQuery("OrderTable.findByAddress", OrderTable.class)
                        .setParameter("address", "UPS")
                        .getResultList();

        assertEquals(trans.size(), 4);

    }

    @Test
    public void validatePastDateSunnyDay() {
        OrderTable ot = new OrderTable("delivered", "UPS", 987687988l, 10l, new GregorianCalendar(2016, 6, 11).getTime());
        Set<ConstraintViolation<OrderTable>> violations = validator.validate(ot);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void validatePastDateRainyDay() {
        OrderTable ot = new OrderTable("delivered", "UPS", 987687988l, 10l, new GregorianCalendar(2026, 6, 11).getTime());
        Set<ConstraintViolation<OrderTable>> violations = validator.validate(ot);

        for (ConstraintViolation<OrderTable> violation : violations) {
            LOG.info(violation.toString());
        }

        assertFalse(violations.isEmpty());
        assertTrue(violations.size() == 1);
        assertEquals(violations.size(), 1);
    }

    @Test
    public void persistOrderTableTest() {
        OrderTable o1 = new OrderTable("delivered", "UPS", 987687988l, 10l, new GregorianCalendar(2016, 6, 11).getTime());

        tx.begin();

        assertNull("OrderTable ID should be null before persist and commit", o1.getId());
        em.persist(o1);
        assertNull("OrderTable ID should be null after persist but before commit", o1.getId());
        tx.commit();
        System.out.println(o1.getId());
        OrderTable o2 = em.find(OrderTable.class, o1.getId());
        System.out.println("OrderTable Id " + o2.getId() + " OrderTable shippersname " + o2.getAddress());
        assertEquals(o2.getAddress(), "UPS");
        assertNotNull("OrderTable ID should not be null after persist and commit", o1.getId());
        assertTrue("OrderTable ID should be greater than 0 after commit", o1.getId() > 0);

        //Update Query to set new transaction type
        tx.begin();

        o2.setAddress("kkk");
        tx.commit();
        System.out.println("OrderTable Id " + o2.getId() + "  Updated OrderTable shippersname " + o2.getAddress());
    }

}
