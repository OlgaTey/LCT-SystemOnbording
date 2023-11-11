package org.lesson;

import java.util.Arrays;

public class BubbleSorter implements Sorter{
    @Override
    public int[] sort(int[] numbers) {
        if (numbers == null) {
            throw new NullPointerException("Array is null");
        }

        int[] copy = Arrays.copyOf(numbers, numbers.length);

        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 1; i < copy.length; i++) {
                if (copy[i - 1] > copy[i]) {
                    int tmp = copy[i];
                    copy[i] = copy[i - 1];
                    copy[i - 1] = tmp;
                    isSorted = false;
                }
            }
        }
        return copy;
    }
}
