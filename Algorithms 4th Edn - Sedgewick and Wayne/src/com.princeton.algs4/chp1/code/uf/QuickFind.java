package com.princeton.algs4.chp1.code.uf;

import com.princeton.StdIn;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 11/23/13
 * Time: 2:25 PM
 */
public class QuickFind extends UnionFind {

  
    @Override
    public int find(int p) {
        return id[p];
    }

    @Override
    public void union(int p, int q) {

        arraccess = 0;

       int cp = find(p);
       int cq = find(q);

        arraccess = 2;

        if (cp == cq) return;
        else {
            for (int i = 0; i < id.length; i++) {
                if (id[i] == cp){
                    id[i] = cq;
                    arraccess++;
                }

                arraccess++;
            }

            count--;


        }
    }

    public int arrayAccess(){
        return arraccess;
    }

    public QuickFind(int N) {
        super(N);
    }


    public static void main(String[] args) {

        int num = StdIn.readInt();

        QuickFind qf = new QuickFind(num);
        qf.display();

        while (!StdIn.isEmpty()){
            int i1 = StdIn.readInt();
            int i2 = StdIn.readInt();

            qf.union(i1,i2);

            qf.display();

            System.out.println(" #array access" + qf.arrayAccess());
        }

    }



}

