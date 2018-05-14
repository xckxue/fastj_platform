package com.wow.test;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};

        //reOrderArray(arr);
        System.out.println(Arrays.toString(arr));
    }

    /*
    public static void reOrderArray(int[] array) {
        if (array == null || array.length == 0) return;
        for (int i = 1; i < array.length; i++) {
           int target = array[i];
            if (array[i] % 2 == 1) {
                int j = i;
                while (j > 0 && array[j - 1] % 2 == 0) {
                    array[j] = array[j - 1];
                    j--;
                }
                array[j] = target;
            }
        }
    }
    */
}