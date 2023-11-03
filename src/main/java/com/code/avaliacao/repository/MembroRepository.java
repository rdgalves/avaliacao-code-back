package com.code.avaliacao.repository;

import com.code.avaliacao.model.Membros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembroRepository extends JpaRepository<Membros, Long> {
}
