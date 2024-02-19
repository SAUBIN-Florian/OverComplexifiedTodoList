package dev.florian.todolistservice.dtos;

import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HttpResponse {
    private String timestamp;
    private int statusCode;
    private HttpStatus status;
    private String message;
    private Map<?, ?> data;
}
