package com.example;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CantorExpansion {

    public static void main(String[] args) {
        String answer="";
        int n=1;
        for (int term : new CantorExpansion().decimalToCantor(87)) {
            answer+=n+"! * "+term+"  ";
            n++;
        }
        String[] res= answer.split("  ");
        Arrays.sort(res, Collections.reverseOrder());
        System.out.println((Arrays.toString(res)));
    }

    /**
     * @return the terms of the cantor expansion (a_i, a_(i-1), ...., a_1)
     */
    public List<Integer> decimalToCantor(int num) {
        List<Integer> terms = new ArrayList<>();
        int n = 0;
        while (num != 0) {
            System.out.println("1 "+ n);
            terms.add(num % (n + 2));
            System.out.println("2 "+num % (n + 2));
            System.out.println("3 "+(num - terms.get(terms.size() - 1)) / (n + 2));
            num = (num - terms.get(terms.size() - 1)) / (n + 2);
            n++;
        }
        System.out.println(terms.toString());
        return terms;
    }

}


