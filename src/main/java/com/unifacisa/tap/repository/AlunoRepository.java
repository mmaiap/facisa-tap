package com.unifacisa.tap.repository;

import com.unifacisa.tap.domain.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    @Query(value = "SELECT a FROM Aluno a WHERE a.numeroTurma = :numeroTurma AND a.matriculado = :matriculado")
    List<Aluno> buscarAlunoPorNomeEStatusMatricula(@Param("numeroTurma") Integer numeroTurma, @Param("matriculado") Boolean matriculado);
}
