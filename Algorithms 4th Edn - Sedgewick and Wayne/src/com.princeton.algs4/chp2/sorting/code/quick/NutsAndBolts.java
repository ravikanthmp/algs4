package com.princeton.algs4.chp2.sorting.code.quick;

import com.princeton.StdIn;
import com.princeton.StdRandom;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 4/22/14
 * Time: 10:01 PM
 */
public class NutsAndBolts {

    static class Tools implements Comparable<Tools>{

        Integer id;

        @Override
        public int compareTo(Tools o) {
            return id.compareTo(o.id);
        }
    }



    static class Nuts extends Tools{
        public Nuts(Integer id) {
            this.id = id;
        }
    }

    static class Bolts extends Tools{
        public Bolts(Integer id){
            this.id = id;
        }

    }


    private static void exch(Comparable[] arr, int a, int b) {
        Comparable temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    private static int partition(Comparable[] a, Comparable b, int lo, int hi){

        int i = lo-1, j = hi+1, ei = 0;
        while (true){

            while (a[++i].compareTo(b) < 0){
                if (i == hi ) break;
            }

            while (a[--j].compareTo(b) > 0){
                if (j == lo ) break;
            }

            if (i >= j) break;

            exch(a, i, j);
            if (a[i].compareTo(b) == 0)  ei = i;
            if (a[j].compareTo(b) == 0)  ei = j;

        }

        exch(a, ei, i);
        return i;

    }

    private static void sortNutsAndBolts(Tools[] nuts, Tools[] bolts, int lo, int hi, int spaces){

        if (hi <= lo) return;
        else {
            for (int i = 0; i <spaces; i++){
                System.out.print(' ');
            }
            System.out.println("Partitioning Nuts on " + bolts[lo].id + " with lo " + lo + " hi " + hi );
            int p = partition(nuts, bolts[lo], lo, hi);
            System.out.println("Pivot pos = " + p);
            System.out.println("Partitioning Bolts on " + nuts[p].id + " with lo " + lo + " hi " + hi);
            partition(bolts, nuts[p], lo, hi);

            display(nuts, bolts);

            sortNutsAndBolts(nuts, bolts, lo, p-1, spaces + 1);
            sortNutsAndBolts(nuts, bolts, p+1, hi, spaces + 1);
        }
    }

    public static void sort(Nuts[] nuts, Bolts[] bolts){
        sortNutsAndBolts(nuts, bolts, 0, nuts.length-1, 0);
    }

    private static void display(Tools[] nuts, Tools[] bolts){
        System.out.print("Nuts ");
        for (int i = 0; i <nuts.length; i++)
            System.out.print(nuts[i].id + " ");

        System.out.println();

        System.out.print("Bolts ");
        for (int i = 0; i <bolts.length; i++)
            System.out.print(bolts[i].id + " ");

        System.out.println();

    }

    public static void main(String[] args) {

        int N = StdIn.readInt();
        Nuts[] nuts = new Nuts[N];
        Bolts[] bolts = new Bolts[N];

        for (int i = 0; i < N; i++){
            nuts[i] = new Nuts(i);
            bolts[i] = new Bolts(i);
        }

        StdRandom.shuffle(nuts);
        StdRandom.shuffle(bolts);

        display(nuts, bolts);
        System.out.println("Going in!");


        sort(nuts, bolts);
        System.out.println("Coming out!");

        for (int i = 0; i < N; i++)
            System.out.print("Nut size" + nuts[i].id + " Bolt size " + bolts[i].id + '\n');


    }


}
