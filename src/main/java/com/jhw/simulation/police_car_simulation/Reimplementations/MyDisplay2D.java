package com.jhw.simulation.police_car_simulation.reimplementations;

import com.jhw.simulation.police_car_simulation.main.PoliceCarsSimulation_UI;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.Point;
import sim.display.Display2D;
import sim.display.GUIState;
import sim.portrayal.LocationWrapper;
import sim.util.Bag;
import sim.util.geo.MasonGeometry;

/**
 *
 * @author Jesús Hernández Barrios
 */
public class MyDisplay2D extends Display2D {

    public MyDisplay2D(double width, double height, GUIState simulation) {
        super(width, height, simulation);
        this.insideDisplay.setOpaque(false);

    }

    @Override
    public void createInspectors(Bag locationWrappers, GUIState simulation) {
        generateEventByInspector(locationWrappers, simulation);//genera 
        super.createInspectors(locationWrappers, simulation);
    }

    private void generateEventByInspector(Bag locationWrappers, GUIState simulation) {
        for (int i = 0; i < locationWrappers.size(); i++) {
            LocationWrapper act = (LocationWrapper) locationWrappers.get(i);
            if (act.getObject() instanceof MasonGeometry && act.getLocation() instanceof Point) {
                MasonGeometry mg = (MasonGeometry) act.getObject();
                if (mg.geometry instanceof LineString) {
                    ((PoliceCarsSimulation_UI) simulation).generateAtPosition(act);
                }
            }
        }
    }

}
