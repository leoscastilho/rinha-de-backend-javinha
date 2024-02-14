package com.javinha.rinha.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record SaldoResponse(
        Integer total,
        @JsonProperty("data_extrato")
        @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'")
        LocalDateTime dataExtrato,
        Integer limite
)
{}
