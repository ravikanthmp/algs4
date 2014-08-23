package com.princeton.algs4.chp1.code.uf;

import com.princeton.StdIn;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 11/23/13
 * Time: 2:27 PM
 */
public class QuickUnion extends UnionFind {

    @Override
    public int find(int p) {
        while (p!=id[p]){
            p=id[p];
            arraccess+=2;
        }
        arraccess++;
        return p;
    }

    public QuickUnion(int N) {
        super(N);
    }

    @Override
    public void union(int p, int q) {
        
        arraccess = 0;
        
        int pc = find(p);
        int qc = find(q);

        if (pc == qc) return;
        else
        {
            id[pc] = qc;
            count--;
            arraccess++;

        }
    }
    
    public static void main(String[] args) {
        int num = StdIn.readInt();

        QuickUnion qu = new QuickUnion(num);
        qu.display();

        while (!StdIn.isEmpty()){
            int i1 = StdIn.readInt();
            int i2 = StdIn.readInt();

            qu.union(i1,i2);

            qu.display();

            System.out.println(" #array access" + qu.arraccess);
        }
    }
}
