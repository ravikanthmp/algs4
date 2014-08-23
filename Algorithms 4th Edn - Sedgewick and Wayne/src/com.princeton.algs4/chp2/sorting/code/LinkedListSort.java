package com.princeton.algs4.chp2.sorting.code;

import com.princeton.StdRandom;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 2/1/14
 * Time: 3:36 PM
 */
public class LinkedListSort {

    public static Node first, globalFirst;

    public static void main(String[] args) {
        Node l = createLinkedList(5);
        display(l);

        assert !isSorted(l);

        try {
            sort(l);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        display(globalFirst);

        assert isSorted(globalFirst);

    }

    private static void sort(Node ll) {

        if (ll == null) {
            return;
        }

        if (ll.next == null) {
            return;
        }

        globalFirst = ll;
        Node i, lo, pLast, hi, t = null;
        int ls, rs, nMid;
        do {
            i = globalFirst;
            nMid = 0;
            pLast = null;
            do {
                lo = i;
                hi = null;
                ls = 1;
                rs = 0;
                while (i.next != null) {
                    if (i.item.compareTo(i.next.item) > 0) {
                        hi = i.next;
                        rs = 1;
                        nMid++;
                        i = i.next;
                        break;
                    } else {
                        i = i.next;
                        ls++;
                    }
                }

                if (i.next != null) {
                    while (i.next != null) {
                        if (i.item.compareTo(i.next.item) > 0) {
                            i = i.next;
                            break;
                        } else {
                            i = i.next;
                            rs++;
                        }
                    }

                }

                if (i.next == null)
                    i = i.next;

                if (hi != null) {
                    if (pLast == null) {
                        pLast = merge(lo, ls, hi, rs);
                        globalFirst = first;
                    } else {
                        t = merge(lo, ls, hi, rs);
                        pLast.next = first;
                        pLast = t;
                    }
                    pLast.next = i;
                }



            } while (i != null && i.next != null);
        } while (nMid > 0);


    }

    private static Node createCustomList(){
        Node n = new Node(null, new Double(-633));
        Node first = n;

        n.next = new Node(null, new Double(-851));
        n = n.next;

        n.next = new Node(null, new Double(959));
        n = n.next;

        n.next = new Node(null, new Double(2677));
        n = n.next;

        n.next = new Node(null, new Double(-2043));

        return first;
    }

    private static Node merge(Node l, int ls, Node r, int rs) {
        Node f = null, curr = null;
        while (ls > 0 && rs > 0) {
            if (l.item.compareTo(r.item) <= 0) {
                if (curr == null) {
                    curr = l;
                    f = curr;
                } else {
                    curr.next = l;
                    curr = curr.next;
                }
                l = l.next;
                ls--;
            } else {
                if (curr == null) {
                    curr = r;
                    f = curr;
                } else {
                    curr.next = r;
                    curr = curr.next;
                }
                r = r.next;
                rs--;
            }
        }
        if (ls > 0) {
            while (ls > 0) {
                curr.next = l;
                l = l.next;
                curr = curr.next;
                ls--;
            }
        } else {
            while (rs > 0) {
                curr.next = r;
                r = r.next;
                curr = curr.next;
                rs--;
            }
        }

        first = f;
        curr.next = null;
        return curr;
    }

    private static Node createLinkedList(int N) {
        Node c = new Node(null, new Double(StdRandom.uniform(-5000.0, 5000.0)));
        Node f = c;

        for (int i = 0; i < N - 1; i++) {
            c.next = new Node(null, new Double(StdRandom.uniform(-5000.0, 5000.0)));
            c = c.next;
        }

        return f;

    }

    public static void display(Node f) {
        while (f != null) {
            System.out.print(f.item + " ");
            f = f.next;
        }

        System.out.println();
    }

    private static class Node {
        Node next;
        Comparable item;

        Node(Node next, Comparable item) {
            this.next = next;
            this.item = item;
        }
    }

    private static boolean isSorted(Node l){
        while (l.next!=null){
            if (l.item.compareTo(l.next.item) > 0){
                return false;
            }

            l = l.next;
        }

        return true;
    }


}
