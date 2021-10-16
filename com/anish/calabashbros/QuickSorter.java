package com.anish.calabashbros;

public class QuickSorter<T extends Comparable<T>> implements Sorter<T> {

    private T[] a;

    @Override
    public void load(T[] a) {
        this.a = a;
    }

    @Override
    public void load(T[][] a, Factory<T> factory){
        this.a = factory.create(a.length * a[0].length);
        for (int i = 0; i < a.length * a[0].length; ++i)
            this.a[i] = a[i / a[0].length][i % a[0].length];
    }

    private void swap(int i, int j) {
        T temp;
        temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        plan += "" + a[i] + "<->" + a[j] + "\n";
    }

    private String plan = "";

    @Override
    public void sort() {
        qsort(0, this.a.length - 1);
    }

    public void qsort(int left, int right)
    {
        T pivot = a[(left + right) / 2];        
        int i = left, j = right;     
        while (i < j) {            
            while (i < j && a[j].compareTo(pivot) > 0) j--;                       
            while (i < j && a[i].compareTo(pivot) < 0) i++;                                      
            swap(i,j);           
        }                
        if (i - 1 > left) qsort(left,i-1);        
        if (j + 1 < right) qsort(j+1,right);
    }

    @Override
    public String getPlan() {
        return this.plan;
    }

}