import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        while (!salir) {

            System.out.println("1. Opcion 1 Dividir y vencer");
            System.out.println("2. Opcion 2 Programación dinámica");
            System.out.println("3. Opcion 3 Vuelta Atrás");
            System.out.println("4. Salir");

            try {

                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("Has seleccionado la opcion 1");
                        DividiryvencerNumeros.main(null);

                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion 2");
                        System.out.println("aun no implementado");
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion 3");
                        problemadeamochila.main(null);
                        break;
                    case 4:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
    }
}
