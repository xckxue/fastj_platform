package com.wow.test;

import java.util.List;

/**
 * Created by wow on 2018/4/19.
 */
public class Permutation2 {

    public static void main(String args[]) throws Exception {
        String[] str = {"a", "b", "c"};
        permutation(str, 0, 2);//输出str[0..2]的所有排列方式
    }

    public static void permutation(String[] str,int first,int end){
        if(first == end){
            for(int i = 0 ; i <= end; i++){
                System.out.print(str[i]);
            }
            System.out.println();
        }

        for(int i = first; i <= end; i++){
            swap(str,i,first);
            permutation(str, first + 1, end);
            swap(str,i,first);
        }
    }

    private static void swap(String[] str, int i, int first) {
        String tmp;
        tmp = str[first];
        str[first] = str[i];
        str[i] = tmp;
    }
}
