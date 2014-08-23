package com.princeton.algs4.chp2.sorting.other;

import com.princeton.StdDraw;
import com.princeton.StdIn;
import com.princeton.StdRandom;
import com.princeton.Stopwatch;
import com.princeton.algs4.chp2.sorting.code.*;
import com.princeton.algs4.chp2.sorting.code.merge.MergeSortBU;
import com.princeton.algs4.chp2.sorting.code.merge.NaturalMergeSort;
import com.princeton.algs4.chp2.sorting.code.quick.QuickSort;
import com.princeton.algs4.chp2.sorting.code.quick.QuickSort3WayPartition;
import com.princeton.algs4.chp2.sorting.code.quick.QuickSortStack;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 1/19/14
 * Time: 11:39 AM
 */
public class SortCompare {

    public static final String INSERTION = "Insertion";
    public static final String SELECTION = "Selection";
    public static final String INSERTIONX = "InsertionX";
    public static final String INSERTIONXX = "InsertionXX";
    public static final String INSERTIONPRIMITIVES = "InsertionPrimitives";
    public static final String SHELLBASIC = "Shell";
    public static final String SHELLDIFF = "ShellDiff";
    public static final String MERGE = "Merge";
    public static final String MERGEX = "MergeX";
    public static final String MERGEBU = "MergeBU";
    public static final String NATURALMERGE = "NaturalMerge";
    public static final String NMS = "NMS";
    public static final String QUICK = "Quick";
    public static final String QUICKX = "QuickX";
    public static final String QUICKSORT3WAY = "Quick3Way";
    public static final String QUICKSORT3WAYMEDIANOF3 = "Quick3wayMedianOf3";
    public static final String QUICKSORT3WAYMEDIANOF5 = "Quick3wayMedianOf5";
    public static final String QUICKSORTSTACK = "QuickSortStack";
    public static final String QUICKSORT = "QuickSortStack";


    public static double time(String algo, Double[] a) {
        Stopwatch s = new Stopwatch();
        if (INSERTION.equals(algo))
            InsertionSort.sort(a);
        else if (SELECTION.equals(algo))
            SelectionSort.sort(a);
        else if (INSERTIONX.equals(algo))
            InsertionXSort.sort(a);
        else if (INSERTIONXX.equals(algo))
            InsertionXSort.sort(a, true);
        else if (SHELLBASIC.equals(algo))
            ShellSort.sort(a);
        else if (SHELLDIFF.equals(algo))
            ShellSort.sortDifferentIncrementSequence(a);
        else if(MERGE.equals(algo))
            MergeSort.sort(a);
        else if(MERGEX.equals(algo)) {
            MergeSort.optimise = true;
            MergeSort.sort(a);
            MergeSort.optimise = false;
        }
        else if(MERGEBU.equals(algo))
            MergeSortBU.sort(a);
        else if (NATURALMERGE.equals(algo))
            NaturalMergeSort.sort(a);
        else if (NMS.equals(algo))
            com.princeton.algs4.chp2.sorting.code.merge.NMS.sort(a);
        else if (QUICK.equals(algo)){
            QuickSort.sort(a);
        }  else if (QUICKSORT3WAY.equals(algo)){
            QuickSort3WayPartition.sort(a);
        }
        else if (QUICKX.equals(algo)){
            QuickSort.sortX(a);
        }
        else if (QUICKSORT3WAYMEDIANOF3.equals(algo)){
            QuickSort3WayPartition.sortM3(a);
        }
        else if (QUICKSORT3WAYMEDIANOF5.equals(algo)){
            QuickSort3WayPartition.sortM5(a);
        }
        else if (QUICKSORTSTACK.equals(algo)){
            QuickSortStack.sort(a);
        }



        return s.elapsedTime();
    }

    public static double time(Integer[] a) {
        Stopwatch s = new Stopwatch();
        InsertionXSort.sort(a, true);
        return s.elapsedTime();
    }

    public static double time(String algo, int[] a) {
        Stopwatch s = new Stopwatch();
        if (INSERTIONPRIMITIVES.equals(algo))
            InsertionXSort.sortPrimitives(a);
        return s.elapsedTime();
    }

    public static double timeRandomInput(String algo, int N, int T) {
        double total = 0.0;
        Double[] a = new Double[N];
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < a.length; j++) {
                a[j] = StdRandom.uniform(0.0, 1000.0);
            }
            total += time(algo, a);
            assert MergeSort.isSorted(a);
        }
        return total;
    }

    public static double timeRandomInputForIntegers(int N, int T) {
        double total = 0.0;
        Integer[] a = new Integer[N];
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < a.length; j++) {
                a[j] = StdRandom.uniform(0, 1000);
            }
            total += time(a);
        }
        return total;
    }

    public static double timeRandomInputForPrimitive(String algo, int N, int T) {
        double total = 0.0;
        int[] a = new int[N];
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < a.length; j++) {
                a[j] = StdRandom.uniform(0, 1000);
            }
            total += time(algo, a);
        }
        return total;
    }

    public static void plotRunningTimes(String algo) {
        StdDraw.setCanvasSize(1000, 600);
        StdDraw.setXscale(1, 100000);
        StdDraw.setYscale(0, 100);
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(Color.DARK_GRAY);
        double d;
        for (int arraySize = 1; arraySize < 100000; arraySize += 2500) {
            d = timeRandomInput(algo, arraySize, 1);
            StdDraw.line(arraySize, 0, arraySize, d);
        }
    }

    public static void plotDistribution(int N) {
        StdDraw.setCanvasSize(1000, 600);
        StdDraw.setXscale(1, 1000);
        StdDraw.setYscale(0, 0.2);
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(Color.DARK_GRAY);
        int[] a = new int[N];
        createRandomArray(a);
        int i = 0;
        double t,avg;
        double runningTime = 0.0;
        while (true) {
            t = time(INSERTIONPRIMITIVES, a);
            avg =  runningTime / i;
            runningTime += t;
            i++;
            StdDraw.line(i, 0, i, avg);
            System.out.println(t + "avg" + avg);
            createRandomArray(a);
        }
    }

    private static void createRandomArray(int[] a) {
        int ln = a.length;
        for (int i = 0; i < ln; i++) {
            a[i] = StdRandom.uniform(0, 100000);
        }
    }

    private static void createArrayWithOnlyTwoValues(int[] a) {
        int ln = a.length;
        for (int i = 0; i < ln; i++) {
            a[i] = StdRandom.uniform(0, 2);
        }
    }

    private static void createEqualArray(int[] a){
        int ln = a.length;
        for (int i = 0; i < ln; i++) {
            a[i] = 3;
        }
    }

    private static void createReverseArray(int[] a){
        int ln = a.length;
        for (int i = 0; i < ln; i++) {
            a[i] = a.length - i;
        }

    }

    public static void main(String[] args) {

       // plotDistribution(1);
       // plotRunningTimes(INSERTIONXX);
        int N = StdIn.readInt();
        int T = StdIn.readInt();
        double t1 = timeRandomInput(MERGEBU, N, T)/T;
        double t2 = timeRandomInput(MERGEX, N, T)/T;
        double t3 = timeRandomInput(NATURALMERGE, N, T)/T;
        double t4 = timeRandomInput(QUICKSORT3WAY, N, T)/T;
        double t5 = timeRandomInput(QUICK, N, T)/T;
        double t6 = timeRandomInput(QUICKX, N, T)/T;
        double t7 = timeRandomInput(QUICKSORT3WAYMEDIANOF3, N, T)/T;
        double t8 = timeRandomInput(QUICKSORT3WAYMEDIANOF5, N, T)/T;
        double t9 = timeRandomInput(QUICKSORTSTACK, N, T)/T;

        System.out.println(MERGEBU + "\t\t\t\t\t" + t1 + "\n" + MERGEX + "\t\t\t\t\t" + t2 + "\n" + NATURALMERGE + "\t\t\t\t\t" + t3
                + "\n" + QUICKSORT3WAY + "\t\t\t\t\t" + t4 +  "\n" + QUICK + "\t\t\t\t\t" + t5 + "\n" + QUICKX + "\t\t\t\t\t" + t6
                + "\n" + QUICKSORT3WAYMEDIANOF3 + "\t\t\t\t\t" + t7
                + "\n" + QUICKSORT3WAYMEDIANOF5 + "\t\t\t\t\t" + t8
                + "\n" + QUICKSORTSTACK + "\t\t\t\t\t" + t9);
    }
}
