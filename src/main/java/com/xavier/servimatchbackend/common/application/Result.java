package com.xavier.servimatchbackend.common.application;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the result of an operation, encapsulating success status, data, errors, and a message.
 *
 * @param <T> the type of data contained in the result
 */

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    private boolean success;
    private T data;
    private List<String> errors;
    private String message;


    public static <T> Result<T> success(T data) {
        return new Result<>(true, data, new ArrayList<>(), null);
    }

    public static <T> Result<T> success(T data, String message) {
        return new Result<>(true, data, new ArrayList<>(), message);
    }

    public static <T> Result<T> failure(String error) {
        List<String> errors = new ArrayList<>();
        errors.add(error);
        return new Result<>(false, null, errors, null);

    }

    public static <T> Result<T> failure(List<String> errors) {
        return new Result<>(false, null, errors, null);
    }
}
