package com.javinha.rinha.repository;

import com.javinha.rinha.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

    @Query(value = "SELECT * FROM transacoes t WHERE t.cliente_id = :id ORDER BY id DESC LIMIT :limit"
            ,nativeQuery = true)
    List<Transacao> findLastTransactionByClienteId (@Param("id") Long id, @Param("limit") Long limit);
}
