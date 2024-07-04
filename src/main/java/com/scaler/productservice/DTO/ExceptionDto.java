package com.scaler.productservice.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionDto {
    Long id;
    String message;
    String solution;
}
