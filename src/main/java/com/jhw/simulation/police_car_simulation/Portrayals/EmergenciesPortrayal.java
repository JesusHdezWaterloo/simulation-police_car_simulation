/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.simulation.police_car_simulation.portrayals;

import com.jhw.simulation.police_car_simulation.main.PoliceCarsSimulation_Sim;
import com.jhw.simulation.police_car_simulation.main.SimulationMain;
import java.awt.Color;
import java.awt.Graphics2D;
import sim.portrayal.DrawInfo2D;
import sim.portrayal.geo.GeomPortrayal;
import sim.util.geo.MasonGeometry;

/**
 *
 * @author Jesus Hernandez Barrios (jhernandezb96@gmail.com)
 */
public class EmergenciesPortrayal extends GeomPortrayal {

    private final PoliceCarsSimulation_Sim state;

    public EmergenciesPortrayal(PoliceCarsSimulation_Sim state) {
        super(Color.RED, SimulationMain.cfg.getPatrolsPointsScale(), true);
        this.state = state;
    }

    @Override
    public void draw(Object object, Graphics2D graphics, DrawInfo2D info) {
        MasonGeometry em = (MasonGeometry) object;
        scale = SimulationMain.cfg.getEmergenciesPointsScale();
        paint = ((PoliceCarsSimulation_Sim) state).getEmergenciesArr().get(em.getIntegerAttribute("ID")).getRealColor();
        super.draw(object, graphics, info);
    }
}
