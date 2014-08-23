package com.princeton.algs4.chp2.sorting.code.quick;

import com.princeton.StdIn;
import com.princeton.StdRandom;

import java.util.Arrays;

/**
 * Created by mpravika on 6/4/2014.
 */
public class QuickSortBentleyMcIllroy {

    private static final Integer CUT_OFF = 10;

    public static <T> void display(T[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void sort(Comparable[] a){
        StdRandom.shuffle(a);
        display(a);
        sort(a, 0, a.length-1);
        display(a);
    }

    private static void exchange(Comparable[] a, int i, int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean less(Comparable[] a, int i, int j){
        return a[i].compareTo(a[j]) < 0;
    }

    private static boolean eq(Comparable[] a, int i, int j){
        return a[i].compareTo(a[j]) == 0;
    }

    private static void sort(Comparable[] a, int lo, int hi){
        if (hi <= lo) {return;}
        else if (hi <= CUT_OFF) {
            Arrays.sort(a, lo, hi+1);
        }else {
            int le = lo+1, re = hi, i = lo, j = hi+1;
            while (true){
                while (less(a, ++i, lo)){
                    if (i == hi) {break;}
                }

                while (less(a, lo, --j)){
                    if (j == lo) {break;}
                }

                if (i == j) {
                    if (eq(a, i, lo)) {
                        exchange(a, i, le++);
                    }
                }

                if (i >= j) {
                    break;
                }


                exchange(a, i, j);
                if (eq(a, i, lo)) {
                    exchange(a, i, le++);
                }
                if (eq(a, j, lo)) {
                    exchange(a, j, re--);
                }

            }

            int r = j+1;
            for (int k = lo; k < le; k++) {
               exchange(a, k, j--);
            }

            for (int k = hi; k > re; k--){
               exchange(a, k, r++);
            }


            sort(a, lo, j);
            sort(a, r, hi);
        }
    }

    public static void main(String[] args) {

        Integer[] ints = new Integer[StdIn.readInt()];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = new Integer(StdRandom.uniform(-100, 100));
        }

        sort(ints);

    }

}