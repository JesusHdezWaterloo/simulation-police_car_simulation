package Agents;

import Inner.EmergencyStateType_Enum;
import PoliceCarsSimulation.PoliceCarsSimulation_Sim;
import PoliceCarsSimulation.SimulationMain;
import com.vividsolutions.jts.geom.LineString;
import java.awt.Color;
import sim.engine.*;
import sim.portrayal.LocationWrapper;
import sim.util.geo.MasonGeometry;

/**
 * Class that control the agent Emergency.
 *
 * @author Jesús Hernández Barrios
 */
public class Emergency implements Steppable {

    /**
     * The PoliceCarsSimulation_Sim where the agent will live.
     */
    PoliceCarsSimulation_Sim world;

    /**
     * Point that denotes emergency's position
     */
    private final MasonGeometry location;

    /**
     * Unic id of the emergency in the energencie's map
     */
    private final int id;

    /**
     * State of the emergency. Start at ACTIVE
     */
    private Enum emergencyState = EmergencyStateType_Enum.ACTIVE;

    private int demand = 0;
    private int increment = 1;

    /**
     * Start time of the system where the patrol is called to atend to an
     * emergency.
     */
    private double startTimeOfResponse = 0;

    /**
     * Generate a emergency in the street contained in the object of
     * LocationWrapper.
     *
     * @param act
     * @param sim
     */
    public Emergency(LocationWrapper act, PoliceCarsSimulation_Sim sim) {
        world = sim;
        id = sim.getEmergenciesArr().size();

        LineString line = (LineString) ((MasonGeometry) act.getObject()).getGeometry();
        location = new MasonGeometry(line.getStartPoint());
        location.isMovable = true;//has to be in true for the scale work in real time
        location.addIntegerAttribute("ID", id);

        demand = sim.random.nextInt(100) + 1;
        increment = sim.random.nextInt(30) + 1;

        startTimeOfResponse = world.schedule.getTime();
    }

    /**
     * Generate a emergency in any place of the map.
     *
     * @param sim
     */
    public Emergency(PoliceCarsSimulation_Sim sim) {
        world = sim;
        id = sim.getEmergenciesArr().size();

        int wich = sim.random.nextInt(sim.getRoads().getGeometries().numObjs);
        MasonGeometry mg = (MasonGeometry) sim.getRoads().getGeometries().objs[wich];

        LineString line = (LineString) mg.getGeometry();
        location = new MasonGeometry(line.getStartPoint());
        location.isMovable = true;//has to be in true for the scale work in real time
        location.addIntegerAttribute("ID", id);

        demand = sim.random.nextInt(100) + 1;
        increment = sim.random.nextInt(30) + 1;

        startTimeOfResponse = world.schedule.getTime();

        System.out.println("demand: " + demand + " increment: " + increment);
    }

    /**
     * Return geometry representing emergency location.
     *
     * @return geometry representing emergency location.
     */
    public MasonGeometry getGeometry() {
        return location;
    }

    /**
     * Return a String indicating the emergency type.
     *
     * @return a String indicating the emergency type.
     */
    public String getType() {
        return location.getStringAttribute("TYPE");
    }

    /**
     * Return the emergency id.
     *
     * @return the emergency id.
     */
    public int getId() {
        return id;
    }

    /**
     * Return the emergency state.
     *
     * @return the emergency state.
     */
    public Enum getEmergencyState() {
        return emergencyState;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public int getIncrement() {
        return increment;
    }

    public void setIncrement(int increment) {
        this.increment = increment;
    }

    /**
     * Step that execute the specific emergency behavior.
     *
     * @param state PoliceCarsSimulation_Sim where it live.
     */
    @Override
    public void step(SimState state) {
        if (emergencyState == EmergencyStateType_Enum.ACTIVE) {
            demand += increment;
            System.out.println(demand);
        }
    }

    /**
     * Change the emergency state to "COMPLETED".
     *
     */
    public void completeEmergency() {
        this.emergencyState = EmergencyStateType_Enum.COMPLETED;

        //Calculate delay time
        double totalDelay = world.schedule.getTime() - startTimeOfResponse;
        SimulationMain.reportResponseTime(totalDelay);
    }

    /**
     * Return the real color of the emergency.<br/>
     * It can be DARK_GRAY if the emergency state is ACTIVE or GREEN if it's
     * COMPLETED.
     *
     * @return
     */
    public Color getRealColor() {
        if (emergencyState == EmergencyStateType_Enum.ACTIVE) {
            return Color.DARK_GRAY;
        } else if (emergencyState == EmergencyStateType_Enum.COMPLETED) {
            return Color.GREEN;
        }
        return Color.BLACK;
    }

    void atend(int ofert) {
        demand -= ofert;
        if (demand < 0) {
            demand = 0;
            completeEmergency();
        }
    }

}
