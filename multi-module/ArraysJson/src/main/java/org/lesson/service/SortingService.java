package org.lesson.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.lesson.BubbleSorter;
import org.lesson.InsertionSorter;
import org.lesson.Sorter;
import org.lesson.model.Input;
import org.lesson.model.Output;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;

public class SortingService {
    private Sorter sorter;
    private ObjectMapper objectMapper;
    int[] sortedValues;

    public SortingService() {
        this.objectMapper = new ObjectMapper();
    }

    public void sortData(File file) throws IOException {
        Input input = getData(file);
        this.sorter = getSorter(input);

        Instant start = Instant.now();

        try {
            sortedValues = sorter.sort(input.getValues());
        } catch (NullPointerException e) {
            String jsonError = "{\"errorMessage\": \"Array is null\"}";
            objectMapper.writeValue(new File("src/main/resources/outpput.json"),
                    objectMapper.readValue(jsonError, Object.class));

            return;
        }

        Duration duration = Duration.between(start, Instant.now());
        Output output = new Output(duration.getNano(), sortedValues);

        objectMapper.writeValue(new File("src/main/resources/outpput.json"), output);

    }

    private Input getData(File file) throws IOException {
        return objectMapper.readValue(file, Input.class);
    }

    private Sorter getSorter(Input input) {
        if (input.getAlgorithm().equals("bubble") || input.getAlgorithm() == null) {
            return new BubbleSorter();
        }

        if (input.getAlgorithm().equals("insertion")) {
            return new InsertionSorter();
        }

        throw new RuntimeException("Метод сортировки не найден");
    }
}
