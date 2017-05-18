/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spuranik.web;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 * Login Controller extending Base Controller consists of enterprise bean, post
 * construct Login Controller is used to define the roles like Admin role Cust
 * role and Emp role for Admin customer employee doLogin and doLogout which uses
 * http Servlet request
 *
 * @author Pooja
 */
@Named
@RequestScoped
public class LoginController extends BaseController {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    public LoginController() {
    }
    @NotNull(message = "Please enter a username.")
    private String username;
    @NotNull(message = "Please enter a password.")
    private String password;

    //utility methods
    public String getRemoteUser() {
        return context.getExternalContext().getRemoteUser();
    }

    public boolean isAdmin() {
        return context.getExternalContext().isUserInRole("ADMIN_ROLE");
    }

    public boolean isCustomer() {
        return context.getExternalContext().isUserInRole("CUST_ROLE");
    }

    public boolean isEmployee() {
        return context.getExternalContext().isUserInRole("EMP_ROLE");
    }

    public String doLogin() {
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            req.login(username, password);
        } catch (ServletException ex) {
            //login failed
            LOG.log(Level.SEVERE, null, ex);
            //provide error message
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed Login", "User or Password not valid"));
            //send back to the login page
            return "/login.xhtml";
        }
        return "/welcome.xhtml?faces-redirect=true";
    }

    public String doLogout() {
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            req.logout();
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
            //mESSAGE ON FAILED LOGIN
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed Logout", "You have Failed to Logout"));
            //send back to the login page
            return "/login.xhtml";
        }
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "You have logged out", "You have logged out successfully.  Please don't forget to close your browser."));
        return "/login.xhtml";
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set the value of username
     *
     * @param username new value of username
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
