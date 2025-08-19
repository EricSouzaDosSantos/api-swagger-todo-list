package com.desafios.apitodolist.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ApiError {
    private int httpStatus;
    private String message;

}
