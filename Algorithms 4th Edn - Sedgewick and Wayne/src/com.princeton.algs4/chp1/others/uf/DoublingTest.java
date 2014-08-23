package com.princeton.algs4.chp1.others.uf;

import com.princeton.StdIn;
import com.princeton.StdRandom;
import com.princeton.StdStats;
import com.princeton.Stopwatch;
import com.princeton.algs4.chp1.code.uf.QuickFind;
import com.princeton.algs4.chp1.code.uf.QuickUnion;
import com.princeton.algs4.chp1.code.uf.UnionFind;
import com.princeton.algs4.chp1.code.uf.WeightedQuickUnion;

/**
 * Created by Ravi on 12/22/13.
 */
public class DoublingTest {

    public static int count(UnionFind uf, int N){

        int c = 0;
        while (uf.count()!=1){
            int i = StdRandom.uniform(0, N);
            int j = StdRandom.uniform(0, N);
            if (!uf.connected(i,j)){
                uf.union(i,j);
            }
            c++;
        }

        return c;
    }


    public static void main(String[] args) {
        int N = StdIn.readInt();
        int savedN = N;
        double initTime = 1.0;

        int[] results = new int[10];
        double[] time = new double[10];

        System.out.println("----------  QuickUnion ---------------------");
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {

                Stopwatch s = new Stopwatch();
                results[i] = DoublingTest.count(new QuickUnion(N), N);
                time[i] = s.elapsedTime();
            }

            double meantime = StdStats.mean(time);
            System.out.println("N= " + N + " Avg. connections processed: " + StdStats.mean(results) +
                                " Avg time" + meantime +
                                " Ratio " +meantime/initTime);

            initTime = meantime;
            N =  N*2;
        }

        N = savedN;
        initTime = 1.0;

        System.out.println("----------  QuickFind ---------------------");
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {

                Stopwatch s = new Stopwatch();
                results[i] = DoublingTest.count(new QuickFind(N), N);
                time[i] = s.elapsedTime();
            }

            double meantime = StdStats.mean(time);
            System.out.println("N= " + N + "Avg. connections processed: " + StdStats.mean(results) +
                    " Avg time" + meantime +
                    " Ratio " +meantime/initTime);

            initTime = meantime;
            N =  N*2;
        }

        N = savedN;
        initTime = 1.0;

        System.out.println("----------  WeightedQuickUnion ---------------------");
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {

                Stopwatch s = new Stopwatch();
                results[i] = DoublingTest.count(new WeightedQuickUnion(N), N);
                time[i] = s.elapsedTime();
            }

            double meantime = StdStats.mean(time);
            System.out.println("N= " + N + "Avg. connections processed: " + StdStats.mean(results) +
                    " Avg time" + meantime +
                    " Ratio " +meantime/initTime);

            initTime = meantime;
            N =  N*2;
        }

    }

}
