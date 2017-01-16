package com.zhuowenfeng.iterator.union;

import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Provides general methods for testing
 *
 * @author Wenfeng Zhuo (wz2366@columbia.edu)
 * @createAt 01-15-2017
 */
public class MergerTest {

  /**
   * Basic test for different mergers
   *
   * @param merger
   */
  public void basicTest(Merger<Tuple<Integer>> merger) {
    List<Tuple<Integer>> l0 = Arrays.asList(
        new Tuple<Integer>(1, 3, 4),
        new Tuple<Integer>(3, 6, 7)
    );
    List<Tuple<Integer>> l1 = Arrays.asList(
        new Tuple<Integer>(2, 4, 5),
        new Tuple<Integer>(4, 7, 9)
    );
    List<PeekingIterator<Tuple<Integer>>> iterators = Arrays.asList(
        Iterators.peekingIterator(l0.iterator()),
        Iterators.peekingIterator(l1.iterator())
    );

    Iterator<Tuple<Integer>> res = merger.union(iterators);

    List<Tuple<Integer>> expected = Arrays.asList(
        new Tuple<Integer>(1, 3, 4),
        new Tuple<Integer>(2, 4, 5),
        new Tuple<Integer>(3, 6, 7),
        new Tuple<Integer>(4, 7, 9)
    );
    int i = 0;
    while (res.hasNext()) {
      assertTrue(res.next().toString().equals(expected.get(i++).toString()));
    }
  }

  /**
   * Test mergers with given number of iterators, tuples in each iterator, and
   * keys in each tuple.
   *
   * @param M - number of iterators
   * @param N - number of tuples in each iterator
   * @param K - number of keys in each tuple
   * @param verify - whether to verify result or not
   * @param tupleGen - generate a tuple
   * @param mergers - merges to test
   */
  public void arbitraryTest(int M, int N, int K, boolean verify,
                            Function<List<Integer>, Tuple<Integer>> tupleGen,
                            Merger<Tuple<Integer>>... mergers) {
    System.out.printf("%d, %d, %d, ", M, N, K);
    List<List<Tuple<Integer>>> lists = new ArrayList<>();
    Random r = new Random();
    for (int i = 0; i < M; i ++) {
      List<Tuple<Integer>> list = new ArrayList<>();
      for (int j = 0; j < N; j ++) {
        List<Integer> keys = new ArrayList<>();
        for (int z = 0; z < K; z ++) {
          keys.add(r.nextInt());
        }
        list.add(tupleGen.apply(keys));
      }
      Collections.sort(list);
      lists.add(list);
    }

    List<Tuple<Integer>> expected = null;
    if (verify) {
      expected = getExpected(lists);
    }

    for (Merger<Tuple<Integer>> merger : mergers) {
      List<PeekingIterator<Tuple<Integer>>> iterators = lists.stream().map(
          l -> Iterators.peekingIterator(l.iterator())
      ).collect(Collectors.toList());

      long start = System.currentTimeMillis();
      Iterator<Tuple<Integer>> res = merger.union(iterators);
      long end = System.currentTimeMillis();

      System.out.printf("%d, ", end - start);

      if (verify) {
        int i = 0;
        while (res.hasNext()) {
          assertTrue(res.next().toString().equals(expected.get(i++).toString()));
        }
        assertEquals(expected.size(), i);
      }
    }
    System.out.println();
  }

  /**
   * Get expected results. The simple way is to sort all tuples with built-in library.
   *
   * @param tuples
   * @return
   */
  private List<Tuple<Integer>> getExpected(List<List<Tuple<Integer>>> tuples) {
    List<Tuple<Integer>> res = new ArrayList<>();
    for (List<Tuple<Integer>> list : tuples) {
      res.addAll(list);
    }
    Collections.sort(res);
    return res;
  }


}
