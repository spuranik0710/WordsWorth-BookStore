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
import edu.iit.sat.itmd4515.spuranik.ejb.OrderDetailsService;
import edu.iit.sat.itmd4515.spuranik.ejb.OrderReviewService;
import edu.iit.sat.itmd4515.spuranik.ejb.OrderTableService;
import edu.iit.sat.itmd4515.spuranik.ejb.ProductService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * PlaceOrder Controller extending Base Controller consists of enterprise bean,
 * post construct Along with method to createproductfororder, createorder Used
 * to create an product for order and also a method to get address and mobile
 * number when item is added to cart
 *
 * @author Pooja
 */
@ManagedBean
@ViewScoped
public class PlaceOrderController extends BaseController {

    @EJB
    private ProductService productService;

    @EJB
    private OrderDetailsService orderDetailsService;

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

    public PlaceOrderController() {
    }
    private static final Logger LOG = Logger.getLogger(PlaceOrderController.class.getName());

    private Customer customer;
    private Employee employee;
    private OrderTable ordertable;
    private List<OrderDetails> allOrderDetails;

    private long total;

    /**
     * Get the value of total
     *
     * @return the value of total
     */
    public long getTotal() {
        return total;
    }

    /**
     * Set the value of total
     *
     * @param total new value of total
     */
    public void setTotal(long total) {
        this.total = total;
    }

    public List<OrderDetails> getAllOrderDetails() {
        return allOrderDetails;
    }

    public void setAllOrderDetails(List<OrderDetails> allOrderDetails) {
        this.allOrderDetails = allOrderDetails;
    }
    private OrderReview orderreview;

    private OrderDetails orderdetails;

    /**
     * Get the value of orderdetails
     *
     * @return the value of orderdetails
     */
    public OrderDetails getOrderdetails() {
        return orderdetails;
    }

    /**
     * Set the value of orderdetails
     *
     * @param orderdetails new value of orderdetails
     */
    public void setOrderdetails(OrderDetails orderdetails) {
        this.orderdetails = orderdetails;
    }

    private Product product;

    /**
     * Get the value of product
     *
     * @return the value of product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Set the value of product
     *
     * @param product new value of product
     */
    public void setProduct(Product product) {
        this.product = product;
    }

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

    public void createProductforOrder(Product p, int quantity) {
        System.out.println("quantity" + quantity);
        OrderDetails od = new OrderDetails();

        System.out.println("quantity" + quantity);
        this.product = p;
        od.setProduct(product);
        od.setQuantity(quantity);
        od.setProductprice(p.getPrice());
        od.setOrderedon(new Date());
        od.setTotal((quantity * p.getPrice()));
        total = (long) (total + od.getTotal());
        orderDetailsService.create(od);
        product.setProductquantity(p.getProductquantity() - quantity);
        productService.update(product);
        allOrderDetails.add(od);

    }

    public void createOrder(String address, String mn) {
        System.out.println("" + address);
        System.out.println("" + mn);
        ordertable.setCustomer(customer);
        ordertable.setMobilenumber(Long.parseLong(mn));
        ordertable.setOrderreview(null);
        ordertable.setOrderdate(new Date());
        ordertable.setOrderdetails(allOrderDetails);
        ordertable.setTotalprice(total);
        ordertable.setStatus("Pending");
        ordertable.setAddress(address);
        orderTableService.create(ordertable);

    }

    @Override
    @PostConstruct
    public void postContruct() {
        super.postContruct(); //To change body of generated methods, choose Tools | Templates.

        customer = customerService.findByUsername(loginController.getRemoteUser());
        ordertable = new OrderTable();
        orderreview = new OrderReview();
        orderdetails = new OrderDetails();
        product = new Product();
        allOrderDetails = new ArrayList<>();

    }

}
