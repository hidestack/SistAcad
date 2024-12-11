package com.multiversa.SistAcad.Service;

import com.multiversa.SistAcad.model.AlunoModel;
import com.multiversa.SistAcad.Repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private static final Logger logger = LoggerFactory.getLogger(AlunoService.class);

    @Autowired
    private AlunoRepository alunoRepository;

    // Método para salvar ou atualizar o aluno
    public AlunoModel save(AlunoModel aluno) {
        if (aluno == null) {
            logger.error("Tentativa de salvar aluno nulo.");
            throw new IllegalArgumentException("Aluno não pode ser nulo.");
        }

        logger.info("Salvando aluno: {}", aluno.getNomeAluno());
        return alunoRepository.save(aluno);
    }

    // Método para atualizar um aluno
    public AlunoModel update(AlunoModel aluno, Long id) {
        if (aluno == null || id == null) {
            logger.error("Aluno ou ID fornecido para atualização é nulo.");
            throw new IllegalArgumentException("Aluno e ID não podem ser nulos.");
        }

        Optional<AlunoModel> existingAluno = alunoRepository.findById(id);
        if (!existingAluno.isPresent()) {
            logger.warn("Tentativa de atualizar aluno com ID {} que não existe.", id);
            throw new IllegalArgumentException("Aluno não encontrado para atualização.");
        }

        // Atualiza os dados do aluno
        AlunoModel alunoToUpdate = existingAluno.get();
        alunoToUpdate.setNomeAluno(aluno.getNomeAluno()); // ou outros campos
        // Adicione outros campos de atualização, se necessário

        logger.info("Aluno com ID {} atualizado com sucesso.", id);
        return alunoRepository.save(alunoToUpdate);
    }

    // Método para buscar um aluno por ID
    public Optional<AlunoModel> findById(Long id) {
        if (id == null) {
            logger.error("ID fornecido para busca é nulo.");
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        Optional<AlunoModel> aluno = alunoRepository.findById(id);
        if (aluno.isPresent()) {
            logger.info("Aluno encontrado: {}", aluno.get().getNomeAluno());
        } else {
            logger.warn("Aluno com ID {} não encontrado.", id);
        }

        return aluno;
    }

    // Método para buscar todos os alunos
    public List<AlunoModel> findAll() {
        logger.info("Buscando todos os alunos.");
        List<AlunoModel> alunos = alunoRepository.findAll();
        if (alunos.isEmpty()) {
            logger.warn("Nenhum aluno encontrado.");
        }
        return alunos;
    }

    // Método para excluir um aluno por ID
    public void deleteById(Long id) {
        if (id == null) {
            logger.error("ID fornecido para exclusão é nulo.");
            throw new IllegalArgumentException("ID não pode ser nulo.");
        }

        if (!alunoRepository.existsById(id)) {
            logger.warn("Tentativa de excluir aluno com ID {} que não existe.", id);
            throw new IllegalArgumentException("Aluno não encontrado para exclusão.");
        }

        alunoRepository.deleteById(id);
        logger.info("Aluno com ID {} excluído com sucesso.", id);
    }
}
