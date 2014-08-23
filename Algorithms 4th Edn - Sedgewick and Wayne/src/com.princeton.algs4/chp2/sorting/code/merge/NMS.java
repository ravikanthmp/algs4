package com.princeton.algs4.chp2.sorting.code.merge;

import com.princeton.StdRandom;
import com.princeton.algs4.chp2.sorting.code.InsertionSort;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 2/15/14
 * Time: 9:16 PM
 */
public class NMS {

    private static Comparable[] aux;

    private static void merge(Comparable[] a, int lo, int mid, int hi) {

        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }

        int j = lo, k = lo, l = mid + 1;

        while (k <= mid && l <= hi) {
            if (aux[k].compareTo(aux[l]) <= 0) {
                a[j++] = aux[k++];
            } else {
                a[j++] = aux[l++];
            }
        }


        if ( k <= mid) {
            while (k <= mid) {
                a[j++] = aux[k++];
            }
        } else {
            while (l <= hi) {
                a[j++] = aux[l++];
            }
        }

    }

    public static void sort(Comparable[] a) {

        if(a.length < 10){
            InsertionSort.sort(a);
            return;
        }
        aux = new Comparable[a.length];
        int lo, mid, hi, i, nMid, N = a.length - 1;
        do {
            i = 0;
            nMid = 0;
            do {
                lo = i; mid = -1; hi = -1;
                while (i <= N - 1) {
                    if (a[i].compareTo(a[i + 1]) > 0) {
                        mid = i++;
                        hi = i;
                        nMid++;
                        break;
                    } else {
                        i++;
                    }
                }

                if (i!=N){
                    while (i<=N-1){
                        if (a[i].compareTo(a[i+1]) > 0){
                            hi = i++;
                            break;
                        }else {
                            hi=i;
                            i++;
                        }
                    }
                }
                if (mid!=-1){
                    NaturalMergeSort.mergeX(a, lo, mid, hi);
                }
            }while (i<=N-1);
        }while (nMid>0);
    }


    private static boolean isSorted(Comparable[] a){
        int N = a.length - 1;
        for (int i = 0; i <= N-1; i++){
            if (a[i].compareTo(a[i+1]) > 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args){
        int N = 40000000;
        Double[] d = new Double[N];
        for (int i = 0; i < N; i++) {
            d[i] = new Double(StdRandom.uniform(-100.0, 100.0));
        }
        sort(d);
        assert isSorted(d);
    }
}
