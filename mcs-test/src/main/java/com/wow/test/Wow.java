package com.wow.test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by wow on 2018/3/19.
 */
public class Wow {

    private static class Holder {
        private static final Wow wow = new Wow();
    }

    public static Wow getWow() {
        return Holder.wow;
    }


    private static volatile Wow wow = null;

    public static Wow getInstance() {
        if (wow == null) {
            synchronized (Wow.class) {
                if (wow == null) {
                    wow = new Wow();
                }
            }
        }
        return wow;
    }


    public static void main(String[] args) throws InterruptedException {
        String iniString = "abcdef";
        iniString.notifyAll();


        System.out.println(Fibonacci(4));


        char[] chars = iniString.toCharArray();
        reverseArry(chars, 0, iniString.length() - 1);
        System.out.println(chars);

        System.out.println(ReverseSentence("I am a student."));

        FindNumbersWithSum(new int[]{1, 2, 3, 4, 5}, 6);

        duplicate(new int[]{2, 4, 3, 1, 4}, 5, new int[]{});

        System.out.println(findMaxLengthHuiWen("cabad"));

        findMoreThanHalfNum(new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2});

        System.out.println(Permutation("abc"));

    }

    public static int Fibonacci(int n) {
        /*
        if(n <= 0) return 0;
        if(n == 1 || n ==2 )return 1;
        return Fibonacci(n-1) + Fibonacci(n-2);
        */
        int preprenum = 0;
        int prenum = 1;
        int result = 0;
        if(n == 0) return 0;
        if (n == 1 || n ==2) return 1;
        for(int i = 2; i <=n ;i++){
            result = preprenum+prenum;
            preprenum = prenum;
            prenum = result;
        }
        return result;
    }


    public static ArrayList<String> Permutation(String str) {
        ArrayList<String> list = new ArrayList<String>();
        if (str == null || str.length() == 0) {
            return list;
        }
        char[] strArr = str.toCharArray();
        permu(list, strArr, 0);
        Collections.sort(list);
        return list;
    }

    public static void permu(ArrayList<String> list, char[] strArr, int start) {
        if (start == strArr.length - 1) {
            String result = String.valueOf(strArr);
            if (list.indexOf(result) < 0) {
                list.add(String.valueOf(strArr));
            }
        }
        for (int i = start; i < strArr.length; i++) {
            char temp = strArr[i];
            strArr[i] = strArr[start];
            strArr[start] = temp;
            permu(list, strArr, start + 1);
            temp = strArr[i];
            strArr[i] = strArr[start];
            strArr[start] = temp;
        }
    }


    public static int findMoreThanHalfNum(int[] numbers) {
        int length = numbers.length;
        if (length == 0) return 0;

        int num = numbers[0], count = 1;
        for (int i = 1; i < length; i++) {
            if (numbers[i] == num) count++;
            else count--;
            if (count == 0) {
                num = numbers[i];
                count = 1;
            }
        }
        // Verifying
        count = 0;
        for (int i = 0; i < length; i++) {
            if (numbers[i] == num) count++;
        }
        if (count * 2 > length) return num;
        return 0;
    }

    public static int MoreThanHalfNum_Solution(int[] array) {
        if (array == null) return 0;

        int[] hash = new int[array.length];
        for (int i : array) {
            hash[i]++;
        }

        int flag = 0;
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] > array.length / 2) {
                flag = i;
            }
        }
        return flag;
    }


    public static String findMaxLengthHuiWen(String inistring) {
        if (inistring == null || inistring.length() == 0) return "";
        for (int i = 0; i < inistring.length(); i++) {
            for (int j = inistring.length(); j > i; j--) {
                String tmp = inistring.substring(i, j);
                if (isHuiWen(tmp) && tmp.length() > 1) {
                    return tmp;
                }
            }
        }
        return "";
    }

    public static boolean isHuiWen(String initString) {
        int i = 0;
        int j = initString.length() - 1;
        while (i < j) {
            if (initString.charAt(i) != initString.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static boolean duplicate(int numbers[], int length, int[] duplication) {
        if (numbers == null) return false;
        if (numbers.length != length) return false;
        for (int i : numbers) {
            if (i < 0 || i > numbers.length - 1) {
                return false;
            }
        }
        int[] hash = new int[length];
        for (int i : numbers) {
            hash[i]++;
        }

        for (int i : hash) {
            if (hash[i] > 1) {
                duplication[0] = i;
                return true;
            }
        }
        return false;
    }

    public static void threadPool() {
        ExecutorService es = Executors.newFixedThreadPool(10);
        List<byte[]> list = new ArrayList<>();
        while (true) {
            es.execute(new Runnable() {
                @Override
                public void run() {
                    list.add(new byte[5 * 1024 * 1024]);
                    System.out.println(Thread.currentThread().getName());

                }
            });

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {

        ArrayList<Integer> list = new ArrayList<>();

        int tmp = 0;
        for (int i = 0; i < array.length; i++) {
            int j = i;
            for (j = i + 1; j < array.length; j++) {
                if (array[i] + array[j] == sum) {
                    if (tmp == 0 || tmp > (array[i] * array[j])) {
                        tmp = array[i] * array[j];
                        list.add(array[i]);
                        list.add(array[j]);
                    }
                }
            }
        }
        return list;
    }


    public static String ReverseSentence(String str) {
        if (str.trim().equals("")) {
            return str;
        }
        String[] a = str.split(" ");
        StringBuffer o = new StringBuffer();
        int i;
        for (i = a.length; i > 0; i--) {
            o.append(a[i - 1]);
            if (i > 1) {
                o.append(" ");
            }
        }
        return o.toString();
    }


    public static void reverseArry(char[] arry, int start, int end) {
        while (start < end) {
            char tmp = arry[start];
            arry[start++] = arry[end];
            arry[end--] = tmp;
        }
    }

}
