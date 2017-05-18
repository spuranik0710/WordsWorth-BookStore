/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spuranik.web;

import edu.iit.sat.itmd4515.spuranik.domain.Customer;
import edu.iit.sat.itmd4515.spuranik.domain.Employee;
import edu.iit.sat.itmd4515.spuranik.domain.*;
import edu.iit.sat.itmd4515.spuranik.ejb.CustomerService;
import edu.iit.sat.itmd4515.spuranik.ejb.EmployeeService;
import edu.iit.sat.itmd4515.spuranik.ejb.OrderReviewService;
import edu.iit.sat.itmd4515.spuranik.ejb.OrderTableService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *Customer Portal Controller extending Base Controller consists of enterprise bean, post construct
 * Along with method to getorderpage, doview etc
 * Customer can view Customer details, Order details and the products
 * Customer can delete the order using doDeleteOrder method
 * Customer can update the address using doUpdateOrder method
 * @author Pooja
 */
@Named
@RequestScoped
public class CustomerPortalController extends BaseController {
    
    @EJB
    private OrderReviewService orderReviewService;
    
    @EJB
    private OrderTableService orderTableService;
    
    @EJB
    private CustomerService customerService;
    
    @EJB
    private EmployeeService employeeService;
    
    @Inject
    LoginController loginController;
    
    public CustomerPortalController() {
    }
    private static final Logger LOG = Logger.getLogger(CustomerPortalController.class.getName());
    
    @Override
    @PostConstruct
    public void postContruct() {
        super.postContruct(); 
        customer = customerService.findByUsername(loginController.getRemoteUser());
        
        ordertable = new OrderTable();
        orderreview=new OrderReview();
    }
    
    private Customer customer;
    private Employee employee;
    
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

    private OrderTable ordertable;
    
    public String getOrderProductForPage(OrderTable o) {
        List<String> names = new ArrayList<>();
        for (OrderDetails od : o.getOrderdetails()) {
            names.add(od.getProduct().getProductname());
        }
        return String.join(",", names);
    }
    
    public String doViewOrder(OrderTable i) {
        this.ordertable = i;
        LOG.info("doViewOrder" + i.toString());
        return "/customer/viewOrder.xhtml";
        
    }

    public String doDeleteOrder(OrderTable i) {
        this.ordertable = i;
       
        LOG.info("doDeleteOrder: " + i.toString());
        this.orderreview=i.getOrderreview();
        if(i.getOrderreview() != null){
            orderreview=orderReviewService.findByOrder(i);
            orderreview.setOrderTable(null);
            System.out.println(orderreview.toString());
            orderReviewService.update(orderreview);
        }
        orderTableService.remove(i);
        if(i.getOrderreview() != null){
            orderReviewService.remove(orderreview);
        }
        context.addMessage(null,
                new FacesMessage(
                        FacesMessage.SEVERITY_INFO,
                        "Successful Deleted! Please refresh to view the changes!",""
                        
                )
        );
        
        return "/customer/welcome.xhtml";
        
    }

    public String doCreateOrder(OrderTable i) {
        return "/customer/createOrder.xhtml";
        
    }

    public String doUpdateOrder(OrderTable i) {
        this.ordertable = i;
        i.setCustomer(customer);
        LOG.info("doUpdateOrder: " + ordertable.toString());
        ordertable.setOrderdetails(i.getOrderdetails());
        return "/customer/editOrder.xhtml";
        
    }

    public String doExecuteUpdate() {
        ordertable.setCustomer(customer);
        LOG.info("doExecuteUpdateOrder: " + ordertable.toString());
        orderTableService.update(ordertable);
        context.addMessage(null,
                new FacesMessage(
                        FacesMessage.SEVERITY_INFO,
                        "Successful Update!",
                        "Updated Order " + ordertable.getAddress()
                )
        );
        return "/customer/welcome.xhtml";
    }

    /**
     * Get the value of ordertable
     *
     * @return the value of ordertable
     */
    
    public OrderTable getOrdertable() {
        return ordertable;
    }

    /**
     * Set the value of ordertable
     *
     * @param ordertable new value of ordertable
     */
    public void setOrdertable(OrderTable ordertable) {
        this.ordertable = ordertable;
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

    /**
     * Get the value of employee
     *
     * @return the value of employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Set the value of employee
     *
     * @param employee new value of employee
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
