/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spuranik.domain;

import java.util.GregorianCalendar;
import java.util.Set;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Pooja
 */
public class OrderDetailsTest {

    private static EntityManagerFactory emf;
    private static final Logger LOG = Logger.getLogger(OrderDetailsTest.class.getName());
    private EntityManager em;
    private EntityTransaction tx;
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

    }

    @After
    public void afterEachTestMethod() {

        em.close();
    }

    @Test
    public void validateNullPriceTest() {
        OrderDetails seed = new OrderDetails(null, 100f, 10, new GregorianCalendar(2016, 6, 11).getTime());
        Set<ConstraintViolation<OrderDetails>> violations = validator.validate(seed);

        assertFalse(violations.isEmpty());
        assertTrue(violations.size() == 1);
        assertEquals(violations.size(), 1);
    }

    @Test
    public void validatePastDateSunnyDay() {
        OrderDetails ot = new OrderDetails(10f, 100f, 10, new GregorianCalendar(2016, 6, 11).getTime());
        Set<ConstraintViolation<OrderDetails>> violations = validator.validate(ot);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void validatePastDateRainyDay() {
        OrderTable ot = new OrderTable("delivered", "ewrRuhwefhn", 987687988l, 10l, new GregorianCalendar(2026, 6, 11).getTime());
        Set<ConstraintViolation<OrderTable>> violations = validator.validate(ot);

        for (ConstraintViolation<OrderTable> violation : violations) {
            LOG.info(violation.toString());
        }

        assertFalse(violations.isEmpty());
        assertTrue(violations.size() == 1);
        assertEquals(violations.size(), 1);
    }

//    Junit Test for OnetoOne Relation
    @Test
    public void validateOnetoOnerelation() {
        OrderDetails od1 = new OrderDetails(10f, 500f, 50, new GregorianCalendar(2011, 5, 11).getTime());
        OrderDetails od2 = new OrderDetails(10f, 1000f, 100, new GregorianCalendar(2015, 5, 10).getTime());
        Product p1 = new Product("Data Networks", "Textbook", 350f, "book on networking", 10l, "Technology", new GregorianCalendar(2017, 6, 11).getTime(), "Ryan Ray");
        Product p2 = new Product("Tell me your dreams", "Book", 100f, "Best Novel", 2l, "Mystery", new GregorianCalendar(2016, 6, 11).getTime(), "Sidney Sheldon");
        od1.addProduct(p2);
        tx.begin();

        em.persist(p2);
        // od1.addProduct(p1);
        em.persist(od1);
        tx.commit();

        OrderDetails findoOrderDetails = em.find(OrderDetails.class, od1.getId());
        System.out.println("Result  " + findoOrderDetails);
        System.out.println("Result  " + findoOrderDetails.getProduct());
        assertTrue(findoOrderDetails.getProduct().getId() >= 1);

    }

}
