package com.princeton.algs4.chp2.sorting.code;

import com.princeton.In;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 1/16/14
 * Time: 9:16 PM
 */
public class ShellSort {

    public static void sort(Comparable[] a){

        int h;
        for (h = 1; h < a.length/3; h=3*h+1);

        while (h>0){
            for (int i = h; i < a.length; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]) ; j-=h) {
                  exch(a, j, j-h);
                }
            }

            h/=3;
        }


        assert isSorted(a);
    }

    // array size is less than 7.8 lakh
    public static void sortDifferentIncrementSequence(Comparable[] a){

        int[] h1 = {260609,146305, 64769, 36289, 16001, 8929, 3905, 2161, 929, 505, 209, 109, 41, 19, 5, 1};

        for(int h : h1){
            for (int i = h; i < a.length; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]) ; j-=h) {
                    exch(a, j, j-h);
                }
            }
            h/=3;
        }


        assert isSorted(a);
    }
    public static void exch(Comparable[] arr, int a, int b){
        Comparable temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    public static boolean isSorted(Comparable[] a){
        for (int i = 0; i < a.length - 1; i++) {
            if (!less(a[i], a[i+1])){
                return false;
            }
        }
        return true;
    }


    public static void display(Comparable[] a){
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {

        String fname = "F:\\Ravi\\Work\\mywork\\Algorithms\\Algorithms 4th Edn - Sedgewick and Wayne\\src\\com.princeton.algs4\\chp2\\sorting\\resource\\large.txt";
        String[] strings = In.readStrings(fname);

        assert (ShellSort.isSorted(strings) == false);

        ShellSort.display(strings);

        ShellSort.sort(strings);

        assert ShellSort.isSorted(strings);

        ShellSort.display(strings);

    }

}
