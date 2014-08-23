package com.princeton.algs4.chp1.code.stackQueueBag.bag;

import com.princeton.StdRandom;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: Ravi
 * Date: 12/14/13
 * Time: 3:23 PM
 */
public class RandomBag<Item> implements Iterable<Item> {
    private int size;
    private Item[] bag;

    public RandomBag() {
        bag = (Item[]) new Object[2];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void add(Item i){
       if (size == bag.length){
           resize(2*bag.length);
       }
       bag[size++] = i;
    }

    private void resize(int ns){
        Item[] temp = (Item[]) new Object[ns];
        for (int i = 0; i < size; i++) {
            temp[i] = bag[i];
        }
        bag = temp;
        temp = null;
    }


    @Override
    public Iterator<Item> iterator() {
        return new RandomBagIterator<Item>();  //To change body of implemented methods use File | Settings | File Templates.
    }

    class RandomBagIterator<Item> implements Iterator<Item>{

        int i= 0;

        RandomBagIterator() {
           i = 0;
           StdRandom.shuffle(bag, 0, size - 1);
        }

        @Override
        public boolean hasNext() {
            return i<size;  //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public Item next() {
            return (Item) bag[i++];
        }

        @Override
        public void remove() throws UnsupportedOperationException{
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }

}
