/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//////
package edu.iit.sat.itmd4515.spuranik.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
/**
 * *Product Entity which consist of Product attributes with proper validation using annotation.
 * Named query to find using specific attribute
 * @author Pooja
 */
@Entity
@Table(name = "Product")
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "select u from Product u")
    ,
    @NamedQuery(name = "Product.findByProductname", query = "SELECT u from Product u WHERE u.productname = :productname")
})
public class Product {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "The product cannot be null")
    @Column(unique = true)
    private String productname;
    
    private String categoryname;
    
        private String image;

    /**
     * Get the value of image
     *
     * @return the value of image
     */
    public String getImage() {
        return image;
    }

    /**
     * Set the value of image
     *
     * @param image new value of image
     */
    public void setImage(String image) {
        this.image = image;
    }

    private Float price;
    
   @Size(max = 2000)
    private String summary;
    
    private Long productquantity;
    
    private String genre;
    
    @Past
    @Temporal(TemporalType.DATE)
    private Date yop;
    
    private String author;
    
  
    public Product() {
    }

    public Product(String productname, String genre, Date yop, String author) {
        this.productname = productname;
        this.genre = genre;
        this.yop = yop;
        this.author = author;
    }

    public Product(String productname, Date yop) {
        this.productname = productname;
        this.yop = yop;
    }

    public Product(String productname, String categoryname, Float price, String summary, Long productquantity, String genre, Date yop, String author) {
        this.productname = productname;
        this.categoryname = categoryname;
        this.price = price;
        this.summary = summary;
        this.productquantity = productquantity;
        this.genre = genre;
        this.yop = yop;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", productname=" + productname + ", categoryname=" + categoryname + ", price=" + price + ", summary=" + summary + ", productquantity=" + productquantity + ", genre=" + genre + ", yop=" + yop + ", author=" + author + '}';
    }
    

    /**
     * Get the value of author
     *
     * @return the value of author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Set the value of author
     *
     * @param author new value of author
     */
    public void setAuthor(String author) {
        this.author = author;
    }


    /**
     * Get the value of yop
     *
     * @return the value of yop
     */
    public Date getYop() {
        return yop;
    }

    /**
     * Set the value of yop
     *
     * @param yop new value of yop
     */
    public void setYop(Date yop) {
        this.yop = yop;
    }

    /**
     * Get the value of genre
     *
     * @return the value of genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Set the value of genre
     *
     * @param genre new value of genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Get the value of productquantity
     *
     * @return the value of productquantity
     */
    public Long getProductquantity() {
        return productquantity;
    }
    

    /**
     * Set the value of productquantity
     *
     * @param productquantity new value of productquantity
     */
    public void setProductquantity(Long productquantity) {
        this.productquantity = productquantity;
    }
    

    /**
     * Get the value of summary
     *
     * @return the value of summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Set the value of summary
     *
     * @param summary new value of summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Get the value of price
     *
     * @return the value of price
     */
    public Float getPrice() {
        return price;
    }

    /**
     * Set the value of price
     *
     * @param price new value of price
     */
    public void setPrice(Float price) {
        this.price = price;
    }


    /**
     * Get the value of categoryname
     *
     * @return the value of categoryname
     */
    public String getCategoryname() {
        return categoryname;
    }

    /**
     * Set the value of categoryname
     *
     * @param categoryname new value of categoryname
     */
    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    

    /**
     * Get the value of productname
     *
     * @return the value of productname
     */
    public String getProductname() {
        return productname;
    }

    /**
     * Set the value of productname
     *
     * @param productname new value of productname
     */
    public void setProductname(String productname) {
        this.productname = productname;
    }

    
    
    
    
    
}
