package com.javinha.rinha.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes")
@Getter
@NoArgsConstructor
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
    Clientes clientes;

    public Transacao (Integer valor, String tipo, String descricao, Clientes clientes){
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.realizadaEm = LocalDateTime.now();
        this.clientes = clientes;
    }


}
