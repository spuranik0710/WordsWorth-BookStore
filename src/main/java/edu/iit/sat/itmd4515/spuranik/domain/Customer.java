/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spuranik.domain;

import edu.iit.sat.itmd4515.spuranik.domain.security.User;

/**
 *Customer Entity which consist of customer attributes with proper validation using annotation.
 * Named query to find using specific attribute
 * @author Pooja
 */
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "Customer")
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "select c from Customer c")
    ,
    @NamedQuery(name = "Customer.findByEmail", query = "SELECT c from Customer c WHERE c.email = :email")
    ,
    @NamedQuery(name = "Customer.findByUsername", query = "select p from Customer p where p.user.userName = :username")
})
public class Customer extends BaseEntity{

    /**
     * @param args the command line arguments
     */
    @Id
    @Column (name="ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Size(min = 4, max = 50, message = "Firstname should be between {min} and {max}")
    private String firstName;
    
    private String lastName;
    
    
    @NotNull(message = "The email cannot be null")
    @Column(unique = true)
    private String email;
   
    private long contactNo;
   
    private String address;
    //#####################################
    
    @OneToMany(mappedBy = "Customer")
    private List<OrderTable> ordertable = new ArrayList<>();

    /**
     * Get the value of ordertable
     *
     * @return the value of ordertable
     */
    @OneToOne
    @JoinColumn(name="USERNAME")
    private User user;

    /**
     * Get the value of user
     *
     * @return the value of user
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the value of user
     *
     * @param user new value of user
     */
    public void setUser(User user) {
        this.user = user;
    }

    
    
    public List<OrderTable> getOrdertable() {
        return ordertable;
    }
    
    
    //##################################

    /**
     * Set the value of ordertable
     *
     * @param ordertable new value of ordertable
     */
    
    // #########################################
   public void setOrdertable(List<OrderTable> ordertable) {
        this.ordertable = ordertable;
    }
     public void addOrdertable(OrderTable ordertable) {
        this.ordertable.add(ordertable);
        ordertable.setCustomer(this);
    }
    
    // ##################################
     
    public Customer(String firstName, String lastName, String email, long contactNo, String address, User user) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNo = contactNo;
        this.address = address;
        this.user = user;
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
     * Get the value of password
     *
     * @return the value of password
     */
    


   

    public Customer() {
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", contactNo=" + contactNo + ", address=" + address + '}';
    }

  
  

    
    public Customer(String firstName, String lastName, String email, long contactNo, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNo = contactNo;
        this.address = address;
    }

   

    

    

    /**
     * Get the value of isadmin
     *
     * @return the value of isadmin
     */
    

    /**
     * Get the value of contactNo
     *
     * @return the value of contactNo
     */
    public long getContactno() {
        return contactNo;
    }

    /**
     * Set the value of contactNo
     *
     * @param contactNo new value of contactNo
     */
    public void setContactno(long contactNo) {
        this.contactNo = contactNo;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Get the value of lastName
     *
     * @return the value of lastName
     */
    public String getLastname() {
        return lastName;
    }

    /**
     * Set the value of lastName
     *
     * @param lastName new value of lastName
     */
    public void setLastname(String lastName) {
        this.lastName = lastName;
    }



    /**
     * Get the value of firstName
     *
     * @return the value of firstName
     */
    public String getFirstname() {
        return firstName;
    }

    /**
     * Set the value of firstName
     *
     * @param firstName new value of firstName
     */
    public void setFirstname(String firstName) {
        this.firstName = firstName;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}
