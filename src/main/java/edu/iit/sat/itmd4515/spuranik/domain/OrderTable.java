/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//////
package edu.iit.sat.itmd4515.spuranik.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;


/**
 * *Order table Entity which consist of order details and the address and mobile number of specific customer who ordered
 * Named query to find using specific attribute
 * @author Pooja
 */
@Entity
@Table(name = "OrderTable")
@NamedQueries({
    @NamedQuery(name = "OrderTable.findAll", query = "select u from OrderTable u")
    ,
    @NamedQuery(name = "OrderTable.findByAddress", query = "SELECT u from OrderTable u WHERE u.address = :address")
})
public class OrderTable {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    
    
    
    private String address;
    
    private Long mobilenumber;

    
    private Long totalprice;
    
    @Past
    @Temporal(TemporalType.DATE)
    private Date orderdate;
    
    @OneToOne
    private OrderReview orderreview;

    /**
     * Get the value of orderreview
     *
     * @return the value of orderreview
     */
    public OrderReview getOrderreview() {
        return orderreview;
    }

    /**
     * Set the value of orderreview
     *
     * @param orderreview new value of orderreview
     */
    public void setOrderreview(OrderReview orderreview) {
        this.orderreview = orderreview;
    }

    
    //@JoinColumn(name = "CUSTOMERID")
    @ManyToOne
    private Customer customer;

    public OrderTable(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Get the value of customer
     *
     * @return the value of customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Set the value of customer
     *
     * @param customer new value of customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public void addCustomer(Customer customer) {
        this.customer = customer;
    }

    

    @OneToMany
    private List<OrderDetails> orderdetails = new ArrayList<>();

    /**
     * Get the value of orderdetails
     *
     * @return the value of orderdetails
     */
    public List<OrderDetails> getOrderdetails() {
        return orderdetails;
    }

    /**
     * Set the value of orderdetails
     *
     * @param orderdetails new value of orderdetails
     */
    public void setOrderdetails(List<OrderDetails> orderdetails) {
        this.orderdetails = orderdetails;
    }

    public void addOrderdetails(OrderDetails orderdetails) {
        this.orderdetails.add(orderdetails) ;
    }
    /**
     * Get the value of shipperemail
     *
     * @return the value of shipperemail
     */
    

    /**
     * Get the value of mobilenumber
     *
     * @return the value of mobilenumber
     */
    public Long getMobilenumber() {
        return mobilenumber;
    }

    /**
     * Set the value of mobilenumber
     *
     * @param mobilenumber new value of mobilenumber
     */
    public void setMobilenumber(Long mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public Long getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Long totalprice) {
        this.totalprice = totalprice;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }


    /**
     * Get the value of address
     *
     * @return the value of address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set the value of address
     *
     * @param address new value of address
     */
    public void setAddress(String address) {
        this.address = address;
    }


    /**
     * Get the value of shippersname
     *
     * @return the value of shippersname
     */
   

    /**
     * Get the value of status
     *
     * @return the value of status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the value of status
     *
     * @param status new value of status
     */
    public void setStatus(String status) {
        this.status = status;
    }
 
    
    //Customer ID FK
   

    public OrderTable(String status, String address, Long mobilenumber, Long totalprice, Date orderdate) {
        
        this.status = status;
        this.address = address;
        this.mobilenumber = mobilenumber;
        this.totalprice = totalprice;
        this.orderdate = orderdate;
    }

    @Override
    public String toString() {
        return "OrderTable{" + "id=" + id + ", status=" + status + ", address=" + address + ", mobilenumber=" + mobilenumber + ", totalprice=" + totalprice + ", orderdate=" + orderdate + ", customer=" + customer + ", orderdetails=" + orderdetails + '}';
    }

   

    

   

    public OrderTable() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   
     
}
