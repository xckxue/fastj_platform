package com.wow.test.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by wow on 2018/3/22.
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] arr = new int[15];
        int n = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            arr[i] = new Random().nextInt(100);
            n++;
        }

        HeapSort hs = new HeapSort();
        hs.create(n, arr);
        System.out.println(Arrays.toString(arr));
        hs.heapsort(n, arr);
        System.out.println(Arrays.toString(arr));

    }

    //创建堆
    public void create(int length, int[] arr) {
        for (int i = length / 2; i >= 1; i--) {
            siftdown(arr, length, i);
        }
    }


    public void heapsort(int n, int[] arr) {
        while (n > 1) {
            swap(arr, 1, n);
            n--;
            siftdown(arr, n, 1);
        }
    }


    //向下调整堆
    // 节点号
    public void siftdown(int[] arr, int n, int i) {
        int t, flag = 0;
        while (i * 2 <= n && flag == 0) {
            if (arr[i] < arr[2 * i]) {
                t = 2 * i;
            } else {
                t = i;
            }

            if (2 * i + 1 <= n) {
                if (arr[2 * i + 1] > arr[t]) {
                    t = 2 * i + 1;
                }
            }

            if (i != t) {
                swap(arr, i, t);
                i = t;
            } else {
                flag = 1;
            }
        }
    }


    public void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] + arr[j];
        arr[j] = arr[i] - arr[j];
        arr[i] = arr[i] - arr[j];
    }
}
