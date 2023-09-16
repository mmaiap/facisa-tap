package com.unifacisa.tap.controller;

import com.unifacisa.tap.domain.entity.Aluno;
import com.unifacisa.tap.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api-aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping(value = "/buscar/{matricula}")
    public ResponseEntity<Aluno> getAlunoPorMatricula(@PathVariable Integer matricula) {

        Aluno aluno = alunoService.buscarAlunoPorMatricula(matricula);
        return new ResponseEntity<>(aluno, HttpStatus.OK);
    }

    @GetMapping(value = "/buscar")
    public ResponseEntity<List<Aluno>> getAlunoPorStatusMatriculaETurma(@RequestParam("matriculado") Boolean matriculado, @RequestParam("turma") Integer turma) {

        List<Aluno> aluno = alunoService.buscarAlunoPorStatusMatriculaETurma(turma, matriculado);
        return new ResponseEntity<>(aluno, HttpStatus.OK);
    }

    @GetMapping(value = "/buscar-todos")
    public ResponseEntity<List<Aluno>> getTodosOsAlunos() {

        List<Aluno> aluno = alunoService.buscarTodosOsAlunos();
        return new ResponseEntity<>(aluno, HttpStatus.OK);
    }

    @PostMapping(path = "/matricular")
    public ResponseEntity<Aluno> postAluno(@RequestBody Aluno novoAluno){
        Aluno aluno = alunoService.matricularAluno(novoAluno);
        return new ResponseEntity<>(aluno, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/deletar/{matricula}")
    public ResponseEntity<Aluno> deleteAluno(@PathVariable Integer matricula){
        alunoService.deletarAluno(matricula);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/editar/{matricula}")
    public ResponseEntity<Aluno> deleteAluno(@PathVariable Integer matricula, @RequestBody Aluno alunoEditado){
        Aluno aluno = alunoService.editarDadosDoAluno(matricula, alunoEditado);
        return new ResponseEntity<>(aluno, HttpStatus.OK);
    }
}
