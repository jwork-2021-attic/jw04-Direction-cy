package com.anish.calabashbros;

public interface Sorter<T extends Comparable<T>> {
    public void load(T[] elements);

    public void load(T[][] elements, Factory<T> factory);

    public void sort();

    public String getPlan();
}
