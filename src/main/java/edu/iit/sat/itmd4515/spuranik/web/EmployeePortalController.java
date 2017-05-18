/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spuranik.web;

import edu.iit.sat.itmd4515.spuranik.domain.Customer;
import edu.iit.sat.itmd4515.spuranik.domain.Employee;
import edu.iit.sat.itmd4515.spuranik.domain.OrderDetails;
import edu.iit.sat.itmd4515.spuranik.domain.OrderReview;
import edu.iit.sat.itmd4515.spuranik.domain.OrderTable;
import edu.iit.sat.itmd4515.spuranik.domain.Product;
import edu.iit.sat.itmd4515.spuranik.ejb.CustomerService;
import edu.iit.sat.itmd4515.spuranik.ejb.EmployeeService;
import edu.iit.sat.itmd4515.spuranik.ejb.OrderReviewService;
import edu.iit.sat.itmd4515.spuranik.ejb.OrderTableService;
import edu.iit.sat.itmd4515.spuranik.ejb.ProductService;
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
 * Employee Portal Controller extending Base Controller consists of enterprise
 * bean, post construct Along with method to getorderproductforpage, doview,
 * createUpdate etc Employee can view Customer details, Order details and the
 * products Employee can add new product Employee can update the SHipping
 * details and shipping date
 *
 * @author Pooja
 */
@Named
@RequestScoped
public class EmployeePortalController extends BaseController {

    @EJB
    private OrderReviewService orderReviewService;

    @EJB
    private ProductService productService;

    @EJB
    private OrderTableService orderTableService;

    @EJB
    private CustomerService customerService;

    @EJB
    private EmployeeService employeeService;

    @Inject
    LoginController loginController;

    private static final Logger LOG = Logger.getLogger(EmployeePortalController.class.getName());

    public EmployeePortalController() {
    }

    private Customer customer;
    private Employee employee;
    private OrderTable ordertable;
    private OrderReview orderreview;
    private Product product;

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

    public List getallOrders() {

        return orderTableService.findAll();

    }

    public String getOrderProductForPage(OrderTable o) {
        List<String> names = new ArrayList<>();
        for (OrderDetails od : o.getOrderdetails()) {
            names.add(od.toString());
        }
        return String.join(",", names);
    }

    public List getOrderForPage() {
        return orderTableService.findAll();
    }

    public String doViewOrder(OrderTable i) {
        this.ordertable = i;
        LOG.info("doViewOrder" + i.toString());
        return "/employee/viewOrder.xhtml";

    }

    public String taketoProduct() {
        return "/employee/product.xhtml";
    }

    public String docreateProduct() {
        return "/employee/createProduct.xhtml";
    }

    public String doDeleteProduct(Product pd) {
        productService.remove(pd);
        return "/employee/product.xhtml";
    }

    public String doReviewOrder(OrderTable i) {
        this.ordertable = i;
        this.orderreview = i.getOrderreview();
        LOG.info("doReviewOrder: " + ordertable.toString());
        ordertable.setOrderdetails(i.getOrderdetails());
        return "/employee/editOrder.xhtml";

    }

    public String doExecuteupdate() {

        LOG.info("doExecuteInvoice: " + ordertable.toString());
        orderReviewService.updateOrderBy(ordertable, employee);
        context.addMessage(null,
                new FacesMessage(
                        FacesMessage.SEVERITY_INFO,
                        "Successful Update!",
                        "Order reviewed- Shipping Details:-  " + ordertable.getOrderreview().getShippingdetails()
                )
        );
        return "/employee/welcome.xhtml";
    }

    public String doExecutenewProduct() {

        LOG.info("doExecutenewProduct: " + product.toString());
        productService.create(product);
        context.addMessage(null,
                new FacesMessage(
                        FacesMessage.SEVERITY_INFO,
                        "Successfully Created!",
                        "Product:-" + product.getProductname()
                )
        );
        return "/employee/product.xhtml";
    }

    @Override
    @PostConstruct
    public void postContruct() {
        super.postContruct(); //To change body of generated methods, choose Tools | Templates.
        employee = employeeService.findByUsername(loginController.getRemoteUser());
        ordertable = new OrderTable();
        orderreview = new OrderReview();
        product = new Product();
        ordertable.setOrderreview(orderreview);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
