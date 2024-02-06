package com.astondev.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrayListTest {
    ArrayList<String> integerArrayList;

    @BeforeEach
    void setUp() {
        integerArrayList = new ArrayList<>();
        integerArrayList.add("2");
        integerArrayList.add("4");
        integerArrayList.add("6");
        integerArrayList.add("8");
        integerArrayList.add("10");
    }

    @Test
    void whenSizeThen5() {
        Assertions.assertEquals(5, integerArrayList.size());
    }

    @Test
    void whenGet2Then6() {
        Assertions.assertEquals("6", integerArrayList.get(2));
    }

    @Test
    void whenAdd19Add32Get1Then32() {
        integerArrayList.clear();
        integerArrayList.add("19");
        integerArrayList.add("32");
        Assertions.assertEquals("32", integerArrayList.get(1));
    }

    @Test
    void whenAdd10AtIndex1Get1Then10() {
        integerArrayList.add(1, "10");
        Assertions.assertEquals("10", integerArrayList.get(1));
    }

    @Test
    void whenAdd10AtIndex1Get4Then8() {
        integerArrayList.add(1, "10");
        Assertions.assertEquals("8", integerArrayList.get(4));
    }

    @Test
    void whenRemove4AndTrimThenSizeThen4() {
        integerArrayList.remove(("4"));
        Assertions.assertEquals(4, integerArrayList.size());
    }

    @Test
    void whenRemove4ThenTrue() {
        Assertions.assertTrue(integerArrayList.remove(("4")));
    }

    @Test
    void whenRemove12ThenFalse() {
        Assertions.assertFalse(integerArrayList.remove(("12")));
    }

    @Test
    void whenClearThenSize0() {
        integerArrayList.clear();
        Assertions.assertEquals(0, integerArrayList.size());
    }

    @Test
    void whenRemoveTwiceAndTrimToSizeThenElementDataLength3() {
        integerArrayList.remove("4");
        integerArrayList.remove("6");
        integerArrayList.trimToSize();
        Assertions.assertEquals(3, integerArrayList.elementData.length);
    }

    @Test
    void whenRemoveTwiceThenElementDataLength10() {
        integerArrayList.remove("4");
        integerArrayList.remove("6");
        Assertions.assertEquals(10, integerArrayList.elementData.length);
    }

    @Test
    void whenAdded11ElementsThenLength16() {
        integerArrayList.add("4");
        integerArrayList.add("6");
        integerArrayList.add("4");
        integerArrayList.add("6");
        integerArrayList.add("4");
        integerArrayList.add("4");
        Assertions.assertEquals(16, integerArrayList.elementData.length);
    }

    @Test
    void whenQuickSort126411510Then146101215() {
        ArrayList<Integer> sortArr = new ArrayList<>();
        sortArr.add(12);
        sortArr.add(6);
        sortArr.add(4);
        sortArr.add(1);
        sortArr.add(15);
        sortArr.add(10);
        sortArr.quickSort(0, sortArr.size() - 1);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1);
        expected.add(4);
        expected.add(6);
        expected.add(10);
        expected.add(12);
        expected.add(15);
        Assertions.assertEquals(expected, sortArr);
    }

    @Test
    void whenQuickSort888999000111Then000111888999() {
        ArrayList<Integer> sortArr = new ArrayList<>();
        sortArr.add(8);
        sortArr.add(8);
        sortArr.add(8);
        sortArr.add(9);
        sortArr.add(9);
        sortArr.add(9);
        sortArr.add(0);
        sortArr.add(0);
        sortArr.add(0);
        sortArr.add(1);
        sortArr.add(1);
        sortArr.add(1);
        sortArr.quickSort(0, sortArr.size() - 1);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(0);
        expected.add(0);
        expected.add(1);
        expected.add(1);
        expected.add(1);
        expected.add(8);
        expected.add(8);
        expected.add(8);
        expected.add(9);
        expected.add(9);
        expected.add(9);
        Assertions.assertEquals(expected, sortArr);
    }
}