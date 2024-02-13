package com.javinha.rinha.record;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record TransacaoResponse(
        Integer valor,
        String tipo,
        String descricao,
        @JsonProperty("realizada_em")
        @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'")
        LocalDateTime realizadaEm
) {}
