package com.zhuowenfeng.iterator.union;

import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Wenfeng Zhuo (wz2366@columbia.edu)
 * @createAt 01-15-2017
 */
public class ParallelMerger<T extends Comparable<T>> extends DivideAndConquerMerger<T> {

  /**
   * A thread pool to serve threads during computation
   */
  private ExecutorService executorService = Executors.newFixedThreadPool(16);

  @Override
  protected PeekingIterator<T> union(List<PeekingIterator<T>> peekingIterators, int s, int e) {
    if (s > e) {
      return Iterators.peekingIterator(new ArrayList<T>().iterator());
    } else if (s == e) {
      return peekingIterators.get(s);
    }
    int mid = s + (e - s) / 2;
    PeekingIterator<T> l = union(peekingIterators, s, mid);

    Callable<PeekingIterator<T>> runnable = () -> {
      PeekingIterator<T> r = union(peekingIterators, mid + 1, e);
      return r;
    };
    Future<PeekingIterator<T>> res = executorService.submit(runnable);

    PeekingIterator<T> r = null;
    try {
      r = res.get();
    } catch (InterruptedException e1) {
      e1.printStackTrace();
    } catch (ExecutionException e1) {
      e1.printStackTrace();
    }

    return mergeIterator(l, r);
  }

  public void close() {
    executorService.shutdown();
  }

}
