/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spuranik.ejb;

import edu.iit.sat.itmd4515.spuranik.domain.OrderTable;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 * OrderTable Service extending Base Service consists of method for named query
 * Along with two methods update and remove
 *
 * @author Pooja
 */
@Named
@Stateless
public class OrderTableService extends BaseService<OrderTable> {

    public OrderTableService() {
        super(OrderTable.class);
    }

    @Override
    public List<OrderTable> findAll() {
        return getEntityManager().createNamedQuery("OrderTable.findAll", OrderTable.class).getResultList();
    }

    @Override
    public void update(OrderTable dataFromJSF) {
        OrderTable oldDataFromDatabase = getEntityManager().find(OrderTable.class, dataFromJSF.getId());
        dataFromJSF.setOrderdetails(oldDataFromDatabase.getOrderdetails());
        dataFromJSF.setOrderdate(oldDataFromDatabase.getOrderdate());
        dataFromJSF.setStatus(oldDataFromDatabase.getStatus());
        dataFromJSF.setOrderreview(oldDataFromDatabase.getOrderreview());

        getEntityManager().merge(dataFromJSF);
    }

    @Override
    public void remove(OrderTable o) {

        o = getEntityManager().getReference(OrderTable.class, o.getId());

        super.remove(o);
    }

}
