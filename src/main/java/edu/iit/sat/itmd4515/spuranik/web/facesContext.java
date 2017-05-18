/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spuranik.web;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

/**
 *
 * @author Pooja
 */
class facesContext {

    static void addMessage(Object object, FacesMessage facesMessage) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    protected FacesContext facesContext;

    protected Flash flash;

    public facesContext() {
    }

    

    @PostConstruct
    protected void postContruct() {
        facesContext = FacesContext.getCurrentInstance();
        flash = facesContext.getExternalContext().getFlash();
    }
}
