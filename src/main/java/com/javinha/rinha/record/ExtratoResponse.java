package com.javinha.rinha.record;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.javinha.rinha.model.Saldo;

import java.util.List;

public record ExtratoResponse(

        @JsonProperty("saldo")
        SaldoResponse saldoResponse,

        @JsonProperty("ultimas_transacoes")
        List<TransacaoResponse> ultimasTransacoes
        )
{
}
