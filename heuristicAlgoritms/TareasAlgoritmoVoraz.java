import java.util.*;
import java.util.stream.Collectors;

/**
 * Se tiene un conjunto de N tareas, cada una de las cuales tarda un tiempo predefinido Ti para realizarse. Se tienen a
 * su vez dos procesadores que trabajan simultáneamente, donde se van a ejecutar dichas tareas.
 * Considerando que las tareas se ejecutan  secuencialmente, y que su tiempo de ejecución no se puede fraccionar;
 * diseñar un algoritmo voraz que determine el orden de ejecución de las tareas, de manera que se minimice el tiempo
 * medio de finalización de todas ellas.
 * Entradas: Número de tarea
 */

public class TareasAlgoritmoVoraz {

    static List<Tareas> listadetareas= new ArrayList<Tareas>();

    static List procesador1= new ArrayList<Tareas>();
    static int tiempo1=0;
    static List procesador2= new ArrayList<Tareas>();
    static int tiempo2=0;
    static int numerodetareas;
    static int mitadlista;
    public static void main(String[] args)
    {
        Scanner reader = new Scanner(System.in);  // Reading from System.in //leer texto
        System.out.println("ingrese el numero de tareas: ");
        numerodetareas=reader.nextInt();
        //llenado de la lista global de tareas
        for (int i = 0; i <numerodetareas ; i++)
        {
            Tareas tareaactual= new Tareas();//inicia de nuevo
            System.out.println("ingrese el nombre: ");
            tareaactual.setNombre(reader.next());
            System.out.println("ingrese el tiempo: ");
            tareaactual.setTiempo(reader.nextInt());
            listadetareas.add(tareaactual);
        }

        //System.out.println(listadetareas.toString());
        
        algoritmoVoraz(listadetareas);


    }
    
    public static void algoritmoVoraz(List<Tareas> listadetareas)
    {
    	//criterio de ordenamiento es el tiempo para segmentar en
        // dos listas que representa cada procesador
        Collections.sort(listadetareas, Comparator.comparing(Tareas::getTiempo));
        System.out.println(listadetareas.toString());
        mitadlista=listadetareas.size()/2;

        //se divide cargas de cada procesador
        for (int i = 0; i < listadetareas.size(); i++)
        {
            if(i%2==0)
            {
                procesador1.add(listadetareas.get(i));
                tiempo1=tiempo1+listadetareas.get(i).getTiempo();
            }
            else
            {
                procesador2.add(listadetareas.get(i));
                tiempo2=tiempo2+listadetareas.get(i).getTiempo();
            }

        }

        System.out.println("procesador 1 ="+procesador1.toString());
        System.out.println("tiempo estimado "+tiempo1);
        System.out.println("procesador 2 ="+procesador2.toString());
        System.out.println("tiempo estimado "+tiempo2);
    }
}

class Tareas
{
    private String nombre;
    private int tiempo;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    @Override
    public String toString() {
        return "Tareas{" +
                "nombre='" + nombre + '\'' +
                ", tiempo=" + tiempo +
                '}';
    }
}
