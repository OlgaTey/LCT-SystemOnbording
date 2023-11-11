package org.lesson;

import java.util.Arrays;

public class InsertionSorter implements Sorter{
    @Override
    public int[] sort(int[] numbers) {
        if (numbers == null) {
            throw new NullPointerException("Array is null");
        }

        int[] copy = Arrays.copyOf(numbers, numbers.length);

        for (int i = 1; i < copy.length; i++) {
            int current = copy[i];
            int j = i;
            while (j > 0 && copy[j - 1] > current) {
                copy[j] = copy[j - 1];
                j--;
            }
            copy[j] = current;
        }
        return copy;
    }
}
