package com.zhuowenfeng.iterator.union;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wenfeng Zhuo (wz2366@columbia.edu)
 * @createAt 01-15-2017
 */
public class Tuple<T extends Comparable<T>> implements Comparable<Tuple<T>> {

  private List<T> keys;

  public Tuple(T... keys) {
    this.keys = new ArrayList<T>();
    for (T k : keys) {
      this.keys.add(k);
    }
  }

  public Tuple(List<T> keys) {
    this.keys = keys;
  }

  public List<T> getKeys() {
    return keys;
  }

  @Override
  public int compareTo(Tuple<T> other) {
    if (other == null) {
      return 1;
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

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("(");
    for (T t : this.getKeys()) {
      sb.append(t).append(",");
    }
    if (sb.charAt(sb.length() - 1) == ',') {
      sb.deleteCharAt(sb.length() - 1);
    }
    sb.append(")");
    return sb.toString();
  }
}
