package com.javinha.rinha.record;


public record PostTransacaoRequest(

        Integer valor,

        String tipo,

        String descricao
) {
        public boolean isValid () {
                return valor != null && valor >= 0 && tipo != null && descricao != null && (tipo.charAt(0) == 'c' || tipo.charAt(0) == 'd') && tipo.length() == 1 && descricao.length() >= 1 && descricao.length() <= 10;
        }
}
