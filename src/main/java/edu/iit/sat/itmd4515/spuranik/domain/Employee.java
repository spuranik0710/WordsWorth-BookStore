/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spuranik.domain;

import edu.iit.sat.itmd4515.spuranik.domain.security.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Employee Entity which consist of employee attributes with proper validation using annotation.
 * Named query to find using specific attribute
 * @author Pooja
 */
@Entity
@Table(name = "Employee")
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "select e from Employee e")
    ,
    @NamedQuery(name = "Employee.findByEmail", query = "SELECT c from Employee c WHERE c.email = :email")
     ,
    @NamedQuery(name = "Employee.findByUsername", query = "select p from Employee p where p.user.userName = :username")   
})
public class Employee extends BaseEntity{
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 4, max = 50, message = "Firstname should be between {min} and {max}")
    private String firstName;
    
    private String lastName;
    
    
    @NotNull(message = "The email cannot be null")
    @Column(unique = true)
    private String email;
   
    private long contactNo;
   
    private String address;
    
    @OneToOne
    @JoinColumn(name = "USERNAME")
    private User user;
     
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
    


   

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", contactNo=" + contactNo + ", address=" + address + '}';
    }

   

    public Employee(String firstName, String lastName, String email, long contactNo, String address) {
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
