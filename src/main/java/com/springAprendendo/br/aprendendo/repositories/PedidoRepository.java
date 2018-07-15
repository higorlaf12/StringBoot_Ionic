package com.springAprendendo.br.aprendendo.repositories;

import com.springAprendendo.br.aprendendo.domain.Cidade;
import com.springAprendendo.br.aprendendo.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
