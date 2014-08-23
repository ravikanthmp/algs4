package com.princeton.algs4.chp2.sorting.other;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 2/19/14
 * Time: 9:02 PM
 */
public class Inversions {

    private static Comparable[] aux;

    public static int getAns() {
        return ans;
    }

    private static int ans = 0;

    public static void sort(Comparable[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
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

    private static void merge(Comparable[] a, int lo, int mid, int high) {

        int rsh = high - mid, p2 = mid, p3 = high;

        while (p3 >= mid && p2 >= lo) {
            if (a[p2].compareTo(a[p3]) < 0) {
                rsh--;
                p3--;
            } else {
                ans += rsh;
                p2--;
            }
        }


        for (int i = lo; i <= high; i++) {
            aux[i] = a[i];
        }

        int j = lo, k = lo, l = mid + 1;
        while (k <= mid && l <= high) {
            if (aux[k].compareTo(aux[l]) <= 0) {
                a[j] = aux[k];
                j++;
                k++;
            } else {
                a[j] = aux[l];
                j++;
                l++;
            }
        }

        if (k <= mid) {
            while (k <= mid) {
                a[j++] = aux[k++];
            }
        }

        if (l <= high) {
            while (l <= high) {
                a[j++] = aux[l++];
            }
        }
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

    public static void main(String[] args) {
        sort(new Integer[]{2,1,5,7,9,6,4});
        System.out.println("#Inversions " + getAns());
    }

}
