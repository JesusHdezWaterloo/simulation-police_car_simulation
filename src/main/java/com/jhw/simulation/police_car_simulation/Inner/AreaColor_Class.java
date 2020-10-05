package Inner;

import PoliceCarsSimulation.SimulationMain;
import java.awt.Color;

/**
 * Class that contain an specific area and it's respective portrayal color.
 *
 * @author Margaret Sánchez Martínez
 */
public class AreaColor_Class {

    /**
     * String representing the area fisical location
     */
    private String area = "";

    /**
     * Colro of the area.
     */
    private String color = "";

    /**
     * Create the object with the specific values.
     *
     * @param area of the .shp.
     * @param color of the area.
     */
    public AreaColor_Class(String area, String color) {
        this.area = area;
        this.color = color;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Return the Color.color asociated with the string 'color'.
     *
     * @return the String asociated color.
     */
    public Color getRealColor() {
        if (color != null) {
            return SimulationMain.cfg.getColor(color);
        } else {
            return SimulationMain.cfg.getDefaultColor();
        }
    }

}
