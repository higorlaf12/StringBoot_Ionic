package com.springAprendendo.br.aprendendo.repositories;

import com.springAprendendo.br.aprendendo.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
