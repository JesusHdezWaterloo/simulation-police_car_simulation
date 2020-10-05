package com.jhw.simulation.police_car_simulation.utils;

import com.jhw.simulation.police_car_simulation.inner.AreaColor_Class;
import com.vividsolutions.jts.geom.Envelope;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Jesús Hernández Barrios
 */
public class Configuration_Class {

    private int frameWidth = 650;
    private int frameHeight = 650;

    private int displayWidth = 650;
    private int displayHeight = 650;

    private int otherWidth = 500;
    private int otherHeight = 500;

    private String urlXMLData = "xmldata";
    private String urlXMLSolution = "solucionDMCLPDTFLOCF.xml";
    private String urlGIS = "geodata";
    private String urlRoads = "calles_centro_habana";
    private LinkedList<AreaColor_Class> urlsAreas = new LinkedList<>();

    private double patrolsPointsScale = 10;

    private int maxDepth = 5;

    //private final String[] colorsAvailables = new String[]{"ROJO", "VERDE", "AZUL", "NEGRO", "GRIS", "ROSADO", "AMARILLO"};
    private final HashMap<String, Color> colorsAvailables = new HashMap();
    private final Color defaultColor = Color.LIGHT_GRAY;

    public Configuration_Class() {
        createColors();
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public int getDisplayWidth() {
        return displayWidth;
    }

    public int getOtherWidth() {
        return otherWidth;
    }

    public int getOtherHeight() {
        return otherHeight;
    }

    public String getUrlGIS() {
        return urlGIS;
    }

    public String getUrlRoads() {
        return urlRoads;
    }

    public String getUrlXMLData() {
        return urlXMLData;
    }

    public String getUrlXMLSolution() {
        return urlXMLSolution;
    }

    public LinkedList<AreaColor_Class> getUrlsAreas() {
        return urlsAreas;
    }

    public double getPatrolsPointsScale() {
        return patrolsPointsScale;
    }

    public int getDisplayHeight() {
        return displayHeight;
    }

    public double getEmergenciesPointsScale() {
        return 2 * patrolsPointsScale;
    }

    public double getJunctionsPointsScale() {
        return patrolsPointsScale / 3;
    }

    public Color getColor(String color) {
        return colorsAvailables.get(color);
    }

    public Object[] getColorsAvailables() {
        return colorsAvailables.keySet().toArray();
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public Color getDefaultColor() {
        return defaultColor;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    public void setDisplayWidth(int displayWidth) {
        this.displayWidth = displayWidth;
    }

    public void setUrlXMLData(String urlXMLData) {
        this.urlXMLData = urlXMLData;
    }

    public void setUrlXMLSolution(String urlXMLSolution) {
        this.urlXMLSolution = urlXMLSolution;
    }

    public void setDisplayHeight(int displayHeight) {
        this.displayHeight = displayHeight;
    }

    public void setOtherWidth(int otherWidth) {
        this.otherWidth = otherWidth;
    }

    public void setOtherHeight(int otherHeight) {
        this.otherHeight = otherHeight;
    }

    public void setUrlGIS(String urlGIS) {
        this.urlGIS = urlGIS;
    }

    public void setUrlRoads(String urlRoads) {
        this.urlRoads = urlRoads;
    }

    public void setUrlsAreas(LinkedList<AreaColor_Class> urlsAreas) {
        this.urlsAreas = urlsAreas;
    }

    public void setPatrolsPointsScale(double patrolsPointsScale) {
        this.patrolsPointsScale = patrolsPointsScale;
    }

    public void adjustScreenSize(Envelope MBR) {//width = ancho         height = alto
        //dimensions of the current screen
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int tirdWidth = screen.width / 3;

        //frame size
        frameHeight = screen.height - 50;
        frameWidth = 2 * tirdWidth;

        //adjust display
        double dispHeight = frameHeight - 100;
        double propH = MBR.getHeight() / dispHeight;

        double dispWidth = frameWidth - 100;
        double propW = MBR.getWidth() / dispWidth;
        double display[] = {propH, propW};
        Arrays.sort(display);
        displayHeight = (int) (MBR.getHeight() / display[1]);
        displayWidth = (int) (MBR.getWidth() / display[1]);

        //console and auxiliar size
        otherHeight = frameHeight / 2;
        otherWidth = tirdWidth;
    }

    public void adjustAllScales(Envelope MBR) {
        double sq = Math.sqrt(MBR.getArea());
        patrolsPointsScale = 2 * sq / Math.pow(10, 3);
        patrolsPointsScale = Math.ceil(Math.log(patrolsPointsScale) / Math.log(2));
    }

    private void createColors() {
        colorsAvailables.put("ROJO", Color.RED);
        colorsAvailables.put("VERDE", Color.GREEN);
        colorsAvailables.put("AZUL", Color.BLUE);
        colorsAvailables.put("NEGRO", Color.BLACK);
        colorsAvailables.put("GRIS", Color.GRAY);
        colorsAvailables.put("ROSADO", Color.PINK);
        colorsAvailables.put("AMARILLO", Color.YELLOW);
        colorsAvailables.put("GRIS", Color.GRAY);
        colorsAvailables.put("NARANJA", Color.ORANGE);
        colorsAvailables.put("MORADO", Color.MAGENTA);
        colorsAvailables.put("BLANCO", Color.WHITE);
    }

}
