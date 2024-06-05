package com.example;

import java.util.ArrayList;
import java.util.Arrays;

public class StringPermutations {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(stringPermutations("CBA")));
    }
    static String[] stringPermutations(String s) {
        char[] c = s.toCharArray();
        int[] ct = new int[26];
        for(int i=0; i < c.length; i++) {
            ct[c[i]-'A']++;
        }
        ArrayList<char[]> al = solve(new char[c.length],0,ct);
        String[] st = new String[al.size()];
        for(int i=0; i<al.size();i++) {
            st[i] = new String(al.get(i));
        }
        return st;

    }

    static ArrayList<char[]> solve(char[] curr, int ind, int[] ct){
        ArrayList<char[]> out = new ArrayList<char[]>();
        if(ind == curr.length){
            out.add(curr.clone());
            return out;
        }
        for(int i=0; i<ct.length; i++) {
            if(ct[i] > 0) {
                ct[i]--;
                curr [ind] = (char)(i+'A');
                ArrayList<char[]> al = solve(curr, ind+1,ct);
                out.addAll(al);
                ct[i]++;
            }
        }
        return out;
    }
}
