package com.princeton.algs4.chp2.sorting.code;

import com.princeton.StdDraw;
import com.princeton.StdRandom;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 1/16/14
 * Time: 8:47 PM
 */
public class SelectionSort {

    public static int N;
    public static Double max;
    public static boolean shouldIDraw = false;

    public static void sort(Comparable[] a) {


        if (shouldIDraw) {
            initDisplay();
            drawArray(a);
        }

        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (less(a[j], a[min])) {
                    min = j;
                }
            }

            if (i != min) {
                exch(a, min, i);
            }

            if (shouldIDraw) {
                StdDraw.clear();
                drawArray(a);
            }
        }

        assert isSorted(a);
    }

    public static void initDisplay() {
        StdDraw.setCanvasSize(1000, 600);
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, max);
        StdDraw.setPenColor(Color.DARK_GRAY);
        StdDraw.setPenRadius(0.006);
    }

    public static void drawArray(Comparable[] a) {
        drawArray(a, 0, a.length - 1);
    }

    public static void drawArray(Comparable[] a, int startIndex, int stopIndex) {
        for (int i = startIndex; i <= stopIndex; i++) {
            StdDraw.line(i,0,i, (Double) a[i]);
        }
    }

    public static void exch(Comparable[] arr, int a, int b) {
        Comparable temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
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

        String fname = "F:\\Ravi\\Work\\mywork\\Algorithms\\Algorithms 4th Edn - Sedgewick and Wayne\\src\\com.princeton.algs4\\chp2\\sorting\\resource\\tiny.txt";
        // String[] tbs = In.readStrings(fname);
        max = 200.0;
        N = 30;
        shouldIDraw = true;

        Double[] tbs = new Double[N];
        for (int i = 0; i < tbs.length; i++) {
            tbs[i] = StdRandom.uniform(0.0, max);
        }

        assert (SelectionSort.isSorted(tbs) == false);

        SelectionSort.display(tbs);

        SelectionSort.sort(tbs);

        assert SelectionSort.isSorted(tbs);

        SelectionSort.display(tbs);

    }


}
