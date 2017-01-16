package com.zhuowenfeng.iterator.union;

import org.junit.Test;

/**
 * @author Wenfeng Zhuo (wz2366@columbia.edu)
 * @createAt 01-15-2017
 */
public class ParallelMergerTest extends MergerTest {

  @Test
  public void basicTest() {
    basicTest(new ParallelMerger<>());
  }

}
