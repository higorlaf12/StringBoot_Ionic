package com.springAprendendo.br.aprendendo.repositories;

import com.springAprendendo.br.aprendendo.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

}
