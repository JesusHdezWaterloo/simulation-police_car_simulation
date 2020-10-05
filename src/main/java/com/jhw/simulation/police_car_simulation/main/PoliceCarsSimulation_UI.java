package com.jhw.simulation.police_car_simulation.main;

import com.jhw.simulation.police_car_simulation.chart.ResponseTimeChart_Class;
import com.jhw.simulation.police_car_simulation.chart.DistanceCoveredChart_Class;
import com.jhw.simulation.police_car_simulation.portrayals.EmergenciesPortrayal;
import com.jhw.simulation.police_car_simulation.portrayals.PatrolsPortrayal;
import com.jhw.simulation.police_car_simulation.reimplementations.MyDisplay2D;
import com.jhw.simulation.police_car_simulation.portrayals.RoadsLabelPortrayal;
import java.awt.*;
import java.util.LinkedList;
import javax.swing.JFrame;
import sim.display.*;
import sim.engine.SimState;
import sim.portrayal.*;
import sim.portrayal.geo.*;

/**
 * Class that describe the simulation's visualization.
 *
 * @author Jesús Hernández Barrios
 */
public class PoliceCarsSimulation_UI extends GUIState {

    /**
     * Display of the simulation.
     */
    private MyDisplay2D display;

    /**
     * Frame of the simulation.
     */
    private JFrame displayFrame;

    /**
     * GeomVectorFieldPortrayal to show the roads.
     */
    private final GeomVectorFieldPortrayal roadsPortrayal = new GeomVectorFieldPortrayal();

    /**
     * GeomVectorFieldPortrayal to show the junctions.
     */
    private final GeomVectorFieldPortrayal junctionsPortrayal = new GeomVectorFieldPortrayal();

    /**
     * GeomVectorFieldPortrayal arrays to show the roads.
     */
    private final LinkedList<GeomVectorFieldPortrayal> areasPortrayal = new LinkedList<>();

    /**
     * GeomVectorFieldPortrayal to show the patrols.
     */
    private final GeomVectorFieldPortrayal patrolsPortrayal = new GeomVectorFieldPortrayal();

    /**
     * GeomVectorFieldPortrayal to show the emergencies.
     */
    private final GeomVectorFieldPortrayal emergenciesPortrayal = new GeomVectorFieldPortrayal();

    /**
     * Response time chart.
     */
    private ResponseTimeChart_Class respTimeChart;

    /**
     * Distance chart.
     */
    private DistanceCoveredChart_Class distChart;

    /**
     * Create the GUI an include the Simulation State.
     *
     * @param state
     */
    public PoliceCarsSimulation_UI(SimState state) {
        super(state);
    }

    /**
     * Create the GUI and the Simulation State.
     *
     * @throws Exception
     */
    public PoliceCarsSimulation_UI() throws Exception {
        super(new PoliceCarsSimulation_Sim(System.currentTimeMillis()));
    }

    /**
     * Initialize the simulation. Create the display, create the charts, setup
     * all the GeomVectorFieldPortrayal and attach it to the display, and setup
     * the portrayals.
     *
     * @param controller Controller of the simulation
     */
    @Override
    public void init(Controller controller) {
        super.init(controller);

        display = new MyDisplay2D(SimulationMain.cfg.getDisplayWidth(), SimulationMain.cfg.getDisplayHeight(), this);

        //add antialiasing and interpolation
        display.insideDisplay.setupHints(true, true, true);

        //put the areas firts, for it go under all the other
        PoliceCarsSimulation_Sim sim = (PoliceCarsSimulation_Sim) state;
        for (int i = 0; i < sim.getAreas().size(); i++) {
            areasPortrayal.add(new GeomVectorFieldPortrayal());
            display.attach(areasPortrayal.getLast(), "Área " + (i + 1));
        }

        //put the roads and the junctions second
        display.attach(roadsPortrayal, "Calles", true);
        display.attach(junctionsPortrayal, "Intersecciones", true);

        //finally put the agents, patrols and emergencies
        display.attach(emergenciesPortrayal, "Emergencias", true);
        display.attach(patrolsPortrayal, "Patrullas", true);

        displayFrame = display.createFrame();
        controller.registerFrame(displayFrame);
        displayFrame.setSize(SimulationMain.cfg.getFrameWidth(), SimulationMain.cfg.getFrameHeight());
        displayFrame.setVisible(true);

        setupPortrayals();

        respTimeChart = new ResponseTimeChart_Class(this);
        controller.registerFrame(respTimeChart.getChartFrame());

        distChart = new DistanceCoveredChart_Class(this);
        controller.registerFrame(distChart.getChartFrame());

    }

    /**
     * Dispose the display.
     */
    public void dispose() {
        displayFrame.dispose();
    }

    /**
     * Set up all the portrayals, put in it the corresponding field and the
     * visualization mode.
     */
    public void setupPortrayals() {
        PoliceCarsSimulation_Sim sim = (PoliceCarsSimulation_Sim) state;

        roadsPortrayal.setField(sim.getRoads());
        roadsPortrayal.setPortrayalForAll(new RoadsLabelPortrayal(new GeomPortrayal(Color.BLACK, true), Color.BLUE));

        junctionsPortrayal.setField(sim.getJunctions());
        junctionsPortrayal.setPortrayalForAll(new GeomPortrayal(Color.LIGHT_GRAY, SimulationMain.cfg.getJunctionsPointsScale(), true));

        emergenciesPortrayal.setField(sim.getEmergencies());
        emergenciesPortrayal.setPortrayalForAll(new EmergenciesPortrayal(sim));

        patrolsPortrayal.setField(sim.getPatrols());
        patrolsPortrayal.setPortrayalForAll(new PatrolsPortrayal(sim));

        display.reset();
        display.repaint();
   }

    /**
     * Return the name of the simulation.
     *
     * @return the name of the simulation.
     */
    public static String getName() {
        return "Controlador Simulación Patrullas";
    }

    /**
     * Generate a patrol o a emergency at the specified location.
     *
     * @param loc LocationWrapper with the position.
     */
    public void generateAtPosition(LocationWrapper loc) {
        com.jhw.simulation.police_car_simulation.inner.AgentType_Enum type = SimulationMain.getTipeNewInserted();
        switch (type) {
            case PATROL://police car
                addPoliceCar(loc);
                break;
            case EMERGENCY://emergency
                addEmergency(loc);
                break;
        }

    }

    /**
     * Add a police car to the simulation in the LocationWrapper position.
     *
     * @param loc LocationWrapper with the position.
     */
    private void addPoliceCar(LocationWrapper loc) {
        ((PoliceCarsSimulation_Sim) state).addPoliceCar(loc);
    }

    /**
     * Add a emergency to the simulation in the LocationWrapper position.
     *
     * @param loc LocationWrapper with the position.
     */
    private void addEmergency(LocationWrapper loc) {
        ((PoliceCarsSimulation_Sim) state).addEmergency(loc);
    }

    /**
     * Report to the response time chart the next value.
     *
     * @param delay Value of the response time reported by the patrol.
     */
    void reportResponseTime(double delay) {
        respTimeChart.addInfo(delay);
    }

    /**
     * Report to the distance covered chart the next value.
     *
     * @param delay Value of the distance covered reported by the patrol.
     */
    void reportDistanceCovered(double dist) {
        distChart.addInfo(dist);
    }

    /**
     * Generate a patrol o a emergency at any point of the map.
     */
    void generateRandomEmergency() {
        ((PoliceCarsSimulation_Sim) state).addEmergency();
    }

}
