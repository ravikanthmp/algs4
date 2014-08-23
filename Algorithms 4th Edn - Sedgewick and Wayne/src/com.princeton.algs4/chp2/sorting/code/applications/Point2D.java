package com.princeton.algs4.chp2.sorting.code.applications;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 8/9/14
 * Time: 7:14 PM
 */
public class Point2D {

    private int x, y;

    public static Comparator<Point2D> BY_X_COORDINATE = new Comparator<Point2D>() {
        @Override
        public int compare(Point2D point2D, Point2D point2D2) {
            return point2D.x - point2D2.x;
        }

    }  ;

    public static Comparator<Point2D> BY_Y_COORDINATE = new Comparator<Point2D>() {
        @Override
        public int compare(Point2D point2D, Point2D point2D2) {
            return point2D.y - point2D2.y;
        }

    }  ;

    public static Comparator<Point2D> BY_DISTANCE_FROM_ORIGIN = new Comparator<Point2D>() {
        @Override
        public int compare(Point2D point2D, Point2D point2D2) {
            return (int) (point2D.distance() - point2D2.distance());
        }

    }  ;

    public Comparator<Point2D> BY_DISTANCE_FROM_THIS_POINT = new Comparator<Point2D>() {
        @Override
        public int compare(Point2D point2D, Point2D point2D2) {
            double d1 = Math.sqrt( (point2D.x - x)*2 - (point2D.y - y)*2);
            double d2 = Math.sqrt( (point2D2.x - x)*2 - (point2D2.y - y)*2);
            return Double.valueOf(d1).compareTo(Double.valueOf(d2));
        }
    }      ;

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance(){
        return Math.sqrt(x*x + y*y);
    }
}
