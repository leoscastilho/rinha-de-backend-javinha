package com.javinha.rinha.enums;

public enum TransacaoTipo {

    CREDITO("C"),
    DEBITO("D");

    private String code;
    TransacaoTipo(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }

}
