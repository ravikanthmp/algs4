package com.princeton.algs4.chp2.sorting.code.applications;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 8/9/14
 * Time: 6:35 PM
 */
public class StockMarketTrading {

    private Comparator<Trade> buyer_cmp = new Comparator<Trade>() {
        @Override
        public int compare(Trade trade, Trade trade2) {
            return Double.valueOf(trade2.price).compareTo(trade.price);
        }
    };
    private Comparator<Trade> seller_cmp = new Comparator<Trade>() {
        @Override
        public int compare(Trade trade, Trade trade2) {
            return Double.valueOf(trade.price).compareTo(trade2.price);
        }
    };
    private PriorityQueue<Trade> buyer_pq = new PriorityQueue<Trade>(2, buyer_cmp);
    private PriorityQueue<Trade> seller_pq = new PriorityQueue<Trade>(2, seller_cmp);

    public static void main(String[] args) {
        StockMarketTrading smk = new StockMarketTrading()  ;
        smk.buy(12, 20.0);
        smk.sell(10, 19.0);

    }

    private void sell(int n, double p) {
        Trade t     = new Trade(n, p);
        seller_pq.add(t);
        if (!buyer_pq.isEmpty()) {
            doTrade();
        }
    }

    private void buy(int n, double p) {

        Trade t     = new Trade(n, p);
        buyer_pq.add(t);
        if (!seller_pq.isEmpty()) {
            doTrade();
        }
    }

    private void doTrade() {
        Trade buyer = buyer_pq.remove();
        Trade seller = seller_pq.remove();
        if (buyer.price >= seller.price) {
            int temp = Math.min(seller.units, buyer.units);

            buyer.units -= temp;
            seller.units -= temp;
            if (buyer.units != 0) {
                buyer_pq.add(buyer);
            }

            if (seller.units != 0) {
                seller_pq.add(seller);
            }

            System.out.println("After trade buyer has " + buyer.units +
                                " Seller has " + seller.units);

        }
    }

    class Trade {
        int units;
        double price;

        Trade(int units, double price) {
            this.units = units;
            this.price = price;
        }
    }
}
