package LeaningDataStructures.List;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingDeque;

public class IntegerArrayList implements IntegerListInterface{
    private Integer[] item;
    private int numItems;
    private static final int DEFAULT_CAPACITY = 64;

    public IntegerArrayList() {
        item = new Integer[DEFAULT_CAPACITY];
        numItems = 0;
    }

    public IntegerArrayList(int n) {
        item = new Integer[n];
        numItems = 0;
    }

    @Override
    public void add(int index, Integer x) {
        if (numItems >= item.length || index < 0 || index > numItems){
            /*예외처리*/
        }else{
            for (int i = numItems - 1; i >=index ; i++ ){
                item[i + 1] = item[i];
                item[index] = x;
                numItems++;
            }
        }
    }

    @Override
    public void append(Integer x) {
        if (numItems >= item.length){
            /*예외처리*/
        }else {
            item[numItems++] = x;
        }
    }

    @Override
    public Integer remove(int i) {
        if (isEmpty() || i < 0 || i > numItems -1){
            return null;
        }else {
            Integer tmp = item[i];
            for (int index = i; index <= numItems-2 ;index++)
                item[index] = item[index+1];
            numItems--;
            return tmp;
        }
    }

    @Override
    public boolean removeItem(Integer x) {
        int k = 0;
        while (k < numItems && item[k].compareTo(x) != 0)
            k++;
        if (k == numItems){
            return  false;
        }else {
            for (int i = k ; i <= numItems -2 ; i++)
                item[i] = item[i+1];
            numItems--;
            return  true;
        }
    }

    @Override
    public Integer get(int i) {
        if (i >= 0 && i <= numItems -1){
            return item[i];
        }else {
            return null;
        }
    }

    @Override
    public void set(int i, Integer x) {
        if (i >= 0 && i <= numItems -1){
            item[i] = x;
        }else {
            /* 예외처리 */
        }
    }

    @Override
    public int len() {
        return numItems;
    }

    @Override
    public boolean isEmpty() {
        return numItems ==0;
    }

    @Override
    public void claer() {
        item = new Integer[item.length];
        numItems = 0;
    }
}
