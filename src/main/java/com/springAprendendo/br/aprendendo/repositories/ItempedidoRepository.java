package com.springAprendendo.br.aprendendo.repositories;

import com.springAprendendo.br.aprendendo.domain.Cliente;
import com.springAprendendo.br.aprendendo.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItempedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
