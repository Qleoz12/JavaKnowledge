package com.example.heuristicAlgoritms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class problemadeamochila {

    int lastvalue=0;
    int[] mejorseleccion=null;
    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        Integer[][] dp = new Integer[profits.length][capacity + 1];
        this.mejorseleccion= new int[profits.length];
        return this.knapsackRecursive(dp, profits, weights, capacity, 0);
    }

    private int knapsackRecursive(Integer[][] dp, int[] profits, int[] weights, int capacity,
                                  int currentIndex) {

        // base checks
        if (capacity <= 0 || currentIndex >= profits.length)
            return 0;

        // if we have already solved a similar problem, return the result from memory
        if(dp[currentIndex][capacity] != null) {
            System.out.println("calculado de memoria");
            System.out.println(dp[currentIndex][capacity]);
            return dp[currentIndex][capacity];
        }
        // recursive call after choosing the element at the currentIndex
        // if the weight of the element at currentIndex exceeds the capacity, we shouldn't process this
        int profit1 = 0;
        if( weights[currentIndex] <= capacity )
            profit1 = profits[currentIndex] + knapsackRecursive(dp, profits, weights, capacity - weights[currentIndex], currentIndex + 1);
        // recursive call after excluding the element at the currentIndex
        int profit2 = knapsackRecursive(dp, profits, weights, capacity, currentIndex + 1);

        if(profit1>profit2)
        {
            dp[currentIndex][capacity]= profit1;
            if(profit1>0 && lastvalue!=profit1){
                lastvalue=profit1;
                mejorseleccion[currentIndex]=currentIndex;
            }

        }
        else
        {
            dp[currentIndex][capacity]=profit2;
            if(profit2>0 && lastvalue!=profit2){
                lastvalue=profit2;
                mejorseleccion[currentIndex]=currentIndex;
            }
        }


//        System.out.println(capacity);
//        System.out.println(currentIndex+" "+(currentIndex + 1));
//        System.out.println(Arrays.toString(weights));
//        System.out.println(Arrays.toString(profits));
//
//        System.out.println(profit1+" "+profit2);
//
//        System.out.println("------------------------");
//        for (int i = 0; i < dp.length; i++) {
//            for (int j = 0; j < dp[i].length; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println("------------------------");
        return dp[currentIndex][capacity];
    }

    public static void main(String[] args) {
        problemadeamochila ks = new problemadeamochila();
//        int[] profits = {1, 6, 5, 16,30};
//        int[] weights = {1, 2, 3, 5,3};

        int[] profits = null;
        int[] weights = null;
        Scanner sn = new Scanner(System.in);
        System.out.println("digite la cantidad de elementos");
        int largo  = sn.nextInt();
        profits = new int[largo];
        weights = new int[largo];
        for (int i = 0; i < largo; i++) {
            System.out.println("digite el beneficio");
            profits[i] = sn.nextInt();

            System.out.println("digite el peso");
            weights[i]  = sn.nextInt();
        }

        System.out.println("digite el limite de la mochila");
        int limitedepeso  = sn.nextInt();

        int maxProfit = ks.solveKnapsack(profits, weights, limitedepeso);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        System.out.println("beneficios: "+Arrays.toString(profits));
        System.out.println("pesos:      "+Arrays.toString(weights));
        System.out.println("            "+Arrays.toString(ks.mejorseleccion));
//        maxProfit = ks.solveKnapsack(profits, weights, 6);
//        System.out.println("Total knapsack profit ---> " + maxProfit);
    }
}

