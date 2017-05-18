/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spuranik.web;

import edu.iit.sat.itmd4515.spuranik.domain.OrderTable;
import edu.iit.sat.itmd4515.spuranik.domain.Product;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Pooja
 */
@FacesConverter("productConverter")
public class ProductConverter implements Converter{

    private static final Logger LOG = Logger.getLogger(ProductConverter.class.getName());

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
        LOG.info("productConverter getAsString received " + value);
        Long id = Long.parseLong(value);
        OrderTable i = new OrderTable(id);
        
        return i;
     }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
         LOG.info("getAsString received " + value.toString());
        return String.valueOf( ((Product)value).getId() );
    }
    
}
