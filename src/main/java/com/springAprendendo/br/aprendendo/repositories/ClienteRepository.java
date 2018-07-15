package com.springAprendendo.br.aprendendo.repositories;

import com.springAprendendo.br.aprendendo.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
