/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spuranik.ejb;

import javax.ejb.Stateless;
import edu.iit.sat.itmd4515.spuranik.domain.Customer;
import java.util.List;
import javax.inject.Named;
import javax.persistence.EntityManager;

/**
 *Customer Service extending Base Service consists of method for named query
 * @author Pooja 
 */
@Named
@Stateless
public class CustomerService extends BaseService<Customer> {

    public CustomerService() {
        super(Customer.class);
    }

    @Override
    public List<Customer> findAll() {
        return getEntityManager().createNamedQuery("Customer.findAll", Customer.class).getResultList();
    }

    public Customer findByUsername(String username) {
        
            return getEntityManager().createNamedQuery("Customer.findByUsername", Customer.class).setParameter("username", username).getSingleResult();
        
    }

    public Customer findByEmail(String email) {
        email = "manoj@gmail.com";
        return getEntityManager()
                .createNamedQuery("Customer.findByEmail", Customer.class)
                .setParameter("email", email)
                .getSingleResult();
    }
    private EntityManager em;
   
   
}
