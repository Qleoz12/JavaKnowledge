package com.example;

public class AdjacentElementsProduct {
    public static void main(String[] args) {
        System.out.println(adjacentElementsProduct(new int[]{ 3,6,-2,-5,7,3}));

        System.out.println(checkPalindrome("hanah"));
    }

    static int adjacentElementsProduct(int[] inputArray){
        int cont = 0;
        int product = 0;
        int largest = Integer.MIN_VALUE;
        while(cont < inputArray.length -1) {
            product = inputArray[cont]*inputArray[cont+1];
            cont++;
            if(product > largest)
                largest = product;
        }
        return largest;
    }
    static boolean checkPalindrome(String inputString) {

        int i = inputString.length()-1;
        int j = 0;
        while(i!=j && j<i)
        {
            if(inputString.charAt(j++) != inputString.charAt(i--))
                return true;


        }
        return false;

    }
}
