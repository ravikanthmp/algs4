package com.princeton.algs4.chp1.code.uf;

import com.princeton.In;
import com.princeton.Stopwatch;

import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 11/23/13
 * Time: 2:18 PM
 */
public abstract class UnionFind {

    int[] id;
    int count;
    int arraccess = 0;

    public int getArraccess() {
        return arraccess;
    }

    protected UnionFind(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            count = N;
        }
    }

    public boolean connected(int p, int q){
        return find(p) == find(q);
    }

    public int count(){
        return count;
    }

    public abstract int find(int p);

    public abstract void union(int p, int q);

    public void display(){
        for (int i = 0; i < id.length; i++) {
            System.out.print(id[i] + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {



        File f = new File("F:\\Ravi\\Work\\mywork\\Algorithms\\Algorithms 4th Edn - Sedgewick and Wayne\\src\\com.princeton.algs4\\chp1\\resource\\");

        In in = null;
        try {
            in = new In(f.getCanonicalPath() + "\\mediumUF.txt");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        // No of sites
        int num = in.readInt();

        QuickFind quickFind = new QuickFind(num);
        UnionFind uf = quickFind;

        Stopwatch sw = new Stopwatch();

        while (!in.isEmpty()){
            int i1 = in.readInt();
            int i2 = in.readInt();

            uf.union(i1, i2);

            System.out.println("Connected " + i1 + " " + i2);
        }

        System.out.println("Number of components " + uf.count());

        System.out.println("Algo with " + num + " sites " + (num-1)/2 + " unions " + "took : " + sw.elapsedTime());


    }

}
