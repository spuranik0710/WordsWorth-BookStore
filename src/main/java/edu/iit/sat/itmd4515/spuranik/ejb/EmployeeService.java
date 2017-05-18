/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spuranik.ejb;

import edu.iit.sat.itmd4515.spuranik.domain.Employee;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *Employee Service extending Base Service consists of method for named query
 * Employee Service performs CRUD operation
 * @author Pooja
 */
@Named
@Stateless
public class EmployeeService {

    @PersistenceContext(unitName = "itmd4515PU")
    private EntityManager em;

    public EmployeeService() {
    }

    public void create(Employee c) {
        em.persist(c);
    }

    public void update(Employee c) {
        em.merge(c);
    }

    public void remove(Employee c) {
        em.remove(em.merge(c));
    }

    public Employee find(Long id) {
        return em.find(Employee.class, id);
    }

    public List<Employee> findAll() {
        return em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }

    public Employee findByUsername(String username) {
            return em.createNamedQuery("Employee.findByUsername", Employee.class).setParameter("username", username).getSingleResult();
        
    }

}
