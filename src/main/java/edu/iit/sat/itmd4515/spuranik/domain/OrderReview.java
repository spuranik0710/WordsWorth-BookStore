/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spuranik.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * *Order review Entity which consist of order review
 * Employee needs to review the order and update shipping details
 * Named query to find using specific attribute
 * @author Pooja
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "OrderReview.findAll", query = "select u from OrderReview u")
    ,
    @NamedQuery(name = "OrderReview.findByOrder", query = "SELECT u from OrderReview u WHERE u.ordertable = :ordertable")
})
public class OrderReview {
    
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private OrderTable ordertable;

    @OneToOne
    private Employee employee;

    @Temporal(TemporalType.DATE)
    private Date date;
    
    @Temporal(TemporalType.DATE)
    private Date deliverydate;

    private String shippingdetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void ofOrderTable(OrderTable ordertable) {
        this.ordertable = ordertable;
    }
    public OrderTable getOrderTable() {
        return ordertable;
    }
    public void setOrderTable(OrderTable ordertable) {
        this.ordertable = ordertable;
    }
    public Employee getEmployee() {
        return employee;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public void createdbyEmployee(Employee employee) {
        this.employee = employee;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public Date getDeliverydate() {
        return deliverydate;
    }
    public void setDeliverydate(Date deliverydate) {
        this.deliverydate = deliverydate;
    }
    public String getShippingdetails() {
        return shippingdetails;
    }
    public void setShippingdetails(String shippingdetails) {
        this.shippingdetails = shippingdetails;
    }

    public OrderReview() {
    }

    public OrderReview(OrderTable ordertable, Employee employee, Date date, Date deliverydate, String shippingdetails) {
        this.ordertable = ordertable;
        this.employee = employee;
        this.date = date;
        this.deliverydate = deliverydate;
        this.shippingdetails = shippingdetails;
    }

    @Override
    public String toString() {
        return "OrderReview{" + ", date=" + date + ", deliverydate=" + deliverydate + ", shippingdetails=" + shippingdetails + '}';
    }
    
    
    
    
    
}
