package com.unifacisa.tap.service;

import com.unifacisa.tap.domain.entity.Aluno;
import com.unifacisa.tap.exception.AlunoException;
import com.unifacisa.tap.exception.AlunoNaoEncontradoException;
import com.unifacisa.tap.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno buscarAlunoPorMatricula(Integer matricula) {

        return alunoRepository.findById(matricula).
                orElseThrow(() -> new AlunoNaoEncontradoException("Aluno não encontrado por matrícula"));
    }

    public List<Aluno> buscarAlunoPorStatusMatriculaETurma(Integer turma, Boolean matriculado) {
        List<Aluno> alunos = alunoRepository.buscarAlunoPorNomeEStatusMatricula(turma, matriculado);
        if (alunos.isEmpty()) {
            throw new AlunoNaoEncontradoException("Nenhum aluno foi encontrado por turma e status da matrícula");
        }
        return alunos;
    }

    public List<Aluno> buscarTodosOsAlunos() {
        List<Aluno> alunos = alunoRepository.findAll();
        if (alunos.isEmpty()) {
            throw new AlunoNaoEncontradoException("Nenhum aluno foi encontrado na base");
        }
        return alunos;
    }

    public Aluno matricularAluno(Aluno aluno) {
        try {
            return alunoRepository.save(aluno);
        } catch (Exception e) {
            throw new AlunoException("Erro ao matricular aluno");
        }
    }

    public Aluno editarDadosDoAluno(Integer matricula, Aluno dadosAlterados) {

        try {
            var aluno = buscarAlunoPorMatricula(matricula);

            aluno.setFoto(dadosAlterados.getFoto());
            aluno.setNumeroTurma(dadosAlterados.getNumeroTurma());
            aluno.setMatriculado(dadosAlterados.getMatriculado());

            return alunoRepository.save(aluno);
        } catch (Exception e) {
            throw new AlunoException("Erro ao editar aluno");
        }
    }

    public void deletarAluno(Integer matricula) {
        try {
            alunoRepository.deleteById(matricula);
        } catch (Exception e) {
            throw new AlunoException("Erro ao deletar aluno");
        }
    }
}
