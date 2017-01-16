package com.zhuowenfeng.iterator.union;

import com.google.common.collect.PeekingIterator;

import java.util.*;

/**
 * @author Wenfeng Zhuo (wz2366@columbia.edu)
 * @createAt 01-15-2017
 */
public class PriorityQueueMerger<T extends Comparable<T>> implements Merger<T> {


  public Iterator<T> union(List<PeekingIterator<T>> iterators) {
    List<T> res = new ArrayList<T>();

    PriorityQueue<PeekingIterator<T>> heap = new PriorityQueue<PeekingIterator<T>>((itr1, itr2) -> {
      return itr1.peek().compareTo(itr2.peek());
    });
    for (PeekingIterator itr : iterators) {
      if (itr.hasNext()) {
        heap.offer(itr);
      }
    }

    while (!heap.isEmpty()) {
      PeekingIterator<T> itr = heap.poll();
      T cur = itr.next();
      addToList(res, cur);
      if (itr.hasNext()) {
        heap.offer(itr);
      }
    }

    return res.iterator();
  }

}
