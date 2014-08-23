package com.princeton.algs4.chp1.others.uf;

import com.princeton.In;
import com.princeton.StdDraw;
import com.princeton.StdIn;
import com.princeton.StdRandom;
import com.princeton.algs4.chp1.code.uf.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 11/24/13
 * Time: 4:44 PM
 */
public class AmortizedPlots {

    private static Color[] c1 = {Color.black, Color.BLUE};
    private static Color[] c2 = {Color.yellow, Color.red};

    public AmortizedPlots() {


    }

    private static In readInput(){
        File f = new File("F:\\Ravi\\Work\\mywork\\Algorithms\\Algorithms 4th Edn - Sedgewick and Wayne\\src\\com.princeton.algs4\\chp1\\resource\\");

        In in = null;
        try {
            in = new In(f.getCanonicalPath() + "\\mediumUF.txt");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return in;


    }

    private static void setDraw(){
        StdDraw.setCanvasSize(1200, 680);
        StdDraw.setXscale(0, 1000);
        StdDraw.setYscale(0, 50);
        StdDraw.setPenRadius(0.005);
    }
    public static void drawQFPlot(){
        In in =  readInput();
        doDrawQF(in);
    }

    public static void drawQUPlot(){

        In in =  readInput();
        doDrawQU(in);
    }

    public static void drawWQUPlot(){
        In in = readInput();
        doDrawWQU(in);
    }

    public static void drawWQUPCPlot(){
        In in = readInput();
        doDrawWQUPC(in);
    }

    private static void doDrawQF(In in){
        int num = in.readInt();
        QuickFind qf = new QuickFind(num);
        draw(qf, in, c1);
    }

    private static void draw(UnionFind qf, In in, Color[] color){
        int total = 0;
        int c = 0;
        // No of sites
        while (!in.isEmpty()){
            int i1 = in.readInt();
            int i2 = in.readInt();
            qf.union(i1, i2);
            total+=qf.getArraccess();
            c++;
            StdDraw.setPenColor(color[0]);
            StdDraw.point(c, qf.getArraccess());
            StdDraw.setPenColor(color[1]);
            StdDraw.point(c, total/c);
        }
    }

    private static void doDrawQU(In in){
        int num = in.readInt();
        QuickUnion qf = new QuickUnion(num);
        draw(qf, in, c2);
    }

    private static void doDrawWQU(In in){
        int num = in.readInt();
        WeightedQuickUnion wqu = new WeightedQuickUnion(num);
        draw(wqu, in, c1);
    }

    private static void doDrawWQUPC(In in){
        int num = in.readInt();
        WeightedQuickUnionWithPathCompression wqupc = new WeightedQuickUnionWithPathCompression(num);
        draw(wqupc, in, c2);
    }


    public static void drawErdosRenyi(){
        int N = StdIn.readInt();
        StdDraw.setCanvasSize(1200, 680);
        StdDraw.setXscale(0, N*N);
        StdDraw.setYscale(0, 10000);
        StdDraw.setPenRadius(0.005);



        int numonnectionsProcessed = 0;
        int totalcost = 0;
        int cost = 0;

        WeightedQuickUnion wqu = new WeightedQuickUnion(N);
        while (wqu.count()!=1){
            int i = StdRandom.uniform(0, N);
            int j = StdRandom.uniform(0, N);
            wqu.union(i, j);
            numonnectionsProcessed++;
            cost = wqu.getArraccess();
            totalcost+=cost;

            StdDraw.setPenColor(Color.BLACK);
            StdDraw.point(numonnectionsProcessed, cost);
            StdDraw.setPenColor(Color.gray);
            StdDraw.point(numonnectionsProcessed, totalcost);

        }

    }



    public static void main(String[] args) {
     //   setDraw();
        drawErdosRenyi();

     //   drawQFPlot();
     //   drawQUPlot();

     //   drawWQUPlot();
     //   drawWQUPCPlot();
    }

}
