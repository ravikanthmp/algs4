package com.princeton.algs4.chp1.code.uf;

/**
 * Created by Ravi on 12/22/13.
 */
public class DynamicGrowth extends WeightedQuickUnion{


   private int numSites;

    public DynamicGrowth() {
        super(1);
        numSites = 0;
    }

    private void resize(int newSize){
        int[] newIds = new int[newSize];
        int[] nSize = new int[newSize];
        for (int i = 0; i < numSites; i++) {
            newIds[i] = id[i];
            nSize[i] = size[i];
        }
        id = newIds;
        size = nSize;
    }


    public int newSite(){
        if (numSites == id.length){
            resize(2*id.length);
        }

            numSites++;
            size[numSites-1] = 1;
            id[numSites-1] = numSites-1;

        if(id.length!=1)
            count++;
        return numSites-1;
    }

    public static void main(String[] args) {
        DynamicGrowth dg = new DynamicGrowth();

        int i = dg.newSite();
        int j = dg.newSite();

        System.out.println(dg.count());
        System.out.println(dg.connected(i,j));


    }
}
