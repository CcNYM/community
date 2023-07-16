package com.nowcoder.community;



import java.util.Arrays;
import java.util.Date;

public class Test1 {




    public static void sort(int left, int right, int[] array) {
        if (left >= right) return;
        int mid = partition(left , right, array);
        sort(left, mid-1, array);
        sort(mid+1, right, array);
    }


    public static  int partition(int left,int right, int[] array){
        int base = array[left];
        while(left < right) {
            while (right>left && array[right] < base) right--;
            array[left] = array[right];
            while(right>left && array[left] > base) left++;
            array[right] = array[left];

        }
        array[left] = base;
        return left;
    }

    public static void main(String[] args) {
        int[] test = new int[]{1,5,8,9,6,7,11,2,13,4};
        sort(0,test.length-1,test);
        for(int i:test) {
            System.out.println(i);
        }
    }
}
