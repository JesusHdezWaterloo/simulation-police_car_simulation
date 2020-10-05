package Inner;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Point;

/**
 * Class that contain the name and initial possition of the patrols loaded from
 * the xml solution.
 *
 * @author Margaret Sánchez Martínez
 */
public class XMLInfo_Class {

    /**
     * Name of the patrol.
     */
    private String name;

    /**
     * positrion of the patrol.
     */
    private Point position;

    /**
     * Create the object with the specific values.
     *
     * @param name of the patrol
     * @param x position
     * @param y position
     */
    public XMLInfo_Class(String name, double x, double y) {
        this.name = name;
        this.position = new Point(new Coordinate(x, y), null, 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

}
