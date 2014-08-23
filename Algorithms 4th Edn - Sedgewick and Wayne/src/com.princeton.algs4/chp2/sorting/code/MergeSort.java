package com.princeton.algs4.chp2.sorting.code;

import com.princeton.StdRandom;
import com.princeton.Stopwatch;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 1/25/14
 * Time: 12:02 AM
 */
public class MergeSort {

    public static boolean optimise = false;
    public static boolean superoptimise = false;

    private static Comparable[] aux;

    public static void sort(Comparable[] a) {

        aux = new Comparable[a.length];
        if (optimise) {sortX(a, 0, a.length - 1);}
        else if(superoptimise){sortXX(a, aux, 0, a.length - 1);}
        else {sort(a, 0, a.length - 1);}
        assert isSorted(a);
    }

    private static void sort(Comparable[] a, int lo, int hi) {

        if (hi <= lo) {
            return;
        }

        int mid = lo + (hi - lo) / 2;

        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int lo, int mid, int high) {
        for (int i = lo; i <= high; i++) {
            aux[i] = a[i];
        }

        int j = lo, k =lo, l = mid + 1;
        while (k<=mid && l<=high) {
            if (aux[k].compareTo(aux[l]) <=0 ){a[j] = aux[k]; j++; k++;}
            else {a[j] = aux[l]; j++; l++;}
        }

        if (k <= mid){
            while (k<=mid) {a[j++] = aux[k++];}
        }

        if (l<=high){
            while (l<=high){a[j++] = aux[l++];}
        }
    }

    private static void sortX(Comparable[] a, int lo, int hi) {

        if (hi <= lo) {
            return;
        }

        if (hi - lo + 1 < 2) {
            // InsertionXSort.sort(a);
            return;
        }

        if (hi - lo + 1 == 2){
            if (a[lo].compareTo(a[hi]) <= 0)
                return;
            else {
               exch(a,lo,hi);
            }
        }

        if (hi - lo + 1 < 8) {
            InsertionSort.sort(a, lo, hi);
            return;
        }

        int mid = lo + (hi - lo) / 2;


        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        if (a[mid].compareTo(a[mid + 1]) <= 0) {
            return;
        }
        mergeX(a, lo, mid, hi);
    }

    private static void mergeX(Comparable[] a, int lo, int mid, int high) {
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

    private static void sortXX(Comparable[] src, Comparable[] dst, int lo, int hi){
        int ln = hi - lo + 1;
        if (ln == 1) return;
        else if(ln == 2){
            if (src[lo].compareTo(src[hi]) <= 0 ) {return;}
            else {exch(src, lo, hi); return;}
        }

        int mid = lo + (hi - lo)/2;
        sortXX(dst, src, lo, mid);
        sortXX(dst, src, mid+1, hi);
        mergeXX(dst, src, lo, mid, hi);


    }

    private static void mergeXX(Comparable[] src, Comparable[] dst, int lo, int mid, int hi){
        int j = lo, k = mid+1, l = lo;
        while (j<=mid && k<=hi){
            if (src[j].compareTo(src[k])<=0) {dst[l++] = src[j++];}
            else {dst[k++] = src[j++];}
        }
        if (j<mid){
            while (j <= mid){
                dst[l++] = src[j++];
            }
        }

        if (k < hi){
            while (k <= hi){
                dst[k++] = src[j++];
            }
        }
    }

    public static void exch(Comparable[] arr, int a, int b) {
        Comparable temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) <= 0;
    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (!less(a[i], a[i + 1])) {
                return false;
            }
        }

        return true;
    }

    public static void display(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {

        String fname = "F:\\Ravi\\Work\\mywork\\Algorithms\\Algorithms 4th Edn - Sedgewick and Wayne\\src\\com.princeton.algs4\\chp2\\sorting\\resource\\words3.txt";

        // String[] strings = In.readStrings(fname);
        int N = 32521;
        Comparable[] strings = new Comparable[N];

        for (int i = 0; i < strings.length; i++) {
            strings[i] = new Double(StdRandom.uniform(-50000.0, 50000.0));
        }


        System.out.println(strings.length);

        assert (MergeSort.isSorted(strings) == false);

        MergeSort.display(strings);

        Stopwatch s = new Stopwatch();
        MergeSort.optimise = true;
        MergeSort.sort(strings);
        System.out.println("Elapsed Time : " + s.elapsedTime());
        assert MergeSort.isSorted(strings);

        MergeSort.display(strings);
       // MergeSort.display(aux);

    }
}
