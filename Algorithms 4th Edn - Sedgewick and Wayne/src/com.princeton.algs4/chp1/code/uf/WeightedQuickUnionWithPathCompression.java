package com.princeton.algs4.chp1.code.uf;

import com.princeton.In;
import com.princeton.Stopwatch;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 11/24/13
 * Time: 11:30 AM
 */
public class WeightedQuickUnionWithPathCompression extends WeightedQuickUnion {

    public WeightedQuickUnionWithPathCompression(int N) {
        super(N);
    }

    @Override
    public int find(int p) {
        int p1 = p;

        while (p!=id[p]){
            p = id[p];
            arraccess++;
        }

        int root = p;

        while (p1!=id[p1]){
            int newp = id[p1];
            id[p1] = root;
            p1 = id[newp];

            arraccess+=3;
        }


        arraccess++;
        return root;
    }

    public static void main(String[] args) {
        File f = new File("F:\\Ravi\\Work\\mywork\\Algorithms\\Algorithms 4th Edn - Sedgewick and Wayne\\src\\com.princeton.algs4\\chp1\\resource\\");

        In in = null;
        try {
            in = new In(f.getCanonicalPath() + "\\largeUF.txt");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        // No of sites
        int num = in.readInt();

        WeightedQuickUnionWithPathCompression quickFind = new WeightedQuickUnionWithPathCompression(num);


        Stopwatch sw = new Stopwatch();

        while (!in.isEmpty()){
            int i1 = in.readInt();
            int i2 = in.readInt();

            quickFind.union(i1, i2);

            // System.out.println("Connected " + i1 + " " + i2);
        }

        System.out.println("Number of components " + quickFind.count());

        System.out.println("Algo with " + num + " sites " + (num-1)/2 + " unions " + "took : " + sw.elapsedTime());
    }

}
