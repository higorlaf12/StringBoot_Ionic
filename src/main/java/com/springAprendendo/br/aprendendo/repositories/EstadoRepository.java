package com.springAprendendo.br.aprendendo.repositories;

import com.springAprendendo.br.aprendendo.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
