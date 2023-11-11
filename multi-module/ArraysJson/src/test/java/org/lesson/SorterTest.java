package org.lesson;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;


import static org.junit.jupiter.api.Assertions.*;

class SorterTest {
    /**
     * Метод предоставляющий реализации сортировки.
     *
     * @return
     */
    public static Stream<Arguments> sorters() {
        return Stream.of(Arguments.of(new BubbleSorter()),
                Arguments.of(new InsertionSorter()));
    }

    /**
     * Простая проверка сортировки
     *
     * @param sorter
     */
    @ParameterizedTest
    @MethodSource("sorters")
    void simpleTest(Sorter sorter) {
        int[] array = new int[]{5, 6, 2, 1};
        int[] actual = sorter.sort(array);
        Assertions.assertNotSame(array, actual);
        Assertions.assertTrue(isSorted(actual));
    }

    /**
     * Проверка сортировки пустого массива
     *
     * @param sorter
     */
    @ParameterizedTest
    @MethodSource("sorters")
    void emptyTest(Sorter sorter) {
        int[] array = new int[0];
        int[] actual = sorter.sort(array);
        Assertions.assertNotSame(array, actual);
    }

    /**
     * Проверка выбрасывания ошибки, при отправке null
     *
     * @param sorter
     */
    @ParameterizedTest
    @MethodSource("sorters")
    void nullTest(Sorter sorter) {
        int[] array = null;
        Assertions.assertThrows(NullPointerException.class, () -> sorter.sort(array));
    }

    /**
     * Проверка массива, на сортировку элементов по возрастанию
     *
     * @param array
     * @return
     */
    boolean isSorted(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; i++) {
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }
}