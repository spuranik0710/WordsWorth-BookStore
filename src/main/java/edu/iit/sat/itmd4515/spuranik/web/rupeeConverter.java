/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spuranik.web;

import java.text.DecimalFormat;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *Dollar to rupee converter
 * @author Pooja
 */
@FacesConverter("rupeeConverter")
public class rupeeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return value;
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent component, Object value) {
        float amountInDollars = Float.parseFloat(value.toString());
        double ammountInRupees = amountInDollars * 64;
        DecimalFormat df = new DecimalFormat("###,##0.##");
        return df.format(ammountInRupees);
    }
}
