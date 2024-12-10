package com.multiversa.SistAcad.Service;
import com.multiversa.SistAcad.Repository.MatriculaRepository;
import com.multiversa.SistAcad.model.AlunoModel;
import com.multiversa.SistAcad.model.MatriculaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository matriculaRepository;

    // Metodo para salvar ou atualizar o aluno
    public MatriculaModel save(Long id, MatriculaModel Matricula) {
        return matriculaRepository.save(Matricula);
    }

    public MatriculaModel update(MatriculaModel matricula, Long id) {
        return matriculaRepository.save(matricula);
    }

    // Metodo para buscar um aluno por ID
    public Optional<MatriculaModel> findById(Long id) {
        return matriculaRepository.findById(id);
    }

    // Metodo para buscar todos os alunos
    public List<MatriculaModel> findAll() {
        return matriculaRepository.findAll();
    }

    // Metodo para excluir um aluno por ID
    public void deleteById(Long id) {
        matriculaRepository.deleteById(id);
    }

}
