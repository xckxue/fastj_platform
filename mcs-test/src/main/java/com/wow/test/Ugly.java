package com.wow.test;

import java.util.Arrays;

public class Ugly {
    public static void main(String[] args) {
        Ugly s= new Ugly();

        System.out.println(s.GetUglyNumber_Solution(10));
    }

    public int GetUglyNumber_Solution(int index) {
        if(index <= 0){
            return 0;
        }
        int uglyFound = 0;
        int number = 0;
        while(uglyFound < index){
            number++;
            if(isUgly(number)){
                uglyFound++;
            }
            
        }
        return number;
    }
    
    public boolean isUgly(int i){
        if(i == 1) return true;
        int tmp = i;
        while(tmp % 2 == 0){
            tmp = tmp/2;
        }

        while(tmp % 3 == 0){
            tmp = tmp/3;
        }
        while(tmp % 5 == 0){
            tmp = tmp/5;
        }
        
        if(tmp == 1){
            return true;
        }
        return false;
    }
}