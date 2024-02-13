package com.javinha.rinha.model;

import jakarta.persistence.*;
@Entity
@Table(name = "saldos")
@Cacheable
public class Saldo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int valor;
    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    Clientes clientes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Clientes getClientes() {
        return clientes;
    }

    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }
}
