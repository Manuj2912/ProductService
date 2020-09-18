package com.company.dev.productService.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class ServiceResponse implements Serializable {
    private String responseCode;
    private String message;
    private String success;
}
