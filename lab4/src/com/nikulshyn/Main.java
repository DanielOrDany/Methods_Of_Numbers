package com.nikulshyn;

import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
    public static double f (double x) {
        return Math.pow( ((1 + x)/(1 - x)), 0.5);
    }

    public static void main (String[] args)
    {
        Scanner in = new Scanner(System.in);
        in.useLocale(Locale.US);
        double a = in.nextDouble();
        double b = in.nextDouble();
        double result = 0;
        for (int N=2; N<=4; N*=2){
            double h, sum2 = 0, sum4 = 0, sum = 0;
            h = (b - a)/(2*N);
            for (int i = 1; i <= 2*N - 1; i +=2){
                sum4 +=f(a + h * i);
                sum2 +=f(a + h * (i+1));
            }
            sum = f(a) + 4 * sum4 + 2 * sum2 - f(b);
            result = (h/3) * sum;
        }

        double check = Math.pow((1 - b*b), 0.5) - Math.asin(b) - Math.pow((1-a*a), 0.5) + Math.asin(a);
        System.out.println(check);
        System.out.println(result);
    }
}