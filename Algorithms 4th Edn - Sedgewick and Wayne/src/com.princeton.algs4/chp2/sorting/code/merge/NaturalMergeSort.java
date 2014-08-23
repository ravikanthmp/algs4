package com.princeton.algs4.chp2.sorting.code.merge;

import com.princeton.StdRandom;
import com.princeton.Stopwatch;
import com.princeton.algs4.chp2.sorting.code.InsertionSort;
import com.princeton.algs4.chp2.sorting.code.MergeSort;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 1/26/14
 * Time: 3:09 PM
 */
public class NaturalMergeSort {

    private static Comparable[] aux;

    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }


        int l = lo, j = lo, k = mid + 1;

        while (j <= mid && k <= hi) {
            if (aux[j].compareTo(aux[k]) <= 0) {
                a[l++] = aux[j++];
            } else {
                a[l++] = aux[k++];
            }
        }

        if (j < mid){
            while (j <= mid){
                a[l++] = aux[j++];
            }
        }else if(k < hi){
            while (k <= hi){
                a[l++] = aux[k++];
            }
        }

    }

    public static void mergeX(Comparable[] a, int lo, int mid, int high) {
        for (int i = lo; i <= mid; i++) {
            aux[i] = a[i];
        }

        int j, k, l;

        for (int i = 0; ; i++) {
            j = mid + 1 +i;
            k = high - i;
            if (j > high) break;
            aux[j] = a[k];
        }

        j = lo;
        k = lo;
        l = high;
        Comparable t1, t2;

        while (k <= l) {
            t1 = aux[k];
            t2 = aux[l];
            if (t1.compareTo(t2) <= 0) {
                a[j++] = t1;
                k++;
            } else {
                a[j++] = t2;
                l--;
            }
        }

    }

    public static void sort(Comparable[] a){
        int i, lo, mid, hi, nMid, N = a.length;

        if(N < 10){
            InsertionSort.sort(a);
            return;
        }

        aux = new Comparable[N];
        do {
            i = 0;  nMid = 0;
            do {
                lo = i; mid = -1; hi = -1;
                while (i < N-1){
                    if (a[i].compareTo(a[i+1]) > 0){
                        mid = i++;
                        nMid++;
                        break;
                    }
                    else {
                        i++;
                    }
                }

                if (i!=N-1){
                    while (i < N-1){
                        if (a[i].compareTo(a[i+1]) > 0){
                            hi = i++;
                            break;
                        }
                        else {
                            i++;
                        }
                    }
                }

                if (mid > -1){
                    mergeX(a, lo, mid, (hi>-1)?hi:N-1);
                }

            }while (i < N-1);
        }while (nMid!=0);
    }

    public static void main(String[] args) {

        String fname = "F:\\Ravi\\Work\\mywork\\Algorithms\\Algorithms 4th Edn - Sedgewick and Wayne\\src\\com.princeton.algs4\\chp2\\sorting\\resource\\words3.txt";

        // String[] strings = In.readStrings(fname);
        int N = 5676776;
        Comparable[] strings = new Comparable[N];

        for (int i = 0; i < strings.length; i++) {
            strings[i] = new Double(StdRandom.uniform(-50000.0, 50000.0));
        }


        System.out.println(strings.length);

        assert (MergeSort.isSorted(strings) == false);

       // MergeSort.display(strings);

        Stopwatch s = new Stopwatch();
        assert !MergeSort.isSorted(strings);

        NaturalMergeSort.sort(strings);
        System.out.println("Elapsed Time : " + s.elapsedTime());
        assert MergeSort.isSorted(strings);

        // MergeSort.display(strings);
        // MergeSort.display(aux);

    }

}
