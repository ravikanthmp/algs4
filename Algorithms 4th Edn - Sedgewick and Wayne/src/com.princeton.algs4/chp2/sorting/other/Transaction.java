package com.princeton.algs4.chp2.sorting.other;

import com.princeton.StdRandom;
import com.princeton.algs4.chp2.sorting.code.ShellSort;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 1/19/14
 * Time: 12:31 PM
 */
public class Transaction implements Comparable<Transaction>{

    double amount;

    public double getAmount() {
        return amount;
    }

    public Transaction(double amount) {
        this.amount = amount;

    }

    @Override
    public int compareTo(Transaction o) {
        if (amount < o.getAmount()) {return -1;}
        else if(amount > o.getAmount()) {return 1;}
        else return 0;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                '}';
    }

    public static void main(String[] args) {
        Transaction[] txn = new Transaction[50];
        for (int i = 0; i < txn.length; i++) {
            txn[i] = new Transaction(StdRandom.uniform(0.0, 1000));
        }

        ShellSort.display(txn);
        ShellSort.sort(txn);
        ShellSort.display(txn);




    }
}
