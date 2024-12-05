package com.multiversa.SistAcad.Service;
import com.multiversa.SistAcad.model.AlunoModel;
import com.multiversa.SistAcad.Repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    // Metodo para salvar ou atualizar o aluno
    public AlunoModel save(Long id, AlunoModel aluno) {
        return alunoRepository.save(aluno);
    }

    // Metodo para buscar um aluno por ID
    public Optional<AlunoModel> findById(Long id) {
        return alunoRepository.findById(id);
    }

    // Metodo para buscar todos os alunos
    public List<AlunoModel> findAll() {
        return alunoRepository.findAll();
    }

    // Metodo para excluir um aluno por ID
    public void deleteById(Long id) {
        alunoRepository.deleteById(id);
    }

}
