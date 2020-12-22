package com.weteam.java.design.entity.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OptimizeScanner {

    public static int getInt() {
        Boolean boo = true;
        int number = 0;
        while (boo) {
            Scanner input = new Scanner(System.in);
            try {
                number = input.nextInt();
                boo = false;
            } catch (InputMismatchException e) {
                System.out.println("请重新输入正确的数字");
            }
        }
        return number;
    }

    public static double getDouble() {
        Boolean boo = true;
        double number = 0;
        while (boo) {
            Scanner input = new Scanner(System.in);
            try {
                number = input.nextDouble();
                boo = false;
            } catch (InputMismatchException e) {
                System.out.println("请重新输入正确的数字");
            }
        }
        return number;
    }

    public static String getString() {
        Boolean boo = true;
        String str = "";
        while (boo) {
            Scanner input = new Scanner(System.in);
            str = input.next();
            if(str.toCharArray().length > 13){
                System.out.println("输入字符串过长, 请输入13个字符以内的字符串!");
            }else{
                boo = false;
            }
        }
        return str;
    }

}
