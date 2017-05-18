/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spuranik.web;

import edu.iit.sat.itmd4515.spuranik.domain.OrderReview;
import edu.iit.sat.itmd4515.spuranik.domain.OrderTable;
import edu.iit.sat.itmd4515.spuranik.ejb.OrderReviewService;
import edu.iit.sat.itmd4515.spuranik.ejb.OrderTableService;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *Admin Portal Controller extending Base Controller consists of enterprise bean
 * Along with method to doview Admin can view all the orders using this method
 * @author Pooja
 */
@Named
@RequestScoped
public class AdminPortalController extends BaseController {

    @EJB
    private OrderReviewService orderReviewService;

    @EJB
    private OrderTableService orderTableService;

    @Inject
    LoginController loginController;

    private static final Logger LOG = Logger.getLogger(AdminPortalController.class.getName());

    public AdminPortalController() {
    }

    private OrderTable ordertable;
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

    public List getallOrders() {

        return orderTableService.findAll();

    }

    public String doView(OrderTable i) {
        this.ordertable = i;

        return "/admin/viewO.xhtml";

    }

    @Override
    @PostConstruct
    public void postContruct() {
        super.postContruct(); //To change body of generated methods, choose Tools | Templates.

        ordertable = new OrderTable();

    }

}
