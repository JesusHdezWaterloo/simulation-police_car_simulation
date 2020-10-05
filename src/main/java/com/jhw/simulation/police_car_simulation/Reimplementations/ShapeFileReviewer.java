/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jhw.simulation.police_car_simulation.reimplementations;

import com.jhw.simulation.police_car_simulation.main.PoliceCarsSimulation_Sim;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import sim.io.geo.ShapeFileImporter;

/**
 *
 * @author Jesús Hernández Barrios
 */
public class ShapeFileReviewer {

    /*
     The same as in the sim.io.geo.ShapeFileImporter in the geomason package
     */
    public static final int MULTIPATCH = 31;
    public static final int MULTIPOINT = 8;
    public static final int MULTIPOINTM = 28;
    public static final int MULTIPOINTZ = 18;
    public static final int NULL_SHAPE = 0;
    public static final int POINT = 1;
    public static final int POINTM = 21;
    public static final int POINTZ = 11;
    public static final int POLYGON = 5;
    public static final int POLYGONM = 25;
    public static final int POLYGONZ = 15;
    public static final int POLYLINE = 3;
    public static final int POLYLINEM = 23;
    public static final int POLYLINEZ = 13;

    /**
     * Returt the shapre type of the file.
     *
     * @param shpFile
     * @return a int representing the shp type
     */
    public static int getSHPType(URL shpFile) {
        try {
            FileInputStream fileInputStream = new FileInputStream(shpFile.getFile());
            FileChannel channel = fileInputStream.getChannel();
            ByteBuffer byteBuf = channel.map(FileChannel.MapMode.READ_ONLY, 0, (long) ((int) channel.size()));
            channel.close();
            byteBuf.position(100);
            byteBuf.position(byteBuf.position() + MULTIPOINT);
            byteBuf.order(ByteOrder.LITTLE_ENDIAN);
            int recordType = byteBuf.getInt();
            return recordType;
        } catch (Exception ex) {
            Logger.getLogger(PoliceCarsSimulation_Sim.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public static int getSHPType(String path) {
        try {
            return getSHPType(new URL("file", null, path));
        } catch (Exception ex) {
            Logger.getLogger(PoliceCarsSimulation_Sim.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public static boolean isSupported(int shapeType) {
        switch (shapeType) {
            case POINT /*1*/:
            case POLYLINE /*3*/:
            case POLYGON /*5*/:
                return true;
            default:
                return false;
        }
    }

    public static String typeToString(int shapeType) {
        switch (shapeType) {
            case NULL_SHAPE /*0*/:
                return "NULL_SHAPE";
            case POINT /*1*/:
                return "POINT";
            case POLYLINE /*3*/:
                return "POLYLINE";
            case POLYGON /*5*/:
                return "POLYGON";
            case MULTIPOINT /*8*/:
                return "MULTIPOINT";
            case POINTZ /*11*/:
                return "POINTZ";
            case POLYLINEZ /*13*/:
                return "POLYLINEZ";
            case POLYGONZ /*15*/:
                return "POLYGONZ";
            case MULTIPOINTZ /*18*/:
                return "MULTIPOINTZ";
            case POINTM /*21*/:
                return "POINTM";
            case POLYLINEM /*23*/:
                return "POLYLINEM";
            case POLYGONM /*25*/:
                return "POLYGONM";
            case MULTIPOINTM /*28*/:
                return "MULTIPOINTM";
            case MULTIPATCH /*31*/:
                return "MULTIPATCH";
            default:
                return "UNKNOWN";
        }
    }
}
