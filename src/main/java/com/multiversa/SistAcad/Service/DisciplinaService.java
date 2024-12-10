package com.multiversa.SistAcad.Service;

import com.multiversa.SistAcad.Repository.DisciplinaRepository;
import com.multiversa.SistAcad.model.DisciplinaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.Data;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class DisciplinaService {

    private static final Logger logger = LoggerFactory.getLogger(DisciplinaService.class);

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    // Metodo para salvar uma disciplina
    public DisciplinaModel save(DisciplinaModel disciplina) {
        if (disciplina == null) {
            logger.error("Tentativa de salvar disciplina nula.");
            throw new IllegalArgumentException("Disciplina não pode ser nula.");
        }

        logger.info("Salvando a disciplina: {}");
        return disciplinaRepository.save(disciplina);
    }

    // Metodo para buscar uma disciplina por ID
    public Optional<DisciplinaModel> findById(Long id) {
        if (id == null) {
            logger.error("ID fornecido é nulo.");
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        Optional<DisciplinaModel> disciplina = disciplinaRepository.findById(id);
        if (disciplina.isPresent()) {
            logger.info("Disciplina encontrada: {}");
        } else {
            logger.warn("Disciplina com ID {} não encontrada.", id);
        }

        return disciplina;
    }

    // Metodo para buscar todas as disciplinas
    public List<DisciplinaModel> findAll() {
        logger.info("Buscando todas as disciplinas.");
        List<DisciplinaModel> disciplinas = disciplinaRepository.findAll();
        if (disciplinas.isEmpty()) {
            logger.warn("Nenhuma disciplina encontrada.");
        }
        return disciplinas;
    }

    // Metodo para excluir uma disciplina por ID
    public void deleteById(Long id) {
        if (id == null) {
            logger.error("ID fornecido para exclusão é nulo.");
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        if (!disciplinaRepository.existsById(id)) {
            logger.warn("Tentativa de excluir disciplina com ID {} que não existe.", id);
            throw new IllegalArgumentException("Disciplina não encontrada para exclusão.");
        }

        disciplinaRepository.deleteById(id);
        logger.info("Disciplina com ID {} excluída com sucesso.", id);
    }

    // Metodo para atualizar uma disciplina
    public DisciplinaModel update(DisciplinaModel disciplina, Long id) {
        if (disciplina == null || id == null) {
            logger.error("Disciplina ou ID fornecido para atualização é nulo.");
            throw new IllegalArgumentException("Disciplina e ID não podem ser nulos.");
        }

        Optional<DisciplinaModel> existingDisciplina = disciplinaRepository.findById(id);
        if (!existingDisciplina.isPresent()) {
            logger.warn("Tentativa de atualizar disciplina com ID {} que não existe.", id);
            throw new IllegalArgumentException("Disciplina não encontrada para atualização.");
        }

        // Atualiza os dados da disciplina (por exemplo, nome, etc.)
        DisciplinaModel updatedDisciplina = existingDisciplina.get();

        logger.info("Disciplina com ID {} atualizada com sucesso.", id);
        return disciplinaRepository.save(updatedDisciplina);
    }
}
