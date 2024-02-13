package com.javinha.rinha.service;

import com.javinha.rinha.exception.SaldoException;
import com.javinha.rinha.model.Clientes;
import com.javinha.rinha.model.Saldo;
import com.javinha.rinha.repository.SaldoRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaldoService {

    private final SaldoRepository saldoRepository;

    public SaldoService (SaldoRepository saldoRepository){
        this.saldoRepository = saldoRepository;
    }

    @Transactional
    public Saldo findByClienteId (Long clienteId) throws ChangeSetPersister.NotFoundException {
        return this.saldoRepository.findByClientes_Id(clienteId).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Transactional
    public Saldo updateSaldo(Clientes clientes, Integer valor, String transactionType) throws SaldoException, ChangeSetPersister.NotFoundException {

        Saldo saldo = this.findByClienteId(clientes.getId());

        Integer novoSaldo = saldo.getValor();

        if (transactionType.equals("d")){
            novoSaldo -= valor;

            if ((novoSaldo + clientes.getLimite()) < 0){
                throw new SaldoException("Transação não permitida. Saldo negativo excede limite.");
            }
        }
        else {
            novoSaldo += valor;
        }

        saldo.setValor(novoSaldo);

        return this.save(saldo);

    }

    @Transactional
    public Saldo save (Saldo saldo){
        return this.saldoRepository.save(saldo);
    }
}
