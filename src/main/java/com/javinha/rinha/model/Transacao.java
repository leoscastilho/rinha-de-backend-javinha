package com.javinha.rinha.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes")
@Cacheable
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer valor;
    private String tipo;
    private String descricao;
    @Column(name = "realizada_em")
    private LocalDateTime realizadaEm;
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Clientes clientes;

    public Transacao (){

    }
    public Transacao (Integer valor, String tipo, String descricao, Clientes clientes){
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.realizadaEm = LocalDateTime.now();
        this.clientes = clientes;
    }

    public Long getId() {
        return id;
    }

    public Integer getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getRealizadaEm() {
        return realizadaEm;
    }
}
