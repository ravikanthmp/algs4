package com.princeton.algs4.chp1.code.uf;

import com.princeton.In;
import com.princeton.Stopwatch;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 11/23/13
 * Time: 2:30 PM
 */
public class WeightedQuickUnion extends QuickUnion {

    int[] size;

    public WeightedQuickUnion(int N) {
        super(N);
        size = new int[N];
        for (int i = 0; i < N; i++) {
            size[i] = 1;
        }

    }


    @Override
    public void display() {
        super.display();    //To change body of overridden methods use File | Settings | File Templates.
        for (int i = 0; i < size.length; i++) {
            System.out.print(size[i] + " ");
        }
        System.out.println();
    }

    @Override
    public void union(int p, int q) {

        arraccess = 0;

        int pc = find(p);
        int qc = find(q);

        if (pc == qc){
            return;
        }
        else {
            if (size[pc] < size[qc]) {
                id[pc] = qc;
                size[qc]+=size[pc];
                arraccess+=5;
            }
            else {
                id[qc] = pc;
                size[pc]+=size[qc];
                arraccess+=5;
            }

            count--;
        }
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

        WeightedQuickUnion quickFind = new WeightedQuickUnion(num);
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
