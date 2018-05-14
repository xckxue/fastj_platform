package com.wow.test;

/**
 * Created by wow on 2018/3/27.
 */
public class Permutation {
    public static void permutation(String[] str, int first, int end) {
        //输出str[first..end]的所有排列方式
        if (first == end) {    //输出一个排列方式
            for (int j = 0; j <= end; j++) {
                System.out.print(str[j]);
            }
            System.out.println();
        }

        for (int i = first; i <= end; i++) {
            swap(str, i, first);
            permutation(str, first + 1, end);  //固定好当前一位，继续排列后面的
            swap(str, i, first);
        }
    }

    private static void swap(String[] str, int i, int first) {
        String tmp;
        tmp = str[first];
        str[first] = str[i];
        str[i] = tmp;
    }

    public static void main(String args[]) throws Exception {
        String[] str = {"a", "b", "c"};
        permutation(str, 0, 2);//输出str[0..2]的所有排列方式
    }
}
