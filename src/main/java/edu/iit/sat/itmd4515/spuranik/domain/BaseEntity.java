/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spuranik.domain;

import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Base Entity 
 * @author pooja
 */
@MappedSuperclass
public class BaseEntity {

    

    @Temporal(TemporalType.TIMESTAMP)
//    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date registeredDate;

    /**
     *
     */
    @PreUpdate
    @PrePersist
    public void doRegisteredDate() {
        registeredDate = new Date();
    }

    /**
     *
     * @return
     */
    

    /**
     *
     * @return
     */
    public Date getRegisteredDate() {
        return registeredDate;
    }

    /**
     *
     * @param registeredDate
     */
    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

}
