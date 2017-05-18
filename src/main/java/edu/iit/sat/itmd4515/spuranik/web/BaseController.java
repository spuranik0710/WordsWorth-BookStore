/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spuranik.web;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Base controller with faces context
 *
 * @author Pooja
 */
public abstract class BaseController {

    protected FacesContext context;

    protected BaseController() {
    }

    @PostConstruct
    public void postContruct() {
        context = FacesContext.getCurrentInstance();
    }
}
