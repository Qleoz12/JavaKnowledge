import java.lang.reflect.Field;
import java.util.*;

public class sample {

    private int largoEdicicio;
    private int AnchoEdicicio;
    private List<Pieza> piezasMoldelos;

    public int getLargoEdicicio() {
        return largoEdicicio;
    }

    public void setLargoEdicicio(int largoEdicicio) {
        this.largoEdicicio = largoEdicicio;
    }

    public int getAnchoEdicicio() {
        return AnchoEdicicio;
    }

    public void setAnchoEdicicio(int anchoEdicicio) {
        AnchoEdicicio = anchoEdicicio;
    }

    public List<Pieza> getPiezasMoldelos() {
        return piezasMoldelos;
    }

    public void setPiezasMoldelos(List<Pieza> piezasMoldelos) {
        this.piezasMoldelos = piezasMoldelos;
    }

    //funcion para imprimir los objetos
    public static String dump(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        StringBuilder sb = new StringBuilder();
        sb.append(object.getClass().getSimpleName()).append('{');

        boolean firstRound = true;

        for (Field field : fields) {
            if (!firstRound) {
                sb.append(", ");
            }
            firstRound = false;
            field.setAccessible(true);
            try {
                final Object fieldObj = field.get(object);
                final String value;
                if (null == fieldObj) {
                    value = "null";
                } else {
                    value = fieldObj.toString();
                }
                sb.append(field.getName()).append('=')
                		.append('\'')
                        .append(value).append('\'')
                        .append('\n');
            } catch (IllegalAccessException ignore) {
                //this should never happen
            }

        }

        sb.append('}');
        return sb.toString();
    }
    
    //algoritmo voraz
    public void algoritmoVoraz(sample edificio)
    {	
    	 //lista de piezas aplicadas para el ejercicio del efidicio o tablero
    	 List<Pieza> piezasRespuesta=new ArrayList<Pieza>();
         //area total del edificio o tablero
         double areatototal= edificio.getAnchoEdicicio()*edificio.getLargoEdicicio();
         //se ordena por el mejor criterio de beneficio por tamaño //reversedes para ordenar de mayor a menr
         Collections.sort(edificio.getPiezasMoldelos(), Comparator.comparing(Pieza::getCriteriobeneficio).reversed());
         
         //imprimo resultado de ordenamiento
         for (int i = 0; i < edificio.getPiezasMoldelos().size(); i++) {
             System.out.println(dump(edificio.getPiezasMoldelos().get(i)));
         }
         
         //calculo de valor total
         //edificio.getPiezasMoldelos() fue ordenada previamente en linea  Collections.sort
         for (int i = 0; i <edificio.getPiezasMoldelos().size() ; i++)
         {
         	if (areatototal==0) break;//termino calculo 
         		
         	 //edificio.getPiezasMoldelos().get(i) -> obtengoarea acttual pieza actual
             double areaactual=edificio.getPiezasMoldelos().get(i).getAncho()*edificio.getPiezasMoldelos().get(i).getLargo(); //area de cada pieza
             if (areatototal>=areaactual) //se compara el area de cada pieza y si es menor se resta el valor al total
             {
             	areatototal = areatototal - areaactual;// se resta el valor al total del valor actual de la pieza
                 edificio.getPiezasMoldelos().get(i).setAreaplicada(areaactual);
                 piezasRespuesta.add(edificio.getPiezasMoldelos().get(i));
                 continue;//siguiente pieza iteracion al nuevo elemento
             }
             //porcentaje restante
             if(areatototal<areaactual) 
             {
             	
             	edificio.getPiezasMoldelos().get(i).setAreaplicada(areatototal);
                 piezasRespuesta.add(edificio.getPiezasMoldelos().get(i));
                 areatototal=0;
                 continue;
             }
         }
         
         System.out.println("------------------------------------------------");
         System.out.println(dump(edificio));
         System.out.println("------------------------------------------------");
         for (int i = 0; i < piezasRespuesta.size(); i++) {
         	System.out.println(piezasRespuesta.get(i).toString());
         }
         System.out.println("------------------------------------------------");
         for (int i = 0; i < piezasRespuesta.size(); i++) {
         	System.out.println(piezasRespuesta.get(i).getNumero()+" "+piezasRespuesta.get(i).getAreaplicada());
         }
         System.out.println("------------------------------------------------");
    }
    
    //calcula el valor de criterio  mayor beneficio por menor area
    public double obtenerBeneficio(Pieza pieza)
    {
        return pieza.getBeneficio()*1.0/(pieza.getAncho()*pieza.getLargo());
    }

    
    //la funcion que ejecuta el programa
    public static void main(String[] args) {

        sample edificio=new sample();//creo edificio
        edificio.setPiezasMoldelos(new ArrayList<Pieza>());//inicializar lista donde iran los tipos de piezas
        
        Scanner reader = new Scanner(System.in);  // Reading from System.in //leer texto
        System.out.println("ingrese el ancho edificio: ");
        edificio.setAnchoEdicicio(reader.nextInt()); // Scans the next token of the input as an int.//leer como entero

        System.out.println("ingrese el largo edificio: ");
        edificio.setLargoEdicicio(reader.nextInt()); // Scans the next token of the input as an int.

        System.out.println("ingrese el numero de tipos de piezas: ");
        Integer tipos=reader.nextInt(); // Scans the next token of the input as an int.
        
       
        for (int i = 0; i <tipos ; i++)
        {
        	
            Pieza p=new Pieza();
            p.setNumero(i);
            System.out.println("Pieza "+(i+1)); //numero de pieza apartir de for
            System.out.println("ingrese el ancho: ");
            p.setAncho(reader.nextInt()); 

            System.out.println("ingrese el largo: ");
            p.setLargo(reader.nextInt()); 

            System.out.println("ingrese el beneficio: ");
            p.setBeneficio(reader.nextInt()); 
            p.setCriteriobeneficio(edificio.obtenerBeneficio(p));//calculo criterio de ordenamiento
            edificio.getPiezasMoldelos().add(p); //se agrega a lista de tablero


        }

        //algorithmo Voraz
        //lista de piezas de respuesta
        edificio.algoritmoVoraz(edificio);
        
        
        
        
    }

    

}

class Pieza
{
    private int numero;
    private int largo;
    private int ancho;
    private int beneficio;
    //respuesta
    private double criteriobeneficio;
    private double areaplicada;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getBeneficio() {
        return beneficio;
    }

    public void setBeneficio(int beneficio) {
        this.beneficio = beneficio;
    }

    public double getCriteriobeneficio() {
        return criteriobeneficio;
    }

    public void setCriteriobeneficio(double criteriobeneficio) {
        this.criteriobeneficio = criteriobeneficio;
    }
    
    
    public double getAreaplicada() {
		return areaplicada;
	}

	public void setAreaplicada(double areaplicada) {
		this.areaplicada = areaplicada;
	}

	@Override
    public String toString() {
        return "Pieza{" +
                "numero=" + numero +
                ", largo=" + largo +
                ", ancho=" + ancho +
                ", beneficio=" + beneficio +
                ", areaplicada=" + areaplicada +
                '}';
    }
}