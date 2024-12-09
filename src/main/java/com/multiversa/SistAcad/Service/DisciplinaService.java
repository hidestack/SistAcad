package com.multiversa.SistAcad.Service;
import com.multiversa.SistAcad.Repository.DisciplinaRepository;
import com.multiversa.SistAcad.model.DisciplinaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    // Metodo para salvar ou atualizar o aluno
    public DisciplinaModel save(Long id, DisciplinaModel disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    // Metodo para buscar um aluno por ID
    public Optional<DisciplinaModel> findById(Long id) {
        return disciplinaRepository.findById(id);
    }

    // Metodo para buscar todos os alunos
    public List<DisciplinaModel> findAll() {
        return disciplinaRepository.findAll();
    }

    // Metodo para excluir um aluno por ID
    public void deleteById(Long id) {
        disciplinaRepository.deleteById(id);
    }

}
