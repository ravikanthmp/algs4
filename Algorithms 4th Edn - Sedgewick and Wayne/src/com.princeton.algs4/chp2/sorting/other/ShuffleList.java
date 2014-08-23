package com.princeton.algs4.chp2.sorting.other;

import com.princeton.StdRandom;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 2/19/14
 * Time: 7:52 PM
 */
public class ShuffleList<Item> {

    private int N=0;
    public Node f = null;

    public ShuffleList(int n) {
        N = n;
    }

    public static void main(String[] args) {
        ShuffleList<Integer> list = new ShuffleList<Integer>(10);
        list.print(list.generateList());
        list.print(list.shuffle(list.f));
    }


    public void print(Node n){
        while (n!=null){
            System.out.print(n.i + " ");
            n=n.next;
        }
        System.out.println();
    }

    public Node generateList(){
        Node f=null, curr=null;
        curr = new Node(null, (Item) new Integer(StdRandom.uniform(-100, 100)));
        f = curr;
        for (int i = 0; i < N-1; i++){
            curr.next =  new Node(null, (Item) new Integer(StdRandom.uniform(-100, 100)));
            curr = curr.next;
        }
        this.f = f;
        return f;
    }

    public Node shuffle(Node l) {
        int i = 0;
        Node f = l;
        while (l != null) {
            l = l.next;
            i++;
        }

        if (i > 0) {
            return shuffle(f, i);
        }



        return f;
    }

    private Node shuffle(Node start, int ln) {
        if (ln == 1) {
            start.next = null;
            return start;
        }else {
            int mid = ln/2;
            Node s1 = start, s2 = null;
            for (int i = mid; i > 1; i--){
                s1 = s1.next;
            }

            s2 = s1.next;
            s1.next = null;

            s1 = shuffle(start, mid);
            s2 = shuffle(s2, ln - mid);

            Node ans = null;

            ans =StdRandom.uniform(0.0,1.0)>0.5?merge(s1, s2, mid, ln-mid):merge(s2, s1, ln-mid, mid);

            return ans;
        }


    }

    private Node merge(Node s1, Node s2, int ln1, int ln2) {
        Node curr = null, first = null;
        boolean toss;
        while (!(s1 == null || s2 == null)){
            toss = StdRandom.uniform(0.0,1.0)>0.5?false:true;
            if (!toss){
                if (curr==null){curr=s1; first=curr;}
                else           {curr.next=s1; curr=curr.next;}
                s1=s1.next;
            }else {
                if (curr==null){curr=s2;first=curr;}
                else           {curr.next=s2; curr=curr.next;}
                s2=s2.next;
            }
        }

        if (s1!=null){
            while (s1!=null){
                curr.next=s1;curr=curr.next;s1=s1.next;
            }
        }else {
            while (s2!=null){
                curr.next=s2;curr=curr.next;s2=s2.next;
            }
        }

        curr.next=null;
        return first;
    }

    public class Node {
        Node(Node next, Item i) {
            this.next = next;
            this.i = i;
        }

        Node next;
        Item i;
    }


}
