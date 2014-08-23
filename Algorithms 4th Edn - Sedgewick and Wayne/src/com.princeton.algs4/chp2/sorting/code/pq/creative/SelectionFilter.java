package com.princeton.algs4.chp2.sorting.code.pq.creative;

import com.princeton.StdRandom;
import com.princeton.Stopwatch;
import com.princeton.algs4.chp2.sorting.code.pq.PriorityQueue;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 6/30/14
 * Time: 10:37 PM
 */
public class SelectionFilter {

    public void test(int M, int N){
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(M);

        Integer[] points = new Integer[N];
        for (int i = 0 ; i < N; i++){
            points[i] = new Integer(StdRandom.uniform(10));
        }

        display(points);

        int i = 0 ;
        for (i = 0; i < M; i++){
            pq.add(points[i]);
        }


        for ( ; i < N; i++){
            pq.delMax();
            pq.add(points[i] );
        }


        // nearest M points
        while (!pq.isEmpty()){
            System.out.print(pq.delMax() + " ");
        }
    }

    public void Mclosest( int M, int N) {
        PriorityQueue<Point> pq = new PriorityQueue<Point>(M);

        Point[] points = new Point[N];
        for (int i = 0 ; i < N; i++){
            points[i] = new Point(StdRandom.uniform(-10.0, 10.0), StdRandom.uniform(-10.0, 10.0));
        }

        display(points);

        int i = 0 ;
        for (i = 0; i < M; i++){
            pq.add(points[i]);
        }


        for ( ; i < N; i++){
            pq.delMax();
            pq.add(points[i] );
        }


        // nearest M points
        while (!pq.isEmpty()){
            System.out.print(pq.delMax() + " ");
        }
    }


    public static void main(String[] args) {
        SelectionFilter sf = new SelectionFilter();
        Stopwatch s = new Stopwatch();
        sf.test(1000, 1000000);
        System.out.println(s.elapsedTime());
    }

    private void display(Comparable[] a){
        for (int i = 0; i < a.length; i++){
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    class Point implements Comparable<Point> {
        double x, y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }


        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        public Double distance() {
            return Math.sqrt(x * x + y * y);
        }

        @Override
        public int compareTo(Point point) {
            return distance().compareTo(point.distance());
        }
    }

}
