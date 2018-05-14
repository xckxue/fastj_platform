package com.wow.test.sort;

/**
 * Created by wow on 2018/3/26.
 */
public class MergeSort {

    public static void main(String[] args) {
        MergeSort ms = new MergeSort();
        int[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 10, 8};
        ms.sort(arr,0,arr.length-1);
    }

    public int[] sort(int[] arr, int left, int right) {
        if(left ==right){
            return new int[] {arr[left]};
        }
        int mid = (left + right)/2;
        int[] l = sort(arr,left,mid);
        int[] r = sort(arr,mid+1,right);
        return merge(l,r);
    }

    public int[] merge(int[] l, int[] r) {
        int[] tmp = new int[l.length + r.length];
        int p = 0;
        int lp = 0;
        int rp = 0;
        while (lp < l.length && rp < l.length) {
            tmp[p++] = l[lp] < r[rp] ? l[lp++] : r[rp++];
        }
        while (lp < l.length) {
            tmp[p++] = l[lp++];
        }
        while (rp < r.length) {
            tmp[p++] = r[rp++];
        }
        return tmp;
    }
}
