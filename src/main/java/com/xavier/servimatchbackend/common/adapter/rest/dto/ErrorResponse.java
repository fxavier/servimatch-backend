package com.xavier.servimatchbackend.common.adapter.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Error response")
public class ErrorResponse {

    @Schema(description = "Error timestamp")
    private Instant timestamp;

    @Schema(description = "HTTP status code")
    private int status;

    @Schema(description = "Error message")
    private String error;

    @Schema(description = "Detailed error messages")
    private List<String> details;

    @Schema(description = "Request path")
    private String path;

    public static ErrorResponse of(int status, String error, List<String> details, String path) {
        return ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(status)
                .error(error)
                .details(details)
                .path(path)
                .build();
    }
}
