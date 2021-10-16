package com.anish.calabashbros;

public interface Factory<T>{
    public T[] create(int n);
}