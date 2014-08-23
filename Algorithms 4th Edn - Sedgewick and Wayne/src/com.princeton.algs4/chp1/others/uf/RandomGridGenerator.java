package com.princeton.algs4.chp1.others.uf;

import com.princeton.StdDraw;
import com.princeton.StdIn;
import com.princeton.StdStats;
import com.princeton.Stopwatch;
import com.princeton.algs4.chp1.code.stackQueueBag.bag.RandomBag;
import com.princeton.algs4.chp1.code.uf.*;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 12/14/13
 * Time: 3:32 PM
 */
public class RandomGridGenerator {

    private int N;

    public RandomGridGenerator(int n) {
        N = n;
    }

    static class Point{
        int p,q;

        Point(int p, int q) {
            this.p = p;
            this.q = q;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "p=" + p +
                    ", q=" + q +
                    '}';
        }
    }

    static class Connection{
        Point p,q;

        Connection(Point p, Point q) {
            this.p = p;
            this.q = q;
        }

        @Override
        public String toString() {
            return "Connection{" +
                    "p=" + p +
                    ", q=" + q +
                    '}';
        }
    }

    public static RandomBag<Connection> generate(int N){
        RandomBag<Connection> ans = new RandomBag<Connection>();
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                boolean left = false, right = false, top = false, bottom = false;
                if ((row < N-1 && row > 1) && (col > 1 && col < N-1)){
                    right = true; left = true; top = true; bottom = true;
                }
                else if(row == 0 || row == N-1){
                    if (row == 0)
                        top = true;
                    else
                        bottom = true;
                    right = true;
                    if (col == N-1)
                        right = false;
                }
                 if (col == 0 || col == N-1){
                    if(row!= N-1) top = true;
                    if (col == 0)
                        right = true;
                    else
                        left = true;
                }

                Point p1 = new Point(row, col);
                if (left){
                    ans.add(new Connection(p1, new Point(row, col - 1)));
                }
                if (right){
                    ans.add(new Connection(p1, new Point(row, col + 1)));
                }
                if (top){
                    ans.add(new Connection(p1, new Point(row + 1, col)));
                }
                if (bottom){
                    ans.add(new Connection(p1, new Point(row - 1, col)));
                }
            }
        }

        return ans;
    }

    public static double randomGridGenerator(boolean graphicsOn, int N, int t){

        Stopwatch s = new Stopwatch();

        int cnt = 0;

        if (graphicsOn){
            StdDraw.setCanvasSize(1200, 680);
            StdDraw.setXscale(0, N);
            StdDraw.setYscale(0, N);
            StdDraw.setPenRadius(0.005);
        }


        RandomBag<Connection> connections = RandomGridGenerator.generate(N);
        UnionFind wqu = null;
        switch (t){
            case(1):
                wqu = new QuickFind(N*N);
                break;
            case (2):
                wqu = new QuickUnion(N*N);
                break;
            case (3):
                wqu = new WeightedQuickUnion(N*N);
                break;
            case (4):
                wqu = new WeightedQuickUnionWithPathCompression(N*N);

        }

        for (Connection c : connections){
          //  System.out.println(c);
            cnt++;

            Point p = c.p;
            Point q = c.q;


            int ip = N*p.p + p.q;
            int iq = N*q.p + q.q;

            if (graphicsOn){
                StdDraw.setPenRadius(0.01);
                StdDraw.setPenColor(Color.DARK_GRAY);
                StdDraw.point(p.p, p.q);
                StdDraw.point(q.p, q.q);
            }


            if(!(wqu.find(ip) == wqu.find(iq))) {
                wqu.union(ip, iq);
                if (graphicsOn){
                    StdDraw.setPenRadius(0.005);
                    StdDraw.setPenColor(Color.RED);
                    StdDraw.line(p.p, p.q, q.p, q.q);
                    try {
                        Thread.sleep(10);
                    } catch(InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                }

            }

            if(wqu.count() == 1){
                break;
            }
        }

        //System.out.println("Count : " + wqu.count());
        return s.elapsedTime();
    }

    public static void compareQU(){
        int T = StdIn.readInt();
        int N = 3;

        double t1 = 1.0, t2 = 1.0, t3 = 1.0, t4 = 1.0;
        double[] times = new double[T];
        double times1, times2, times3, times4;

        for (int i = 0; i < 10; i++) {

            for (int j = 0; j < T; j++) {
                times[j] = RandomGridGenerator.randomGridGenerator(false, N, 1);
            }

            times1 = StdStats.mean(times);

            for (int j = 0; j < T; j++) {
                times[j] = RandomGridGenerator.randomGridGenerator(false, N, 2);
            }

            times2 = StdStats.mean(times);

            for (int j = 0; j < T; j++) {
                times[j] = RandomGridGenerator.randomGridGenerator(false, N, 3);
            }

            times3 = StdStats.mean(times);

            for (int j = 0; j < T; j++) {
                times[j] = RandomGridGenerator.randomGridGenerator(false, N, 4);
            }

            times4 = StdStats.mean(times);

            System.out.println("N = " + N + " ||  Time for 1 = " + times1 + " || Time for 2 = " + times2 +
                    "|| Time for 3 = " + times3 + "|| Time for 4 = times4 " + times4 +
                    " || Ratio = " + times1/t1 + ": " + times2/t2 + ": " + times3/t3 + ": " + times4/t4);

            t1= times1;
            t2 = times2;
            t3 = times3;
            t4 = times4;
            N+=N;


        }
    }

    public static void main(String[] args) {

        //1.5.18
        RandomGridGenerator.randomGridGenerator(true, 50, 3);

        //1.5.25
        //RandomGridGenerator.compareQU();


    }
}
