package com.zhuowenfeng.iterator.union;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wenfeng Zhuo (wz2366@columbia.edu)
 * @createAt 01-15-2017
 */
public class HashedTuple<T extends Comparable<T>> extends Tuple<T> {

  private int hashValue;

  public HashedTuple(T... keys) {
    super(keys);
    computeHash();
  }

  public HashedTuple(List<T> keys) {
    super(keys);
    computeHash();
  }

  private void computeHash() {
    int h = 0;
    int i = 0;
    int len = this.getKeys().size();
    Integer half = 0;
    while (i < len) {
      if (this.getKeys().get(i).compareTo((T)half) >= 0) {
        h |= 1 << (len - 1 - i);
      }
      i ++;
    }
    this.hashValue = h;
  }

  @Override
  public int compareTo(Tuple<T> other) {
    if (other == null) {
      return 1;
    }
    if (other instanceof HashedTuple) {
      HashedTuple<T> hashedOther = (HashedTuple<T>) other;
      if (this.hashValue < hashedOther.getHashValue()) {
        return -1;
      } else if (this.hashValue > hashedOther.getHashValue()) {
        return 1;
      }
    }
    int curLen = this.getKeys().size();
    int otherLen = other.getKeys().size();
    List<T> curKeys = this.getKeys();
    List<T> otherKeys = other.getKeys();
    int n = Math.min(curLen, otherLen);
    int i = 0;
    while (i < n) {
      int cmp = curKeys.get(i).compareTo(otherKeys.get(i));
      if (cmp < 0) {
        return -1;
      } else if (cmp > 0) {
        return 1;
      } else {
        i ++;
      }
    }
    return curLen - otherLen;
  }

  public int getHashValue() {
    return hashValue;
  }
}
