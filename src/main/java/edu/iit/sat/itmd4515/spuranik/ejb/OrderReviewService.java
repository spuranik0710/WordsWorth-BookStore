/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spuranik.ejb;

import edu.iit.sat.itmd4515.spuranik.domain.Employee;
import edu.iit.sat.itmd4515.spuranik.domain.OrderReview;
import edu.iit.sat.itmd4515.spuranik.domain.OrderTable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.Stateless;

/**
 *OrderReview Service extending Base Service consists of method for named query
 * ALong with an method updateOrderBy which is used to update order by employee
 * where set the new value by getting the old data and updating it with new data
 * @author Pooja
 */
@Stateless
public class OrderReviewService extends BaseService<OrderReview> {

    public OrderReviewService() {
        super(OrderReview.class);
    }

    public OrderReviewService(Class entityClass) {
        super(entityClass);
    }

    @Override
    public List<OrderReview> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updateOrderBy(OrderTable dataFromJSF, Employee employee) {
        OrderTable OldData = getEntityManager().find(OrderTable.class, dataFromJSF.getId());
        dataFromJSF.setAddress(OldData.getAddress());
        dataFromJSF.setCustomer(OldData.getCustomer());
        dataFromJSF.setOrderdetails(OldData.getOrderdetails());
        dataFromJSF.setOrderdate(OldData.getOrderdate());

        if (OldData.getOrderreview() == null) {
            System.out.println("" + dataFromJSF.getOrderreview().toString());
            OrderReview d = new OrderReview(OldData, employee, new Date(), 
                    dataFromJSF.getOrderreview().getDeliverydate(), dataFromJSF.getOrderreview().getShippingdetails());
            super.create(d);
            System.out.println("" + d.toString());
            dataFromJSF.setOrderreview(d);
        } else {
            dataFromJSF.getOrderreview().setDate(new Date());
            dataFromJSF.getOrderreview().setEmployee(employee);
            dataFromJSF.getOrderreview().setOrderTable(dataFromJSF);
            dataFromJSF.setOrderreview(dataFromJSF.getOrderreview());
            OrderReview or=OldData.getOrderreview();
            remove(or);
        }

        getEntityManager().merge(dataFromJSF);
    }

    public OrderReview findByOrder(OrderTable orderTable) {

        return getEntityManager()
                .createNamedQuery("OrderReview.findByOrder", OrderReview.class)
                .setParameter("ordertable", orderTable)
                .getSingleResult();
    }

}
