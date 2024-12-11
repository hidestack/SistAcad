package com.multiversa.SistAcad.Service;

import com.multiversa.SistAcad.Repository.MatriculaRepository;
import com.multiversa.SistAcad.model.MatriculaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {

    private static final Logger logger = LoggerFactory.getLogger(MatriculaService.class);

    @Autowired
    private MatriculaRepository matriculaRepository;

    // Método para salvar ou atualizar a matrícula
    public MatriculaModel save(MatriculaModel matricula) {
        if (matricula == null) {
            logger.error("Tentativa de salvar matrícula nula.");
            throw new IllegalArgumentException("Matrícula não pode ser nula.");
        }

        logger.info("Salvando matrícula para aluno: {}");
        return matriculaRepository.save(matricula);
    }

    // Método para atualizar a matrícula
    public MatriculaModel update(MatriculaModel matricula, Long id) {
        if (matricula == null || id == null) {
            logger.error("Matrícula ou ID fornecido para atualização é nulo.");
            throw new IllegalArgumentException("Matrícula e ID não podem ser nulos.");
        }

        Optional<MatriculaModel> existingMatricula = matriculaRepository.findById(id);
        if (!existingMatricula.isPresent()) {
            logger.warn("Tentativa de atualizar matrícula com ID {} que não existe.", id);
            throw new IllegalArgumentException("Matrícula não encontrada para atualização.");
        }

        // Atualiza os dados da matrícula
        MatriculaModel matriculaToUpdate = existingMatricula.get();

        logger.info("Matrícula com ID {} atualizada com sucesso.", id);
        return matriculaRepository.save(matriculaToUpdate);
    }

    // Método para buscar uma matrícula por ID
    public Optional<MatriculaModel> findById(Long id) {
        if (id == null) {
            logger.error("ID fornecido para busca é nulo.");
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        Optional<MatriculaModel> matricula = matriculaRepository.findById(id);
        if (matricula.isPresent()) {
            logger.info("Matrícula encontrada: {}", matricula.get().getId());
        } else {
            logger.warn("Matrícula com ID {} não encontrada.", id);
        }

        return matricula;
    }

    // Método para buscar todas as matrículas
    public List<MatriculaModel> findAll() {
        logger.info("Buscando todas as matrículas.");
        List<MatriculaModel> matriculas = matriculaRepository.findAll();
        if (matriculas.isEmpty()) {
            logger.warn("Nenhuma matrícula encontrada.");
        }
        return matriculas;
    }

    // Método para excluir uma matrícula por ID
    public void deleteById(Long id) {
        if (id == null) {
            logger.error("ID fornecido para exclusão é nulo.");
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        if (!matriculaRepository.existsById(id)) {
            logger.warn("Tentativa de excluir matrícula com ID {} que não existe.", id);
            throw new IllegalArgumentException("Matrícula não encontrada para exclusão.");
        }

        matriculaRepository.deleteById(id);
        logger.info("Matrícula com ID {} excluída com sucesso.", id);
    }
}
