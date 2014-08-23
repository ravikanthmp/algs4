package com.princeton.algs4.chp2.sorting.code.quick;

import com.princeton.StdIn;
import com.princeton.StdRandom;
import com.princeton.algs4.chp2.sorting.code.InsertionSort;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 4/20/14
 * Time: 6:31 PM
 */
public class QuickSort3WayPartition {

    private static final int CUTOFF = 9;

    public static void sort(Comparable[] a){
        StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    public static void sortM3(Comparable[] a){
        StdRandom.shuffle(a);
        int max = 0;
        for (int i = 1; i < a.length; i++){
            if (a[i].compareTo(a[max]) > 0)
                max = i;
        }

        exch(a, max, a.length - 1);

        sortM3(a, 0, a.length - 1);
        assert isSorted(a);
    }

    public static void sortM5(Comparable[] a){
        StdRandom.shuffle(a);
        int max = 0;
        for (int i = 1; i < a.length; i++){
            if (a[i].compareTo(a[max]) > 0)
                max = i;
        }

        exch(a, max, a.length - 1);

        sortM5(a, 0, a.length - 1);
        assert isSorted(a);
    }

    public static void exch(Comparable[] arr, int a, int b) {
        Comparable temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) <= 0;
    }

    public static boolean le(Comparable a, Comparable b) {
        return a.compareTo(b) <= 0;
    }
    public static boolean isSorted(Comparable[] a) {
        for (int i = 0; i < a.length - 1; i++) {
            if (!le(a[i], a[i + 1])) {
                return false;
            }
        }

        return true;
    }


    private static void medianOf5(Comparable[] a, int lo, int hi){
        int i1, i2, i3, i4, i5;
        int middle = lo + (hi - lo)/2;

        int l = StdRandom.uniform(lo, hi+1);
        int m = StdRandom.uniform(lo, hi+1);
        int h = StdRandom.uniform(lo, hi+1);

        int med = median(a, l , m ,h);

        int lm = StdRandom.uniform(lo, hi+1);
        int mh = StdRandom.uniform(lo, hi+1);
        lm = Math.min(lm, mh);
        mh = Math.max(lm, mh);

        if (lm < l){
            if (mh < l){
                i1 = lm; i2 = mh; i3 = l; i4 = m; i5 = h;
            }
            if (mh < m){
                i1 = lm; i2 = l; i3 = lm; i4 = m; i5 = h;
            }
            if (mh < h){
                i1 = lm; i2 = l; i3 = m; i4 = mh; i5 = h;
            }else {
                i1 = lm; i2 = l; i3 = m; i4 = h; i5 = mh;
            }
        }else if (lm < m){
            if (mh < h){
                i1 = l; i2 = lm; i3 = m; i4 = mh; i5 = h;
            }
            else {
                i1 = l; i2 = lm; i3 = m; i4 = h; i5 = mh;
            }

        } else if (lm < h){
            if (mh < h){
                i1 = l; i2 = m; i3 = lm; i4 = mh; i5 = h;
            }
            else {
                i1 = l; i2 = m; i3 = lm; i4 = h; i5 = mh;
            }

        } else {
            i1 = l; i2 = m; i3 = h; i4 = lm; i5 = mh;
        }

        exch(a, lo, i3);
        exch(a, lo + 1, i1);
        exch(a, hi, i5);
        exch(a, (middle - lo)/2, i2);
        exch(a, (hi - middle)/2, i4);
    }


    private static int median(Comparable[] a, int l, int m, int h){
        Comparable li = a[l], mi = a[m], hi = a[h];
        if (less(li, mi)){
            if (less(mi, hi))
                return m;
            if (less(hi, li))
                return l;
            return h;
        }else {
            if (less(li, hi))
                return l;
            if (less(hi, mi))
                return m;
            return h;
        }
    }

    private static void sort(Comparable[] a, int lo, int hi) {

        if (hi <= lo)return;
        int lt = lo;
        int i = lo+1;
        int gt = hi;
        Comparable v = a[lo];
        while (i <= gt){
            int comp = a[i].compareTo(v);

            if (comp < 0){
                exch(a, i++, lt++);
            }
            else if (comp == 0){
                i++;
            }
            else {
                exch(a, i, gt--);
            }
        }

        sort(a, lo, lt - 1);
        sort(a, gt +1, hi);

    }

    private static void sortM3(Comparable[] a, int lo, int hi) {

        if (hi <= lo)return;
        if (hi <= lo + CUTOFF){
            InsertionSort.sort(a, lo, hi);
            return;
        }
        int lt = lo;
        int i = lo+1;
        int gt = hi;

        int median = median(a, lo, lo + (hi - lo)/2, hi);
        exch(a, lo, median);

        Comparable v = a[lo];

        while (i <= gt){
            int comp = a[i].compareTo(v);

            if (comp < 0){
                exch(a, i++, lt++);
            }
            else if (comp == 0){
                i++;
            }
            else {
                exch(a, i, gt--);
            }
        }

        sort(a, lo, lt - 1);
        sort(a, gt +1, hi);

    }

    private static void sortM5(Comparable[] a, int lo, int hi) {

        if (hi <= lo)return;
        if (hi <= lo + CUTOFF){
            InsertionSort.sort(a, lo, hi);
            return;
        }
        int lt = lo;
        int i = lo+1;
        int gt = hi;

        medianOf5(a, lo, hi);
        Comparable v = a[lo];

        while (i <= gt){
            int comp = a[i].compareTo(v);

            if (comp < 0){
                exch(a, i++, lt++);
            }
            else if (comp == 0){
                i++;
            }
            else {
                exch(a, i, gt--);
            }
        }

        sort(a, lo, lt - 1);
        sort(a, gt +1, hi);

    }

    public static void display(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] myArr = new Integer[StdIn.readInt()];
        for (int i = 0; i < myArr.length; i++) {
            myArr[i] = StdRandom.uniform(-10000, 10000);
        }
        display(myArr);
        sortM5(myArr);
        assert isSorted(myArr);
        display(myArr);

    }


}
