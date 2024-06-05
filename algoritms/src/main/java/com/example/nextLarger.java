package com.example;

import java.util.ArrayList;
import java.util.List;

public class nextLarger {
        public static void main(String args[]) {
            int x=10;
            int y=25;
            int z=x+y;

            System.out.println("Sum of x+y = " + z);
            int[] a=new int[]{ 6,7,3,8 };
            System.out.println(""+nextLarger(a));
        }

        static int[] nextLarger(int[] a)
        {
            int sum[] = new int[a.length];
            List<Integer> list = new ArrayList<Integer>();
            sum[a.length -1] = -1;
            list.add(a[a.length-1]);
            for(int i=a.length-2;i>=0;i--)
            {
                boolean hasGreater = false;
                for(int j=list.size()-1;j>=0;j--)
                {
                    if(list.get(j)>a[i])
                    {
                        hasGreater = true;
                        sum[i] = list.get(j);
                        list.add(a[i]);
                        break;
                    }
                }
                if(!hasGreater)
                {
                    sum[i] = -1;
                    list.add(a[i]);
                }
            }
            return sum;
        }

}
