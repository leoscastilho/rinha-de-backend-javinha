package com.javinha.rinha.model;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "saldos")
@Getter
public class Saldo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int valor;

    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    Clientes clientes;

}
