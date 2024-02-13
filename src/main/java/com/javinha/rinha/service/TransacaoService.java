package com.javinha.rinha.service;

import com.javinha.rinha.model.Clientes;
import com.javinha.rinha.model.Transacao;
import com.javinha.rinha.record.PostTransacaoRequest;
import com.javinha.rinha.repository.TransacaoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransacaoService {

    private final TransacaoRepository transacaoRepository;

    public TransacaoService (TransacaoRepository transacaoRepository){
        this.transacaoRepository = transacaoRepository;
    }

    @Transactional
    public Transacao save (Transacao transacao){
        return transacaoRepository.save(transacao);
    }

    @Transactional
    public List<Transacao> findLatestTransactionsByClienteId (Long clienteId, Long limit){
        return this.transacaoRepository.findLastTransactionByClienteId(clienteId, limit);
    }

    @Transactional
    public Transacao processaTransacao (PostTransacaoRequest postTransacaoRequest, Clientes clientes) {
        if (!postTransacaoRequest.isValid()){
            throw new IllegalArgumentException("Transacao com dados invalidos!");
        }

//        if (postTransacaoRequest.tipo().equals("d") && (Math.multiplyExact(clientes.getLimite(), -1) > (postTransacaoRequest.valor() + clientes.getSaldo()))){
//            throw new TransacaoException("Transacao com saldo Inconsistente");
//        }

        Transacao transacao = new Transacao(postTransacaoRequest.valor(), postTransacaoRequest.tipo(), postTransacaoRequest.descricao(), clientes);

        return this.save(transacao);
    }
}
