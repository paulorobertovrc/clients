package br.dev.pauloroberto.algafood.clientjava.model;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class Problem {
    private Integer status;
    private String type;
    private String title;
    private String detail;
    private OffsetDateTime timestamp;
    private String userMessage;

    private List<Object> fields = new ArrayList<>();

    @Data
    public static class Object {
        private String name;
        private String userMessage;
    }
}
