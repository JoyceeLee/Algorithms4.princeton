// http://algs4.cs.princeton.edu/23quicksort/

/**
 * Basic Quick Sort algorithm
 * O(T) = n log (n), worst n^2
 */
public class QuickSort {
  private QuickSort() { }
  
  public static void sort(Comparable[] a) {
    sort(a, 0, a.length - 1);
  }
  
  private static void sort(Comparable[] a, int lo, int hi) {
    if (lo >= hi) return;
    int j = partition(a, lo, hi);
    sort(a, lo, j-1);
    sort(a, j+1, hi);
  }
  
  private static int partition(Comparable[] a, int lo, int hi) {
    Comparable v = a[lo];
    int i = lo;
    int j = hi + 1;
    while (true) {
      
      while (a[++i].compareTo(v) < 0)
        if (i == hi) break;
      
      while (a[--j].compareTo(v) > 0)
        if (j == lo) break;
        
      if (i >= j) break;
      
      exch(a, i, j);
    }
    exch(a, lo, j);
    return j;
  }
  
  private static void exch(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }
}

/**
 * Quick Bars, quicksort with cutoff to insertion sort and median-of-3 partitioning.
 * Improvements:
 * 1. Cutoff to insertion sort.
 * 2. Median-of-three partitioning
 */
public class QuickBars {
  private static final int CUTOFF = 8;
  
  private QuickBars() { }
  
  public static void sort(double[] a) {
    sort(a, 0, a.length - 1);
  }
  
  private static void sort(double[] a, int lo, int hi) {
    int N = hi - lo + 1;
    if (N <= CUTOFF) {
      insertionSort(a, lo, hi);
      return;
    }
    
    int m = median3(a, lo, lo + N/2, hi);
    exch(a, m, lo);
        
    int j = partition(a, lo, hi);
    sort(a, lo, j-1);
    sort(a, j+1, hi);
  }
  
  private static void insertionSort(double[] a, int lo, int hi) {
    for (int i = lo; i <= hi; i++)
      for (int j = i; j > lo && a[j] < a[j-1]; j--)
        exch(a, j, j-1);
  }

  private static int median3(double[] a, int i, int j, int k) {
    return (less(a[i], a[j]) ?
           (less(a[j], a[k]) ? j : less(a[i], a[k]) ? k : i) :
           (less(a[k], a[j]) ? j : less(a[k], a[i]) ? k : i));
  }
    
  private static int partition(double[] a, int lo, int hi) {
    double v = a[lo];
    int i = lo;
    int j = hi + 1;
    while (true) {
      
      while (a[++i] < v)
        if (i == hi) break;
      
      while (a[--j] > v)
        if (j == lo) break;
        
      if (i >= j) break;
      
      exch(a, i, j);
    }
    exch(a, lo, j);
    return j;
  }
  
  private static void exch(Object[] a, int i, int j) {
    Object swap = a[i];
    a[i] = a[j];
    a[j] = swap;
  }
}

