package com.jhw.simulation.police_car_simulation.main;

import com.jhw.simulation.police_car_simulation.inner.AgentType_Enum;
import com.jhw.simulation.police_car_simulation.utils.Configuration_Class;
import com.jhw.simulation.police_car_simulation.utils.Utility_Class;
import com.jhw.simulation.police_car_simulation.visuals.Auxiliar.Auxiliar_UI;
import com.jhw.simulation.police_car_simulation.visuals.Configuration.Configuration_UI;
import com.jhw.swing.ui.MaterialLookAndFeel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import sim.display.Console;

/**
 * Main of the application. Where it's created the object containing the
 * simulation, configuration, console and auxiliar classes.
 *
 * @author Jesús Hernández Barrios
 */
public class SimulationMain {

    /**
     * Configuration of the simulation.
     */
    public static Configuration_Class cfg = new Configuration_Class();

    /**
     * User Interface of the simulation, have inside se Simulation State.
     */
    private static PoliceCarsSimulation_UI sim = null;

    /**
     * Console to controlate the simulation.
     */
    private static Console console = null;

    /**
     * Auxiliar UI to change in real time some values of the sim.
     */
    private static Auxiliar_UI aux = null;

    /**
     * Initial UI where the roads, areas and XML are configurated.
     */
    private static Configuration_UI config = null;

    /**
     * Start the simulation
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
//        UIManager.setLookAndFeel(new MaterialLookAndFeel());//javax.swing.plaf.nimbus.NimbusLookAndFeel
        showConfiguration();
    }

    /**
     * Prepare the simulation. Create the UI, the console and the auxiliar.
     */
    public static void prepareSimulation() {
        try {
            sim = new PoliceCarsSimulation_UI();
            createConsole();
            createAuxiliar();
        } catch (Exception ex) {
            Logger.getLogger(PoliceCarsSimulation_Sim.class.getName()).log(Level.SEVERE, null, ex);
            Utility_Class.jopError(ex.getMessage());
            showConfiguration();
        }
    }

    public static void generateRandomEmergency() {
        sim.generateRandomEmergency();
    }

    public static void closeSimulation() {
        console.dispose();
        sim.dispose();
        aux.dispose();
    }

    public static AgentType_Enum getTipeNewInserted() {
        return aux.getTypeNewInserted();
    }

    private static void createConsole() {
        console = new Console(sim);
        console.setSize(cfg.getOtherWidth(), cfg.getOtherHeight());
        console.setLocation(cfg.getFrameWidth(), 0);
        console.setVisible(true);
        console.pressPause();
    }

    private static void createAuxiliar() {
        aux = new Auxiliar_UI();
        aux.setSize(cfg.getOtherWidth(), cfg.getOtherHeight());
        aux.setLocation(cfg.getFrameWidth(), cfg.getOtherHeight());
        aux.setVisible(true);
    }

    public static void reportResponseTime(double delay) {
        sim.reportResponseTime(delay);
    }

    public static void reportDistanceCovered(double dist) {
        sim.reportDistanceCovered(dist);
    }

    public static void adjustScale() {
        sim.adjustScale();
    }

    public static void showConfiguration() {
        config = new Configuration_UI();
    }
}
