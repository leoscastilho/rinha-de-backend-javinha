package com.javinha.rinha.service;


import com.javinha.rinha.exception.SaldoException;
import com.javinha.rinha.model.Clientes;
import com.javinha.rinha.model.Saldo;
import com.javinha.rinha.model.Transacao;
import com.javinha.rinha.record.*;
import com.javinha.rinha.repository.ClientesRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClientesService {


    private final ClientesRepository clientesRepository;


    private final TransacaoService transacaoService;

    private final SaldoService saldoService;

    public ClientesService (ClientesRepository clientesRepository, TransacaoService transacaoService, SaldoService saldoService){
        this.clientesRepository = clientesRepository;
        this.transacaoService = transacaoService;
        this.saldoService = saldoService;
    }



    public PostTransacaoResponse processaTransacaoEAtualizaSaldo (PostTransacaoRequest postTransacaoRequest, Long id) throws SaldoException, ChangeSetPersister.NotFoundException {


        Clientes clientes = this.findById(id);

        Transacao transacao = transacaoService.processaTransacao(postTransacaoRequest, clientes);

        Saldo saldo = saldoService.updateSaldo(clientes, transacao.getValor(), transacao.getTipo());

        return new PostTransacaoResponse(clientes.getLimite(), saldo.getValor());
    }

//    public void updateSaldo(Clientes clientes, Integer valor, String transactionType) throws SaldoException {
//        Integer saldo = clientes.getSaldo();
//
//        if (transactionType.equals("d")){
//            saldo -= valor;
//
//            if ((saldo + clientes.getLimite()) < 0){
//                throw new SaldoException("Transação não permitida. Saldo negativo excede limite.");
//            }
//        }
//        else {
//            saldo += valor;
//        }
//
//        clientes.setSaldo(saldo);
//        this.updateBalance(clientes);
//
//    }

//    private void updateBalance(Clientes cliente, int saldo, String tipoTransacao) {
//        var saldoAtual = cliente.getSaldo();
//
//        switch (tipoTransacao) {
//            case "d" -> processaDebito(cliente, saldo);
//            case "c" -> cliente.setSaldo(saldoAtual + saldo);
//        }
//
//        updateBalance(cliente);
//    }
//
//    private void processaDebito(Clientes cliente, Integer saldo) {
//        var novoSaldo = cliente.getSaldo() - saldo;
//
//        if ((novoSaldo + cliente.getLimite()) < 0) {
//            throw new WebApplicationException("Transacao nao permitida. Saldo negativo excede o limite", 422);
//        }
//
//        cliente.setSaldo(novoSaldo);
//    }

    @Transactional
    public void updateBalance (Clientes clientes){
        clientes = this.clientesRepository.saveAndFlush(clientes);
       // this.clientesRepository.updateBalance(clientes.getId(), clientes.getSaldo()); FIXME IT IS NOT WORKING
    }

    public ExtratoResponse processaExtrato (Long id) throws ChangeSetPersister.NotFoundException {

        Clientes clientes = this.findById(id);

        List<TransacaoResponse> transacaoResponses = transacaoService
                .findLatestTransactionsByClienteId(id, 10L)
                .stream()
                .map(transacao -> new TransacaoResponse(transacao.getValor(), transacao.getTipo(), transacao.getDescricao(), transacao.getRealizadaEm()))
                .toList();

        Saldo saldo = saldoService.findByClienteId(clientes.getId());

        SaldoResponse saldoResponse = new SaldoResponse(saldo.getValor(), LocalDateTime.now(), clientes.getLimite());

        return new ExtratoResponse(saldoResponse, transacaoResponses);
    }


    public Clientes findById (Long id) throws ChangeSetPersister.NotFoundException {
        return clientesRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }
}
