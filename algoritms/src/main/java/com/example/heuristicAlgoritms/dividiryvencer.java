package com.example.heuristicAlgoritms;

import java.util.Scanner;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.logging.Logger;

public class dividiryvencer {

    /**
     * Entradas del programa: Ecuación Polinómica e Intervalo
     * Salida del programa: Raíz de la ecuación
     * jdk 1.8
     */

    static Logger logger = Logger.getLogger(dividiryvencer.class.getName());

//    public static void main(String[] args) {
//
//        //se crea funcion lambda que aplica logica matematica
//        Function<Double, Double> Ecuacion = (x1) -> Math.pow(x1, 3)-(8*Math.pow(x1, 2))+(3*x1)+2;
//        //Function<Double, Double> Ecuacion = (x1) -> Math.pow(x1, 3)-(8*Math.pow(x1, 2))+(3*x1)+2;
//
//        Scanner reader = new Scanner(System.in);  // Reading from System.in //leer texto
//        //captura de datos
//        logger.info("ingrese el numero inicial el rango: ");
//        Double inicial=reader.nextDouble();
//        //captura de datos
//        logger.info("ingrese el numero final del rango  el rango: ");
//        Double _final=reader.nextDouble();
//        //impresion de respuestas ed validación de calculo
////        logger.info(new logica().dividiryvencerLogica(inicial,_final,Ecuacion)); //este es el rango correcto
////        logger.info(new logica().dividiryvencerLogica(0.0,10.0,Ecuacion)); //este es el rango correcto
////
////        logger.info(new logica().dividiryvencerLogica(1.0,5.0,Ecuacion));
////        logger.info(new logica().dividiryvencerLogica(20.0,30.0,Ecuacion));
////        logger.info(new logica().dividiryvencerLogica(1.0,10.0,Ecuacion)); //este es el rango correcto
////        logger.info(new logica().dividiryvencerLogica(-10.0,-1.0,Ecuacion)); //este es el rango correcto
//
//    }



}

class logica
{
    Logger logger = Logger.getLogger(dividiryvencer.class.getName());
    public Double dividiryvencerLogica( Double a, Double b, Function<Double, Double> func)
    {

        //debe de haber uno positivo y otro negativo
        Double redondeo=100.0;
        //calcular logica matematica para cada ino de los  limites del rango
        Double resultado1=Math.round(func.apply(a)*redondeo)/redondeo;
        Double resultado2=Math.round(func.apply(b)*redondeo)/redondeo;

        //valido que el resultado obtenido sea igual a cero para terminar el calculo
        if(resultado1==0) return a;
        if(resultado2==0) return b;
        //se calcula la mitad de el  rango  actual
        Double medio=(a+b)*1.0/2;
        //calculo sobre el valor medio de el rango
        Double resultado3=Math.round(func.apply(medio)*redondeo)/redondeo;
        //validar que almenos uno sea negativo
        if (resultado3*resultado1<0) return dividiryvencerLogica(a,medio,func);
        if (resultado3*resultado2<0) return dividiryvencerLogica(b,medio,func);

        //lineas para analizar el comportamiento del programa
        //System.out.println(a);
        //System.out.println(b);
        //System.out.println(medio);
        //validar si se logra la mitad esperada
        if(resultado3==0.0)
            return Math.round(medio*10000.0)/10000.0 ;
        // no se cumple ninguna sentencia que se adecue al calculo se entrega respuesta de fallo
        logger.info("el rango no tiene raiz en el limite cero");
        return 0.0;
    }

}

