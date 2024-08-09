package com.example;

import java.util.Arrays;
import java.util.List;

public class michaelPage {

    public static void main(String[] args) {

//        String s1 = "Hello, World!";
//
//        String s2 = "world";
//
//        boolean result = StringUtility.containsIgnoreCase(s1, s2);
//
//        System.out.println(result);
//
//
//        int x = 5;
//
//        int y = x++;
//
//        int z = ++x;
//
//        System.out.println(y + z);
//
//        int x1 = 1;
//
//        while (x1 < 10) {
//
//            x1++;
//
//            if (x1 % 2 == 0) {
//
//                continue;
//
//            }
//
//            System.out.println(x1);
//
//        }
//
//
//        int x2 = 10;
//
//        int y2 = 5;
//
//        int z2 = x2 / y2;
//
////        System.out.println(z2);


        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        int sum = list.stream()

                .map(n -> n * 2)

                .reduce(0, (a, b) -> a + b);

        System.out.println(sum);
    }
}

class StringUtility {

    public static boolean containsIgnoreCase(String s1, String s2) {

        if (s1 == null || s2 == null) {

            return false;

        }

        return s1.toLowerCase().contains(s2.toLowerCase());

    }

}
