package com.zhuowenfeng.iterator.union;

import com.google.common.collect.PeekingIterator;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * A merger could merge multiple sorted iterators into a single sorted iterators
 *
 * @author Wenfeng Zhuo (wz2366@columbia.edu)
 * @createAt 01-15-2017
 */
public interface Merger<T extends Comparable<T>> {

  Iterator<T> union(List<PeekingIterator<T>> iterators);

  /**
   * To avoid duplicate elements, we need to discard the duplicate elements
   *
   * @param list
   * @param ele
   */
  default void addToList(List<T> list, T ele) {
    if (list.size() == 0 || list.get(list.size() - 1).compareTo(ele) != 0) {
      list.add(ele);
    }
  }

}
