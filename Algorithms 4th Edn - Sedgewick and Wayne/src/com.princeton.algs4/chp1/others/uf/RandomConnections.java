package com.princeton.algs4.chp1.others.uf;

import com.princeton.StdRandom;
import com.princeton.StdStats;
import com.princeton.algs4.chp1.code.uf.WeightedQuickUnionWithPathCompression;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 11/24/13
 * Time: 8:07 PM
 */
public class RandomConnections {

    public static void main(String[] args) {
        int N = 2500;
        System.out.println(count(N));

        int[] edges = new int[25];


        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 25; j++) {
                edges[j] = count(N);
            }
            System.out.println("N : " + N + " 0.5NlgN : " + 0.5*N*Math.log(N) + " Our Mean : " + StdStats.mean(edges));
            N*=2;
        }



    }


    private static int count(int N){
        int i = 0;
        WeightedQuickUnionWithPathCompression wq = new WeightedQuickUnionWithPathCompression(N);

        while (wq.count()!=1){
            int i1 = StdRandom.uniform(0, N);
            int i2 = StdRandom.uniform(0, N);
            if (!wq.connected(i1, i2)){
                wq.union(i1, i2);
            }
            i++;
        }

        return i;
    }
}
