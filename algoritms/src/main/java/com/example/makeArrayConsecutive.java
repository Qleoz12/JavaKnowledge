package com.example;

import java.util.*;

public class makeArrayConsecutive {
    public static void main(String[] args) {
    int result=  makeArrayConsecutive2(new int[]{6,2,3,8});
        System.out.println(result);
    }

    static public int makeArrayConsecutive2(int[] statues){
        int[] m = new int[2];
        m = minNum(statues);
        int large = m[1] - m[0] + 1;
        return large - statues.length;
    }

    static  public int[] minNum(int[] array) {
        int[] m = new int[2];
        m[0] = array[0];
        m[1] = array[0];
        for(int i= 1; i < array.length; i++) {
            if(m[0] > array[i])
                m[0] = array[i];
            if(m[1] < array[i])
                m[1] = array[i];
        }
        return m;
    }

    List groupDishes(String[][] dishes) {
        Map<String, Set> store = new TreeMap<>();
        List grouped = new ArrayList();
        for(String[] dish: dishes) {
            for(int i = 1; i< dish.length;i++) {
                //Create a map for ingredient at first
                if(!store.containsKey(dish[i])) store.put(dish[i], new TreeSet());

                Set dishSet = store.get(dish[i]);
//                dishSet.add(dish[]);
            }
        }

        for(Map.Entry<String, Set> entrySet: store.entrySet()) {
            System.out.println(entrySet.getKey() + " -> " + entrySet.getValue());
            if(entrySet.getValue().size() > 1) {
                List l = new ArrayList();
                l.add(entrySet.getKey());
                for(Object o: entrySet.getValue()) l.add(o);
                grouped.add(l);
            }
        }
        return grouped;
    }
}
