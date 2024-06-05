package com.example;


import java.util.Arrays;
import java.util.stream.Stream;

public class MostFrequentSum {

//    int[] mostFrequentSum(Tree<Integer> t){
//        Map<Integer,Integer> sums = new HashMap<>();
//        getSums(t,sums);
//
//        int max = 0;
//        List<Integer> maxes = new ArrayList<Integer>();
//        for (Map.Entry<Integer,Integer> entry : sums.entrySet()){
//            System.out.println(entry);
//            if(entry.getValue() > max){
//                maxes.clear();
//                maxes.add(entry.getValue());
//                max = entry.getValue();
//            } else if (entry.getValue() == max){
//                maxes.add(entry.getKey());
//            }
//        }
//
//        int[] rv = new int[maxes.size()];
//        for(int i=0;i < maxes.size(); i++){
//            rv[i] = maxes.get(i);
//        }
//
//        Arrays.sort(rv);
//        return rv;
//    }
//
//    public int getSums(Tree<Integer> t, Map<Integer,Integer> sums){
//        if(t == null){
//            return 0;
//        }
//
//        int left = getSums(t.left, sums);
//        int right = getSums(t.right, sums);
//
//        int sum = left + right + t.value;
//
//        sums.put(sum,sums.getOrDefault(sum,0) + 1);
//        return sum;
//    }
}