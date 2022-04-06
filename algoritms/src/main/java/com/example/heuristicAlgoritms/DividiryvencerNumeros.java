package com.example.heuristicAlgoritms;

import java.util.ArrayList;
import java.util.Scanner;

public class DividiryvencerNumeros {

        static void print(ArrayList<ArrayList<Integer>> arr)
        {
            for (int i = 0; i < arr.size(); i++) {
                System.out.println(arr.get(i).toString());
            }
        }


        static void print_all_sum_rec(
                int target,
                int current_sum,
                int start,
                ArrayList<ArrayList<Integer>> output,
                ArrayList<Integer> result)
        {
//            System.out.println(target+" "+current_sum+" "+start+" "+output.toString()+" "+result.toString());
            if (target == current_sum) {
                output.add((ArrayList)result.clone());
            }
            //se divide el problema en multiples llamados
            // internos para calcular cada suma por cada indice que se suma
            for (int i = start; i < target; ++i)
            {
                int temp_sum = current_sum + i;
                if (temp_sum <= target)
                {

                    result.add(i);
                    //llamado interno para calculo de cada indice
                    print_all_sum_rec(target, temp_sum, i, output, result);
                    //tras obtener respuesta del indice
                    // se remueve el ultimo valor para nuevo llamado con indice posterior
                    result.remove(result.size() - 1);
                } else {
                    return;
                }
            }
        }

        //llamada de la funcion general
        static ArrayList<ArrayList<Integer>> print_all_sum(int target) {
            ArrayList<ArrayList<Integer>> output = new ArrayList<ArrayList<Integer>>();
            ArrayList<Integer> result = new ArrayList<Integer>();
            print_all_sum_rec(target, 0, 1, output, result);
            return output;
        }



        //funcion main
        public static void main(String[] args) {

            Scanner sn = new Scanner(System.in);
            System.out.println("digite un valor a procesar");
            int n  = sn.nextInt();
            ArrayList<ArrayList<Integer>> result = print_all_sum(n);
            print (result);
        }
}

