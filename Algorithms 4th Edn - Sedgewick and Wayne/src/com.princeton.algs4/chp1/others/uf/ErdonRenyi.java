package com.princeton.algs4.chp1.others.uf;

import com.princeton.StdIn;
import com.princeton.StdRandom;
import com.princeton.StdStats;
import com.princeton.Stopwatch;
import com.princeton.algs4.chp1.code.uf.UnionFind;
import com.princeton.algs4.chp1.code.uf.WeightedQuickUnion;
import com.princeton.algs4.chp1.code.uf.WeightedQuickUnionWithPathCompression;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ravi on 12/22/13.
 */
public class ErdonRenyi {

    public static List<Connection> cx;

    static class Connection{
        private int x,y;

        Connection(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

    public ErdonRenyi() {

    }

    public static List<Connection> generateConnections(int N){
        cx = new ArrayList<Connection>();
        WeightedQuickUnion wqu = new WeightedQuickUnion(N);
        while (wqu.count()!=1){
            int i = StdRandom.uniform(0, N);
            int j = StdRandom.uniform(0, N);
            if (!wqu.connected(i, j)){
                wqu.union(i, j);
            }

            cx.add(new Connection(i,j));
        }
        return cx;
    }


    public static double performExperiment(boolean type,int N, List<Connection> cx){

        Stopwatch s = new Stopwatch();

        UnionFind uf;
        if (type == false){
            uf = new WeightedQuickUnion(N);
        }else {
            uf = new WeightedQuickUnionWithPathCompression(N);
        }

        for (Connection c: cx){
            int i = c.getX();
            int j = c.getY();
            uf.union(i, j);;
        }

        return s.elapsedTime();
    }


    public static void main(String[] args) {
        int T = StdIn.readInt();
        int N = 10000;

        double[] times = new double[T];
        double times1, times2;

        for (int i = 0; i < 10; i++) {

            List<Connection> cx = ErdonRenyi.generateConnections(N);

            for (int j = 0; j < T; j++) {
                times[j] = ErdonRenyi.performExperiment(false, N, cx);
            }

            times1 = StdStats.mean(times);

            for (int j = 0; j < T; j++) {
                times[j] = ErdonRenyi.performExperiment(false, N, cx);
            }

            times2 = StdStats.mean(times);

            System.out.println("N = " + N + " ||  Time for WQU = " + times1 + " || Time for WQUPC = " + times2 +
                                " || Ratio = " + times2/times1);

            N+=N;


        }
    }

}
