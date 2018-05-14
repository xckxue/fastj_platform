package com.wow.test.sort;

import java.util.Arrays;

/**
 * Created by wow on 2018/3/9.
 */
public class Sort {
    public static void main(String[] args){
        char c = 'a' + 'b';
        System.out.println(c);
//        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
//        quickSort(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));
    }

    public static int jieCeng(int n){
        if(n<=1){

            return 1;
        }else{

            return  jieCeng(n-1)+n;
        }
    }


    //快速排序
    public static void quickSort(int[] arr, int low, int high) {
        if (low > high) {
            return;
        }
        int i = low;
        int j = high;
        int key = arr[low];
        while (i < j) {
            while (arr[j] >= key && i < j) {
                j--;
            }
            while (arr[i] <= key && i < j) {
                i++;
            }
            if (i < j) {
                swap(arr, i, j);
            }
        }

        arr[low] = arr[i];
        arr[i] = key;

        quickSort(arr, low, j - 1);
        quickSort(arr, j + 1, high);
    }

    //插入
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            while (j > 0 && arr[j] < arr[j - 1]) {
                swap(arr, j, j - 1);
                j--;
            }
        }
    }

    //选择
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(arr, min, i);
            }
        }
    }

    //冒泡排序
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    flag = false;
                }
            }
            if (flag) {
                return;
            }
        }
    }

    public static void swap(int[] arr, int a, int b) {
        arr[a] = arr[a] + arr[b];
        arr[b] = arr[a] - arr[b];
        arr[a] = arr[a] - arr[b];
    }
}
