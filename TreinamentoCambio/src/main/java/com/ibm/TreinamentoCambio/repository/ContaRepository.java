package com.ibm.TreinamentoCambio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.TreinamentoCambio.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {

}
