/*
 Que es lo que tiene el XML SOLUCION!!!!!
 -Periodos[intalaciones_activas[instalacion(nombre)]]
 -Instalaciones[instalacion(lugar y coordenada)]
 -Puntos de demandas
 */
package com.jhw.simulation.police_car_simulation.utils;

import com.jhw.simulation.police_car_simulation.inner.XMLInfo_Class;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 *
 * @author Margaret Sánchez Martínez
 */
public class XMLoader_Class {

    private final Document document;

    public XMLoader_Class(URL url) throws JDOMException, IOException {
        //parseador de xml a java
        SAXBuilder builder = new SAXBuilder();
        document = builder.build(url);
    }

    public LinkedList<XMLInfo_Class> read() {
        LinkedList<XMLInfo_Class> inf = new LinkedList<>();

        //obtenemos la raiz "DMCLP_DTF_LocF_Sol"
        Element root = document.getRootElement();
        //instalaciones abiertas en los 4 periodos
        LinkedList<String> Instalaciones_Abiertas = new LinkedList<>();
        //Se obtiene la lista de hijos de la raiz 
        List hijosRaiz = root.getChildren("Periodos");
        //Se recorre la lista de hijos de 'DMCLP_DTF_LocF_Sol'
        for (int i = 0; i < hijosRaiz.size(); i++) {

            //Se obtiene el 1er hijo 'periodos'
            Element periodos = (Element) hijosRaiz.get(i);

            //Se obtiene el atributo 'cantidad' que esta en 'periodos'
            String nombreTabla = periodos.getAttributeValue("cantidad");
            //System.out.println("Periodos: " + nombreTabla);

            //Se obtiene la lista de hijos del tabla 'periodos'
            List lista_periodo = periodos.getChildren();
            for (int j = 0; j < lista_periodo.size(); j++) {
                //Se obtiene el elemento 'periodo_1..2..3..4'
                Element periodo = (Element) lista_periodo.get(j);
                //adiciona a I_A los nombres de las I de un periodod dado
                Instalaciones_Abiertas.addAll(funcion(periodo));
            }

            //--------------------------------------------------------------------------------
            //se hace el mismo proceso que para periodo
            List Instalaciones_Raiz = root.getChildren("Instalaciones");
            for (int j = 0; j < Instalaciones_Raiz.size(); j++) {
                Element instalacion = (Element) Instalaciones_Raiz.get(j);

                //Se obtiene el atributo 'cantidad' que esta en el tabla 'intalaciones'
                String nameTabla = instalacion.getAttributeValue("cantidad");
                //System.out.println("InstalacionesDisponibles: " + nameTabla);

                List listaInsts = instalacion.getChildren("Instalacion");
                for (int k = 0; k < listaInsts.size(); k++) {
                    Element instal = (Element) listaInsts.get(k);
                    Element lugar = instal.getChild("Lugar");
                    String lugarString = lugar.getText();

                    //se obtienen las coordenadas x y y 
                    Element coordenadas = instal.getChild("Coordenadas");

                    String coordenadasX = coordenadas.getChild("X").getText();
                    String coordenadasY = coordenadas.getChild("Y").getText();

                    double x = Double.parseDouble(coordenadasX);
                    double y = Double.parseDouble(coordenadasY);
                    //----------------------------------------------------------------------------           
                    //Anade a la lista las coordenadas x & y, lugar z
                    if (Instalaciones_Abiertas.contains(lugarString)) {
                        inf.add(new XMLInfo_Class(lugarString, x, y));
                    }
                    //ver las coordenadas
                    //System.out.println(x + " " + y);
                }
            }
        }
        
        return inf;
    }

    // Devuelve lista de los nombre de las instalaciones activas de un periodo ¨resultado¨
    private LinkedList<String> funcion(Element periodo) {
        //creo la lista
        LinkedList<String> resultado = new LinkedList<>();
        //periodo dame tu hijo "Instalaciones_Activas"
        Element Instalaciones_Activas = periodo.getChild("Instalaciones_Activas");
        //ME MUESTRA LA CANTIDAD SE INSTALACIONES ACTIVAS
        //System.out.println("******Instalaciones_Activas_Cantidad : " + Instalaciones_Activas.getAttribute("cantidad").getValue());

        //adiciona el nombre de cada instalacion abierta
        List lista_inst = Instalaciones_Activas.getChildren();
        for (int ii = 0; ii < lista_inst.size(); ii++) {
            Element insta = (Element) lista_inst.get(ii);
            //ver los nombre de I.Activas
            //  System.out.println( insta.getAttribute("Nombre").getValue());
            resultado.add(insta.getAttribute("Nombre").getValue());
        }
        return resultado;
    }

    //me devuelves x y y como coordenadas de mason
    private class Point {

        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

    }

}
