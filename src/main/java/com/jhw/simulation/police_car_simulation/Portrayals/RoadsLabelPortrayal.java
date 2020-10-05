/* 
 * Copyright 2011 by Mark Coletti, Keith Sullivan, Sean Luke, and
 * George Mason University Mason University Licensed under the Academic
 * Free License version 3.0
 *
 * See the file "LICENSE" for more information
 *
 * $Id$
 */
package com.jhw.simulation.police_car_simulation.portrayals;

import com.jhw.swing.util.MaterialDrawingUtils;
import java.awt.Graphics2D;
import java.awt.Paint;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.SimplePortrayal2D;
import sim.portrayal.simple.LabelledPortrayal2D;
import sim.util.geo.MasonGeometry;

public class RoadsLabelPortrayal extends LabelledPortrayal2D {

    private static final long serialVersionUID = 1L;

    public RoadsLabelPortrayal(SimplePortrayal2D child, Paint paint) {
        super(child, null, paint, true);
    }

    @Override
    public String getLabel(Object object, DrawInfo2D info) {
        if (object instanceof MasonGeometry) {
            MasonGeometry mg = (MasonGeometry) object;
            if (mg.hasAttribute("NAME")) {
                return mg.getStringAttribute("NAME");
            }
        }

        return "Desconocido";
    }

    @Override
    public void draw(Object object, Graphics2D graphics, DrawInfo2D info) {
        MaterialDrawingUtils.getAliasedGraphics(graphics);
        
        super.draw(object, graphics, info);
    }

}
