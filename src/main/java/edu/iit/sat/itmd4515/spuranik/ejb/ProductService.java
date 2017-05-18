/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spuranik.ejb;

import edu.iit.sat.itmd4515.spuranik.domain.Product;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *Product Service extending Base Service consists of method for named query
 * @author Pooja
 */
@Named
@Stateless
public class ProductService extends BaseService<Product>{

    public ProductService() {
        super(Product.class);
    }

    @Override
    public List<Product> findAll() {
        return getEntityManager().createNamedQuery("Product.findAll", Product.class).getResultList();
    }
    
    
}
