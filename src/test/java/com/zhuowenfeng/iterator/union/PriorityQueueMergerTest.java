package com.zhuowenfeng.iterator.union;

import com.google.common.collect.Iterators;
import com.google.common.collect.PeekingIterator;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author Wenfeng Zhuo (wz2366@columbia.edu)
 * @createAt 01-15-2017
 */
public class PriorityQueueMergerTest extends MergerTest {

  @Test
  public void basicTest() {
    basicTest(new PriorityQueueMerger<>());
  }

}
