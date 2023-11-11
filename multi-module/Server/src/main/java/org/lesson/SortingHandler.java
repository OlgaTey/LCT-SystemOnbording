package org.lesson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.lesson.model.Input;
import org.lesson.model.Output;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.time.Duration;
import java.time.Instant;

public class SortingHandler implements HttpHandler {
    ObjectMapper objectMapper = new ObjectMapper();
    Sorter sorter;

    int[] sortedValues;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        URI uri = exchange.getRequestURI();
        String param = getParam(uri);

        if (param == null || param.equals("bubble")) {
            this.sorter = new BubbleSorter();
        } else if (param.equals("insertion")){
            this.sorter = new InsertionSorter();
        } else {
            String error = "сортировка не существует";
            exchange.sendResponseHeaders(404,0);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(error.getBytes());
            }
            return;
        }

        switch(method) {
            case "POST":
                InputStream inputStream = exchange.getRequestBody();
                Input input = objectMapper.readValue(inputStream, Input.class);
                Instant start = Instant.now();

                try {
                    sortedValues = (sorter.sort(input.getValues()));
                } catch (NullPointerException e) {
                    String jsonError = "{\"errorMessage\": \"Array is null\"}";
                    exchange.sendResponseHeaders(400,0);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(jsonError.getBytes());
                    }
                    return;
                }

                Duration duration = Duration.between(start, Instant.now());
                Output output = new Output(duration.getNano(), sortedValues);

                String response = objectMapper.writeValueAsString(output);

                exchange.sendResponseHeaders(200,0);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
                break;
            default:
                System.out.println("Метод не обрабатывается: " + method);
                exchange.sendResponseHeaders(404, 0 );
        }
    }

    private String getParam(URI uri) {
        if (uri.getQuery() == null) {
            return null;
        }
        return uri.getQuery().substring(10);
    }
}
