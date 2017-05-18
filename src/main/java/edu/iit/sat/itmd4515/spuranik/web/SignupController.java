/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spuranik.web;

import edu.iit.sat.itmd4515.spuranik.domain.Customer;
import edu.iit.sat.itmd4515.spuranik.domain.Employee;
import edu.iit.sat.itmd4515.spuranik.domain.security.Group;
import edu.iit.sat.itmd4515.spuranik.domain.security.User;
import edu.iit.sat.itmd4515.spuranik.ejb.CustomerService;
import edu.iit.sat.itmd4515.spuranik.ejb.EmployeeService;
import edu.iit.sat.itmd4515.spuranik.ejb.GroupService;
import edu.iit.sat.itmd4515.spuranik.ejb.UserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Pooja
 */
@Named
@RequestScoped
public class SignupController extends BaseController {

    @EJB
    private UserService userService;

    @EJB
    private GroupService groupService;

    @EJB
    private EmployeeService employeeService;

    @EJB
    private CustomerService customerService;

    private User user;

    private Group group;

    /**
     * Get the value of group
     *
     * @return the value of group
     */
    public Group getGroup() {
        return group;
    }

    /**
     * Set the value of group
     *
     * @param group new value of group
     */
    public void setGroup(Group group) {
        this.group = group;
    }

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

    private static final Logger LOG = Logger.getLogger(SignupController.class.getName());

    public SignupController() {
        super();
    }
    @NotNull(message = "Please enter a username.")
    private String username;
    @NotNull(message = "Please enter a password.")
    private String password;

    //utility methods
    public String getRemoteUser() {
        return context.getExternalContext().getRemoteUser();
    }

    private Customer customer;

    private Employee employee;

    /**
     * Get the value of employee
     *
     * @return the value of employee
     */
    public Employee getEmployee() {
        return employee;
    }

    @PostConstruct
    private void postConstruct() {
        employee = new Employee();
        customer = new Customer();
        
        user = new User();
        customer.setUser(user);
        
        super.postContruct();
    }

    /**
     * Set the value of employee
     *
     * @param employee new value of employee
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public String createcustomer() {
        try {
            LOG.info("CREATE CUSTOMER CONTROLLER");
//            group = new Group("CUSTOMERS", "Group of Customer");
            
            //user.addGroup(group);
            LOG.log(Level.SEVERE, customer.toString());
          
            userService.createnewuser(customer.getUser());

          //  customer.setUser(user);

            customerService.create(customer);
        //    facesContext.addMessage(null, new FacesMessage("Successfully Created Customer, Now Login Again"));

        } catch (Exception e) {
            LOG.log(Level.INFO, "Creating a new user has encountered Error", e);
           // facesContext.addMessage(null, new FacesMessage("Creating a new user has encountered Error please use different username" + e));
        }
        return "/login.xhtml";
    }

    public String doLogout() {
        HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            req.logout();
        } catch (ServletException ex) {
            LOG.log(Level.SEVERE, null, ex);
            //provide error message
           // context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed Logout", "You have Failed to Logout"));
            //send back to the login page
            return "/login.xhtml";
        }
     //   context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "You have logged out", "You have logged out successfully.  Please don't forget to close your browser."));
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
