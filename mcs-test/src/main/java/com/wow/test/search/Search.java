package com.wow.test.search;

/**
 * Created by wow on 2018/3/27.
 */
public class Search {
    public static int search1(int[] arr, int key) {
        int start = 0;
        int end = arr.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (key < arr[mid]) {
                end = mid - 1;
            } else if (key > arr[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int search2(int[] arr, int key, int l, int h) {
        if(l > h) return -1;
        int mid = (l + h) / 2;
        if (key == arr[mid]) {
            return mid;
        } else if (key > arr[mid]) {
            return search2(arr, key, mid + 1, h);
        } else {
            return search2(arr, key, l, mid - 1);
        }
    }


    public static void main(String[] args){
        Search s = new Search();
        int[] array = {2,3,5,7,8,11,24,57};
        int index = s.search2(array, 111,0, array.length - 1);
        System.out.println(index);
    }
}
