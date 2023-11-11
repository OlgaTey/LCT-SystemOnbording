package org.lesson;

import org.lesson.service.SortingService;

import java.io.File;
import java.io.IOException;

public class MainSorter {
    public static void main(String[] args) throws IOException {

//        Sorter sorter = new BubbleSorter();
//        int[] numbers = new int[] {1,4,0,15,13,12,5};
//
//        int[] sortedNumbers = sorter.sort(numbers);
//
//
//        Sorter insertSorter = new InsertionSorter();
//        int[] numbers2 = new int[] {1,4,0,15,13,12,5,5435, 3, 4,6,2,55,5,6};
//        int[] sortedNumbers2 = insertSorter.sort(numbers2);
//


        SortingService sortingService = new SortingService();
        sortingService.sortData(new File("src/main/resources/input.json"));







        System.out.println("Ура!");





    }
}