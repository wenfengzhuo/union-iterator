package com.zhuowenfeng.iterator.union;

import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Wenfeng Zhuo (wz2366@columbia.edu)
 * @createAt 01-15-2017
 */
public class DivideAndConquerMerger<T extends Comparable<T>> implements Merger<T> {

  protected PeekingIterator<T> mergeIterator(PeekingIterator<T> itr1, PeekingIterator<T> itr2) {
    List<T> res = new ArrayList<T>();
    while (itr1.hasNext() && itr2.hasNext()) {
      T k1 = itr1.peek();
      T k2 = itr2.peek();
      if (k1.compareTo(k2) <= 0) {
        addToList(res, itr1.next());
      } else {
        addToList(res, itr2.next());
      }
    }
    while (itr1.hasNext()) {
      addToList(res, itr1.next());
    }
    while (itr2.hasNext()) {
      addToList(res, itr2.next());
    }
    return Iterators.peekingIterator(res.iterator());
  }

  @Override
  public Iterator<T> union(List<PeekingIterator<T>> peekingIterators) {
    return union(peekingIterators, 0, peekingIterators.size() - 1);
  }

  protected PeekingIterator<T> union(List<PeekingIterator<T>> peekingIterators, int s, int e) {
    if (s == e) {
      return peekingIterators.get(s);
    }
    int mid = s + (e - s) / 2;
    PeekingIterator<T> l = union(peekingIterators, s, mid);
    PeekingIterator<T> r = union(peekingIterators, mid + 1, e);
    return mergeIterator(l, r);
  }

}
