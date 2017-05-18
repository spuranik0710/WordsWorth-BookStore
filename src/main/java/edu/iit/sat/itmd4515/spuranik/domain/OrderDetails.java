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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 *OrderDetails Entity which consist of order details
 * Named query to find using specific attribute
 * @author Pooja
 */
@Entity
@Table(name = "OrderDetails")
@NamedQueries({
    @NamedQuery(name = "OrderDetails.findAll", query = "select u from OrderDetails u")
    ,
    @NamedQuery(name = "OrderDetails.findByQuantity", query = "SELECT u from OrderDetails u WHERE u.quantity = :quantity")
})
public class OrderDetails {
    
    
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "The productprice cannot be null")
    private Float productprice;
    
    private Float total;
    
    private int quantity;

    /**
     * Get the value of quantity
     *
     * @return the value of quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set the value of quantity
     *
     * @param quantity new value of quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    
   @Past
    @Temporal(TemporalType.DATE)
    private Date orderedon;
    
    @OneToOne
    private Product product;

    /**
     * Get the value of product
     *
     * @return the value of product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Set the value of product
     *
     * @param product new value of product
     */
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public void addProduct(Product product) {
        this.product=(product);
    }

    /**
     * Get the value of orderedon
     *
     * @return the value of orderedon
     */
    public Date getOrderedon() {
        return orderedon;
    }

    /**
     * Set the value of orderedon
     *
     * @param orderedon new value of orderedon
     */
    public void setOrderedon(Date orderedon) {
        this.orderedon = orderedon;
    }

        

    /**
     * Get the value of productprice
     *
     * @return the value of productprice
     */
    public Float getProductprice() {
        return productprice;
    }

    /**
     * Set the value of productprice
     *
     * @param productprice new value of productprice
     */
    public void setProductprice(Float productprice) {
        this.productprice = productprice;
    }

   
   
    

    public Float getTotal() {
        return total;
    }

    

    public void setTotal(Float total) {
        this.total = total;
    }
    

    public OrderDetails() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderDetails(Float productprice, Float total, int Quantity, Date orderedon) {
        
        this.productprice = productprice;
        this.total = total;
        this.quantity = Quantity;
        this.orderedon = orderedon;
    }

    @Override
    public String toString() {
        return "OrderDetails{" + "id=" + id + ", productprice=" + productprice + ", total=" + total + ", quantity=" + quantity + ", orderedon=" + orderedon + ", product=" + product + '}';
    }

    

 

    

   
    

    
}
