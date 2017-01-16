package com.zhuowenfeng.iterator.union;

/**
 * The benchmark tests for all mergers.
 *
 * @author Wenfeng Zhuo (wz2366@columbia.edu)
 * @createAt 01-15-2017
 */
public class ComparisonTest {

  public static void main(String[] args) {
    MergerTest mergerTest = new MergerTest();
    int[][] testSizes = {
        {10, 10, 5},
        {100, 10, 5},
        {1000, 10, 5},
        {10000, 10, 5},
        {100000, 10, 5},
        {10, 10, 5},
        {10, 100, 5},
        {10, 1000, 5},
        {10, 10000, 5},
        {10, 100000, 5}
    };
    for (int[] test : testSizes) {
      mergerTest.arbitraryTest(test[0], test[1], test[2], false,
          list -> new Tuple<Integer>(list),
          new PriorityQueueMerger<>(),
          new DivideAndConquerMerger<>()
      );
    }
  }

}
