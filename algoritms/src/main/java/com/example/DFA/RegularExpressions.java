package com.example.DFA;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegularExpressions {


        public boolean esUnFecha(String fechaleida) {
            // Define the regular expression pattern for a date in the format "dd/MM/yyyy"
            Pattern pattern = Pattern.compile("^\\d{2}/\\d{2}/\\d{4}$");

            // Use the pattern to create a Matcher object
            Matcher matcher = pattern.matcher(fechaleida);

            // Return true if the input string matches the pattern, false otherwise
            return matcher.matches();
        }

        public boolean esUnNombre(String nombre) {
            // Define the regular expression pattern for a name
            Pattern pattern = Pattern.compile("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");

            // Use the pattern to create a Matcher object
            Matcher matcher = pattern.matcher(nombre);

            // Return true if the input string matches the pattern, false otherwise
            return matcher.matches();
        }

        public boolean esUnTelefono(String telefono) {
            // Define the regular expression pattern for a phone number
            Pattern pattern = Pattern.compile("^(\\+\\d{1,3}[- ]?)?\\d{10}$");

            // Use the pattern to create a Matcher object
            Matcher matcher = pattern.matcher(telefono);

            // Return true if the input string matches the pattern, false otherwise
            return matcher.matches();
        }


    public static void main(String[] args) {
        RegularExpressions exp = new RegularExpressions();

        // Example usage for fecha
        String fecha1 = "01/01/2022";
        String fecha2 = "31-12-2022";
        System.out.println(fecha1 + " es una fecha válida: " + exp.esUnFecha(fecha1));
        System.out.println(fecha2 + " es una fecha válida: " + exp.esUnFecha(fecha2));

        // Example usage for nombre
        String nombre1 = "Juan Pérez";
        String nombre2 = "Ana María Gómez";
        String nombre3 = "Pedro - García";
        String nombre4 = "Marta O'Connell";
        System.out.println(nombre1 + " es un nombre válido: " + exp.esUnNombre(nombre1));
        System.out.println(nombre2 + " es un nombre válido: " + exp.esUnNombre(nombre2));
        System.out.println(nombre3 + " es un nombre válido: " + exp.esUnNombre(nombre3));
        System.out.println(nombre4 + " es un nombre válido: " + exp.esUnNombre(nombre4));

        // Example usage for telefono
        String telefono1 = "1234567890";
        String telefono2 = "+1 123-456-7890";
        String telefono3 = "+593 9876543210";
        System.out.println(telefono1 + " es un número de teléfono válido: " + exp.esUnTelefono(telefono1));
        System.out.println(telefono2 + " es un número de teléfono válido: " + exp.esUnTelefono(telefono2));
        System.out.println(telefono3 + " es un número de teléfono válido: " + exp.esUnTelefono(telefono3));
    }
}
