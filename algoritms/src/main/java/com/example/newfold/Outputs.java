package com.example.newfold;

public class Outputs {
    public static void main(String[] args) {

        char c = 'A';

        StringBuilder builder = new StringBuilder("Initial");

        System.out.println(c + " " + builder); // A Initial

        update(c, builder);

        System.out.println(c + " " + builder); // A Initial - Updated

    }

    private static void update(char c, StringBuilder builder) {

        c = 'B';

        builder.append(" - Updated");

        System.out.println(c + " " + builder); // B Initial - Updated

    }

}


