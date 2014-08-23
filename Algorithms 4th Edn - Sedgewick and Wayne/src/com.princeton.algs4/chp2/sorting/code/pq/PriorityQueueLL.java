package com.princeton.algs4.chp2.sorting.code.pq;

import com.princeton.In;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 6/26/14
 * Time: 9:43 PM
 */
public class PriorityQueueLL<Key extends Comparable<Key>> {

    private int N;
    private int lf = 0, lm = 1, ht = 0;
    private Node root, last;

    class Node{
        Key item;
        Node parent, rightChild, leftChild;

        public Node(Key key) {
            this.item = key;
        }
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }

    private void exch(Node n1, Node n2){
        Key temp = n1.item;
        n1.item = n2.item;
        n2.item = temp;
    }

    private Node leftParent(Node src){
        if (src == root){
            return src;
        } else if (lf == 1){
            Node temp = root;
            for (int i = 0; i < ht - 1; i++){
                temp = temp.rightChild;
            }
            return temp;
        }else if(isRightChild(src)){
            return src.parent;
        }else {
            Node temp= src;
            int lt = 0;
            while (isLeftChild(temp)){
                temp = temp.parent;
            }
            temp = temp.parent.leftChild;
            while (lt-- > 0){
                temp = temp.rightChild;
            }
            return temp.parent;
        }

    }

    private Node rightParent(Node src){
        if (src == root){
            return src;
        }else if (isLeftChild(src)){
            return src.parent;
        }else if (lf == lm){
            Node t = root;
            for (int i = 0; i < ht; i++){
                t = t.leftChild;
            }
            return t;
        }else {
            Node t = src;
            int lt = 0;
            while (isRightChild(t)){
                t = t.parent;
                lt++;
            }
            t = t.parent.rightChild;
            while (lt-- > 0){
                t = t.leftChild;
            }
            return t.parent;
        }


    }

    private boolean isRightChild(Node src) {
        return src == src.parent.rightChild;
    }

    private boolean isLeftChild(Node src){
        return src == src.parent.leftChild;
    }

    private boolean less(Node n1, Node n2){
        return n1.item.compareTo(n2.item) < 0;
    }

    private void swim(Node n){
        while (n != root){
            if (less(n.parent, n)){
                exch(n.parent, n);
            }
            n = n.parent;
        }
    }

    private void sink(Node n){
        Node ch = n.leftChild;
        while (ch != null){
            if (n.rightChild != null && less(ch, n.rightChild)){
                ch = n.rightChild;
            }

            if (less(n, ch)){
                exch(n, ch);
            }else {
                break;
            }

            n = ch;
            ch = n.leftChild;
        }
    }

    public void insert(Key key){

        Node n = new Node(key);
        if (N == 0){
            root =  n;
            root.parent = root;
        }else {
            Node p = rightParent(last);
            if (isLeftChild(last)){
                p.rightChild = n;
            }else {
                p.leftChild = n;
            }
        }

        N++;
        if (lf == lm){
            lm *= 2;
            lf = 0;
            ht++;
        }
        lf++;

        last = n;
        swim(n);
    }

    public Key delMax(){
        exch(root, last);
        Key ans = last.item;

        Node p = leftParent(last);

        if (isLeftChild(last)){
            last.parent.leftChild = null;
        }else {
            last.parent.rightChild = null;
        }

        N--;

        last = p.rightChild != null ? p.rightChild : p.leftChild;

        if (lf == 1){
            lm/=2;
            lf = lm;
            ht--;
        }else {
            lf--;
        }

        sink(root);
        return ans;


    }


    public static void main(String[] args) {
        PriorityQueueLL<String> pq = new PriorityQueueLL<String>();
        String fname = "F:\\Ravi\\Work\\mywork\\Algorithms\\Algorithms 4th Edn - Sedgewick and Wayne\\src\\com.princeton.algs4\\chp2\\sorting\\resource\\tiny.txt";

        String[] tokens = In.readStrings(fname);
        for (String s : tokens){
            pq.insert(s);
        }

        while (!pq.isEmpty()){
            System.out.print(pq.delMax() + " ");
        }


    }

}
