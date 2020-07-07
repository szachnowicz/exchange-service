package com.szachnowicz.exchange.dto.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ErrorDTO {
    private ErrorCode errorCode;
    private String message;

    public ErrorDTO(ErrorCode errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }
}
