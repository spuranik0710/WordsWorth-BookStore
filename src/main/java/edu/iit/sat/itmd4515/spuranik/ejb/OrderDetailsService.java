/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spuranik.ejb;

import edu.iit.sat.itmd4515.spuranik.domain.OrderDetails;
import java.util.List;
import javax.ejb.Stateless;

/**
 *OrderDetail Service extending Base Service consists of method for named query
 * @author Pooja
 */

@Stateless
public class OrderDetailsService extends BaseService<OrderDetails>{

    public OrderDetailsService() {
        super(OrderDetails.class);
    }

    public OrderDetailsService(Class entityClass) {
        super(entityClass);
    }

    @Override
    public List<OrderDetails> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
    
}
