package com.princeton.algs4.chp1.code.uf;

import com.princeton.In;
import com.princeton.Stopwatch;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 11/24/13
 * Time: 1:09 PM
 */
public class WeightedQuickUnionBasedOnTreeHeight extends WeightedQuickUnion {
    public WeightedQuickUnionBasedOnTreeHeight(int N) {
        super(N);
        for (int i = 0; i < size.length; i++) {
            size[i] = 1;
        }
    }

    @Override
    public void union(int p, int q) {

        int pc = find(p);
        int qc = find(q);

        if (pc == qc){
            return;
        }
        else {
            if (size[pc] < size[qc])
                id[pc] = qc;
            else if(size[qc] < size[pc])
                id[qc] = pc;
            else {
                id[qc] = pc;
                size[pc]++;
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

        WeightedQuickUnionBasedOnTreeHeight quickFind = new WeightedQuickUnionBasedOnTreeHeight(num);


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
